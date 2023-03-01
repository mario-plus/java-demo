package com.mario.scan;

import com.mario.annoation.*;
import com.mario.until.BoomAnnotationUtils;
import com.mario.until.MyAnnotationUtils;
import com.mario.until.SpringCompatUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Arrays.asList;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;
import static org.springframework.context.annotation.AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR;
import static org.springframework.util.ClassUtils.resolveClassName;

/**
 * @author zxz
 * @date 2023年03月01日 10:52
 */
public class BoomServiceAnnotationPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware,
        ResourceLoaderAware, BeanClassLoaderAware, ApplicationContextAware, InitializingBean {

    //region Description
    private final static List<Class<? extends Annotation>> serviceAnnotationTypes = asList(
            BoomService.class,BoomComponent.class
    );

    protected final Set<String> packagesToScan;

    public BoomServiceAnnotationPostProcessor(Set<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    private Set<String> resolvedPackagesToScan;

    private Environment environment;

    private ResourceLoader resourceLoader;

    private ClassLoader classLoader;

    private BeanDefinitionRegistry registry;

    private BoomServicePackageHolder servicePackagesHolder;

    private volatile boolean scanned = false;
    //endregion


    //1
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    //2
    @Override
    public void afterPropertiesSet() throws Exception {
        this.resolvedPackagesToScan = resolvePackagesToScan(packagesToScan);
    }

    //3
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registry = registry;
        scanServiceBeans(resolvedPackagesToScan, registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (this.registry == null) {
            this.registry = (BeanDefinitionRegistry) beanFactory;
        }
        // scan bean definitions
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            Map<String, Object> annotationAttributes = getServiceAnnotationAttributes(beanDefinition);
            if (annotationAttributes != null) {
                processAnnotatedBeanDefinition(beanName, (AnnotatedBeanDefinition) beanDefinition, annotationAttributes);
            }
        }
        if (!scanned) {
            // In spring 3.x, may be not call postProcessBeanDefinitionRegistry(), so scan service class here
            scanServiceBeans(resolvedPackagesToScan, registry);
        }
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //this.servicePackagesHolder = applicationContext.getBean(BoomServicePackageHolder.BEAN_NAME, BoomServicePackageHolder.class);
        this.servicePackagesHolder = new BoomServicePackageHolder();

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }







    private void processAnnotatedBeanDefinition(String refServiceBeanName, AnnotatedBeanDefinition refServiceBeanDefinition, Map<String, Object> attributes) {

        Map<String, Object> serviceAnnotationAttributes = new LinkedHashMap<>(attributes);
        String returnTypeName = SpringCompatUtils.getFactoryMethodReturnType(refServiceBeanDefinition);
        Class<?> beanClass = resolveClassName(returnTypeName, classLoader);
        String serviceInterface = BoomAnnotationUtils.resolveInterfaceName(serviceAnnotationAttributes, beanClass);

        // TODO ServiceBean Bean name  可以做版本控制 一个interface有多个实现
        //String serviceBeanName = generateServiceBeanName(serviceAnnotationAttributes, serviceInterface);

        AbstractBeanDefinition serviceBeanDefinition = buildServiceBeanDefinition(serviceAnnotationAttributes, serviceInterface, refServiceBeanName);

        // set id
        serviceBeanDefinition.getPropertyValues().add("id", refServiceBeanName);

        registerServiceBeanDefinition(refServiceBeanName, serviceBeanDefinition, serviceInterface);
    }


    private Map<String, Object> getServiceAnnotationAttributes(BeanDefinition beanDefinition) {
        if (beanDefinition instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            MethodMetadata factoryMethodMetadata = SpringCompatUtils.getFactoryMethodMetadata(annotatedBeanDefinition);
            if (factoryMethodMetadata != null) {
                // try all dubbo service annotation types
                for (Class<? extends Annotation> annotationType : serviceAnnotationTypes) {
                    if (factoryMethodMetadata.isAnnotated(annotationType.getName())) {
                        // Since Spring 5.2
                        // return factoryMethodMetadata.getAnnotations().get(annotationType).filterDefaultValues().asMap();
                        // Compatible with Spring 4.x
                        Map<String, Object> annotationAttributes = factoryMethodMetadata.getAnnotationAttributes(annotationType.getName());
                        return MyAnnotationUtils.filterDefaultValues(annotationType, annotationAttributes);
                    }
                }
            }
        }
        return null;
    }


    private Set<String> resolvePackagesToScan(Set<String> packagesToScan) {
        Set<String> resolvedPackagesToScan = new LinkedHashSet<>(packagesToScan.size());
        for (String packageToScan : packagesToScan) {
            if (StringUtils.hasText(packageToScan)) {
                String resolvedPackageToScan = environment.resolvePlaceholders(packageToScan.trim());
                resolvedPackagesToScan.add(resolvedPackageToScan);
            }
        }
        return resolvedPackagesToScan;
    }


    //TODO
    private void scanServiceBeans(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        scanned = true;
        if (CollectionUtils.isEmpty(packagesToScan)) {
            return;
        }
        BoomClassPathBeanDefinitionScanner scanner =
                new BoomClassPathBeanDefinitionScanner(registry, environment, resourceLoader);
        BeanNameGenerator beanNameGenerator = resolveBeanNameGenerator(registry);
        scanner.setBeanNameGenerator(beanNameGenerator);
        for (Class<? extends Annotation> annotationType : serviceAnnotationTypes) {
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType));
        }

        ScanExcludeFilter scanExcludeFilter = new ScanExcludeFilter();
        scanner.addExcludeFilter(scanExcludeFilter);

        for (String packageToScan : packagesToScan) {
            // avoid duplicated scans
            if (servicePackagesHolder.isPackageScanned(packageToScan)) {
                continue;
            }
            scanner.scan(packageToScan);

            Set<BeanDefinitionHolder> beanDefinitionHolders =
                    findServiceBeanDefinitionHolders(scanner, packageToScan, registry, beanNameGenerator);
            if (!CollectionUtils.isEmpty(beanDefinitionHolders)) {
                for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
                    //processScannedBeanDefinition(beanDefinitionHolder);
                    servicePackagesHolder.addScannedClass(beanDefinitionHolder.getBeanDefinition().getBeanClassName());
                }
            }
            servicePackagesHolder.addScannedPackage(packageToScan);
        }
    }


    private BeanNameGenerator resolveBeanNameGenerator(BeanDefinitionRegistry registry) {

        BeanNameGenerator beanNameGenerator = null;

        if (registry instanceof SingletonBeanRegistry) {
            SingletonBeanRegistry singletonBeanRegistry = SingletonBeanRegistry.class.cast(registry);
            beanNameGenerator = (BeanNameGenerator) singletonBeanRegistry.getSingleton(CONFIGURATION_BEAN_NAME_GENERATOR);
        }
        if (beanNameGenerator == null) {
            beanNameGenerator = new AnnotationBeanNameGenerator();

        }
        return beanNameGenerator;

    }


    private Set<BeanDefinitionHolder> findServiceBeanDefinitionHolders(
            ClassPathBeanDefinitionScanner scanner, String packageToScan, BeanDefinitionRegistry registry,
            BeanNameGenerator beanNameGenerator) {

        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(packageToScan);

        Set<BeanDefinitionHolder> beanDefinitionHolders = new LinkedHashSet<>(beanDefinitions.size());

        for (BeanDefinition beanDefinition : beanDefinitions) {

            String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, beanName);
            beanDefinitionHolders.add(beanDefinitionHolder);

        }

        return beanDefinitionHolders;

    }

    private void registerServiceBeanDefinition(String serviceBeanName, AbstractBeanDefinition serviceBeanDefinition, String serviceInterface) {
        if (registry.containsBeanDefinition(serviceBeanName)) {
            BeanDefinition existingDefinition = registry.getBeanDefinition(serviceBeanName);
            if (existingDefinition.equals(serviceBeanDefinition)) {
                return;
            }
            String msg = "Found duplicated BeanDefinition of service interface [" + serviceInterface + "] with bean name [" + serviceBeanName +
                    "], existing definition [ " + existingDefinition + "], new definition [" + serviceBeanDefinition + "]";
            throw new BeanDefinitionStoreException(serviceBeanDefinition.getResourceDescription(), serviceBeanName, msg);
        }
        registry.registerBeanDefinition(serviceBeanName, serviceBeanDefinition);
    }


    private AbstractBeanDefinition buildServiceBeanDefinition(Map<String, Object> serviceAnnotationAttributes, String serviceInterface, String refServiceBeanName) {

        BeanDefinitionBuilder builder = rootBeanDefinition(refServiceBeanName);

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        //TODO 添加Bean参数

        return builder.getBeanDefinition();

    }



    private class ScanExcludeFilter implements TypeFilter {

        private int excludedCount;

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            String className = metadataReader.getClassMetadata().getClassName();
            boolean excluded = servicePackagesHolder.isClassScanned(className);
            if (excluded) {
                excludedCount++;
            }
            return excluded;
        }

        public int getExcludedCount() {
            return excludedCount;
        }
    }
}

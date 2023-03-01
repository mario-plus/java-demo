package com.mario.scan;



import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * @author zxz
 * @date 2023年03月01日 10:45
 */
public class BoomComponentScanRegistrar implements ImportBeanDefinitionRegistrar {


    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //扫描包路径
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);

        registerServiceAnnotationPostProcessor(packagesToScan, registry);
    }

    private void registerServiceAnnotationPostProcessor(Set<String> packagesToScan, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = rootBeanDefinition(BoomServiceAnnotationPostProcessor.class);
        builder.addConstructorArgValue(packagesToScan);
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);

    }


    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        //直接返回启动类当前包
        return Collections.singleton(ClassUtils.getPackageName(metadata.getClassName()));
    }


    private Set<String> getPackagesToScan0(AnnotationMetadata metadata, Class annotationClass, String basePackagesName, String basePackageClassesName) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(annotationClass.getName()));
        if (attributes == null) {
            return Collections.emptySet();
        }
        String[] basePackages = attributes.getStringArray(basePackagesName);
        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        Class<?>[] basePackageClasses = attributes.getClassArray(basePackageClassesName);
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (attributes.containsKey("value")) {
            String[] value = attributes.getStringArray("value");
            packagesToScan.addAll(Arrays.asList(value));
        }
        return packagesToScan;
    }
}

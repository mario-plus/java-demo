package com.mario.scan;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.springframework.context.annotation.AnnotationConfigUtils.registerAnnotationConfigProcessors;

/**
 * @author zxz
 * @date 2023年03月01日 11:05
 */
public class BoomClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {


    /**
     * key is package to scan, value is BeanDefinition
     */
    private final ConcurrentMap<String, Set<BeanDefinition>> beanDefinitionMap = new ConcurrentHashMap<>();

    public BoomClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment,
                                               ResourceLoader resourceLoader) {

        super(registry, useDefaultFilters);

        setEnvironment(environment);

        setResourceLoader(resourceLoader);

        registerAnnotationConfigProcessors(registry);

    }

    public BoomClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, Environment environment,
                                               ResourceLoader resourceLoader) {

        this(registry, false, environment, resourceLoader);

    }


    @Override
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> beanDefinitions = beanDefinitionMap.get(basePackage);
        // if beanDefinitions size is null => scan
        if (Objects.isNull(beanDefinitions)) {
            beanDefinitions = super.findCandidateComponents(basePackage);
            beanDefinitionMap.put(basePackage, beanDefinitions);
        }
        return beanDefinitions;
    }
}

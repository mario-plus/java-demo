package com.mario.until;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.PropertyResolver;

import java.lang.annotation.Annotation;
import java.util.*;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static org.springframework.util.CollectionUtils.arrayToList;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.util.ObjectUtils.nullSafeEquals;
import static org.springframework.util.StringUtils.trimWhitespace;

/**
 * @author zxz
 * @date 2023年03月01日 11:14
 */
public class MyAnnotationUtils extends AnnotationUtils {

    public static Map<String, Object> getAttributes(Annotation annotation, boolean ignoreDefaultValue,
                                                    String... ignoreAttributeNames) {
        return getAttributes(annotation, null, ignoreDefaultValue, ignoreAttributeNames);
    }

    public static Map<String, Object> getAttributes(Annotation annotation, PropertyResolver propertyResolver,
                                                    boolean ignoreDefaultValue, String... ignoreAttributeNames) {

        Map<String, Object> annotationAttributes = org.springframework.core.annotation.AnnotationUtils.getAnnotationAttributes(annotation);

        String[] actualIgnoreAttributeNames = ignoreAttributeNames;

        if (ignoreDefaultValue && !isEmpty(annotationAttributes)) {

            List<String> attributeNamesToIgnore = new LinkedList<String>(asList(ignoreAttributeNames));

            for (Map.Entry<String, Object> annotationAttribute : annotationAttributes.entrySet()) {
                String attributeName = annotationAttribute.getKey();
                Object attributeValue = annotationAttribute.getValue();
                if (nullSafeEquals(attributeValue, getDefaultValue(annotation, attributeName))) {
                    attributeNamesToIgnore.add(attributeName);
                }
            }
            // extends the ignored list
            actualIgnoreAttributeNames = attributeNamesToIgnore.toArray(new String[attributeNamesToIgnore.size()]);
        }

        return getAttributes(annotationAttributes, propertyResolver, actualIgnoreAttributeNames);
    }


    public static Map<String, Object> getAttributes(Map<String, Object> annotationAttributes,
                                                    PropertyResolver propertyResolver, String... ignoreAttributeNames) {

        Set<String> ignoreAttributeNamesSet = new HashSet<String>(arrayToList(ignoreAttributeNames));

        Map<String, Object> actualAttributes = new LinkedHashMap<String, Object>();

        for (Map.Entry<String, Object> annotationAttribute : annotationAttributes.entrySet()) {

            String attributeName = annotationAttribute.getKey();
            Object attributeValue = annotationAttribute.getValue();

            // ignore attribute name
            if (ignoreAttributeNamesSet.contains(attributeName)) {
                continue;
            }

            if (attributeValue instanceof String) {
                attributeValue = resolvePlaceholders(valueOf(attributeValue), propertyResolver);
            } else if (attributeValue instanceof String[]) {
                String[] values = (String[]) attributeValue;
                for (int i = 0; i < values.length; i++) {
                    values[i] = resolvePlaceholders(values[i], propertyResolver);
                }
                attributeValue = values;
            }
            actualAttributes.put(attributeName, attributeValue);
        }
        return actualAttributes;
    }

    private static String resolvePlaceholders(String attributeValue, PropertyResolver propertyResolver) {
        String resolvedValue = attributeValue;
        if (propertyResolver != null) {
            resolvedValue = propertyResolver.resolvePlaceholders(resolvedValue);
            resolvedValue = trimWhitespace(resolvedValue);
        }
        return resolvedValue;
    }

    public static <T> T getAttribute(Map<String, Object> attributes, String attributeName, boolean required) {
        T value = getAttribute(attributes, attributeName, null);
        if (required && value == null) {
            throw new IllegalStateException("The attribute['" + attributeName + "] is required!");
        }
        return value;
    }

    public static <T> T getAttribute(Map<String, Object> attributes, String attributeName, T defaultValue) {
        T value = (T) attributes.get(attributeName);
        return value == null ? defaultValue : value;
    }

    public static <T> T getAttribute(Map<String, Object> attributes, String attributeName) {
        return getAttribute(attributes, attributeName, false);
    }


    public static Map<String, Object> filterDefaultValues(Class<? extends Annotation> annotationType, Map<String, Object> attributes) {
        Map<String, Object> filteredAttributes = new LinkedHashMap<>(attributes.size());
        attributes.forEach((key, val) -> {
            if (!Objects.deepEquals(val, getDefaultValue(annotationType, key))) {
                filteredAttributes.put(key, val);
            }
        });
        // remove void class, compatible with spring 3.x
        Object interfaceClassValue = filteredAttributes.get("interfaceClass");
        if (interfaceClassValue instanceof String && "void".equals(String.valueOf(interfaceClassValue))) {
            filteredAttributes.remove("interfaceClass");
        }
        return filteredAttributes;
    }
}

package com.mario.until;



import com.mario.until.MyAnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.util.ClassUtils.getAllInterfacesForClass;


/**
 * @author zxz
 * @date 2023年03月01日 11:24
 */
public class BoomAnnotationUtils {

    public static String resolveInterfaceName(Map<String, Object> attributes, Class<?> defaultInterfaceClass) {
        String interfaceClassName = MyAnnotationUtils.getAttribute(attributes, "interfaceName",false);
        if (StringUtils.hasText(interfaceClassName)) {
            return interfaceClassName;
        }
        Class<?> interfaceClass = MyAnnotationUtils.getAttribute(attributes, "interfaceClass");
        if (interfaceClass == null || void.class.equals(interfaceClass)) { // default or set void.class for purpose.
            interfaceClass = null;
        }
        if (interfaceClass == null && defaultInterfaceClass != null) {

            Class<?>[] allInterfaces = getAllInterfacesForClass(defaultInterfaceClass);
            if (allInterfaces.length > 0) {
                interfaceClass = allInterfaces[0];
            }
        }
        Assert.notNull(interfaceClass, "@Service interfaceClass() or interfaceName() or interface class must be present!");
        Assert.isTrue(interfaceClass.isInterface(), "The annotated type must be an interface!");
        return interfaceClass.getName();
    }

}

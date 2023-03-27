package com.mario.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zxz
 * @date 2023年03月22日 14:58
 */
@Component
public class ReflectUtil implements ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * @param s 通过字符串反射调用: ${beanName.methodName(${param1},${param2})}
     */
    public String callByStr(String s, JSONObject jsonObject) throws Exception {
        if (s.startsWith("${") && s.endsWith("}") && s.contains(".") && s.contains("(") && s.contains(")")) {
            Object[] params;
            String content = StringUtil.getValueFromS1(s);
            String className = StringUtil.getFirstSplitByChar(content, "\\.");
            String contentWithoutMethodName = content.replace(className + ".", "");
            String beanName = StringUtil.getFirstSplitByChar(contentWithoutMethodName, "\\(");
            String paramsContent = contentWithoutMethodName.replace(beanName, "");
            if (paramsContent.length() == 2) {
                params = null;
            } else {

                String[] split = paramsContent.substring(1, paramsContent.length() - 1).split(",");
                params = new Object[split.length];
                int i = 0;
                for (String param : split) {
                    String valueFromS1 = StringUtil.getValueFromS1(param);
                    if (valueFromS1.contains(".")) {
                        params[i] = StringUtil.getValueFromD(valueFromS1, jsonObject);
                    } else {
                        params[i] = jsonObject.get(valueFromS1);
                    }
                    i++;
                }
            }
            return call(className, beanName, params).toString();//暂将返回值定为String
        } else {
            throw new Exception("字符串不符合规定");
        }
    }

    /**
     * 通过bean反射调用
     */
    private Object call(String beanName, String methodName, Object... param) throws Exception {
        try {
            Object bean = applicationContext.getBean(beanName);
            Method method = null;
            Method[] methods = bean.getClass().getMethods();
            for (Method value : methods) {
                if (value.getName().equals(methodName)) {
                    method = value;
                }
            }
            return method.invoke(bean, param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("调用失败");
        }
    }


}



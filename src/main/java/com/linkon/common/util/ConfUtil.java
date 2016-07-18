package com.linkon.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 配置文件读取工具
 * @author zx
 *
 */
public class ConfUtil extends PropertyPlaceholderConfigurer{

	private static Map<String, String> ctxPropertiesMap = new HashMap<String, String> ();

    public ConfUtil() {
    }

    @SuppressWarnings("rawtypes")
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        Iterator var3 = props.keySet().iterator();

        while(var3.hasNext()) {
            Object key = var3.next();
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> getAllConfig() {
        Map ret = ctxPropertiesMap;
        return ret;
    }

    public static String getConf(String name, String defaulValue) {
        String value = (String)ctxPropertiesMap.get(name);
        return value != null && !"".equals(value)?value:defaulValue;
    }

    public static String getConf(String name) {
        return getConf(name, (String)null);
    }
}

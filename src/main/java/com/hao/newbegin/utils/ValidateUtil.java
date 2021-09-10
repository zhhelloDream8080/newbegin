package com.hao.newbegin.utils;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.Collection;
import java.util.Map;

public class ValidateUtil {
    //工具类，判断是否为空
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String) value).length() == 0;
        } else if (value instanceof Collection) {
            return ((Collection) value).size() == 0;
        } else if (value instanceof Map) {
            return ((Map) value).size() == 0;
        } else if (value instanceof String[]) {
            return ((String[]) ((String[]) value)).length == 0;
        } else {
            return false;
        }
    }

    //工具类，判断是否为空
    public static boolean isNotEmpty(Collection value) {
        return value != null && value.size() > 0;
    }

    public static boolean isNotEmpty(String value){
        return value != null && value.length() > 0;
    }
}

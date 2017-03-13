package com.wuxian.charge.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jianglei on 2017/3/2.
 */

public class ObjectUtil<T> {
    public static HashMap<String, Object> convertToMap(Object object) {
        HashMap<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public T convertToObject(JSONObject jsonObject, Class<T> type) {
        Field[] fields = type.getDeclaredFields();
        T object = null;
        try {
            object = type.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        List list = new ArrayList();
                        JSONArray array = jsonObject.getJSONArray(field.getName());
                        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                        Class declarClass = (Class) parameterizedType.getActualTypeArguments()[0];
                        if (!declarClass.getName().startsWith("java")) {
                            int len = array.length();
                            for (int i = 0; i < len; i++) {
                                list.add(convertToObject(array.getJSONObject(i), declarClass));
                            }
                        } else {
                            int len = array.length();
                            for (int i = 0; i < len; i++) {
                                list.add(array.get(i));
                            }
                        }
                        field.set(object, list);
                    } else {
                        field.set(object, jsonObject.get(field.getName()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void convertToJSON(JSONObject root, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldObject = field.get(object);
                if (fieldObject instanceof Collection) {
                    JSONArray current = new JSONArray();
                    Collection collection = (Collection) fieldObject;
                    for (Object o : collection) {
                        if (o instanceof Boolean ||
                                o instanceof Byte ||
                                o instanceof Character ||
                                o instanceof Double ||
                                o instanceof Float ||
                                o instanceof Integer ||
                                o instanceof Long ||
                                o instanceof Short ||
                                o instanceof String) {
                            current.put(o);
                        } else {
                            JSONObject childObject = new JSONObject();
                            convertToJSON(childObject, o);
                            current.put(childObject);
                        }
                    }
                    root.put(field.getName(), current);
                } else {
                    root.put(field.getName(), fieldObject);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

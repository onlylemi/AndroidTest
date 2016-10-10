package com.onlylemi.plugin.host;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * ClassInject
 *
 * @author qijianbin
 * @time 2016-10-9 15:34
 */
public class ClassInject {

    /**
     * 将 DexClassLoader 的 PathList 注入到 PathClassLoader 中去
     *
     * @param pathLoader
     * @param dexLoader
     */
    public void inject(PathClassLoader pathLoader, DexClassLoader dexLoader) {
        try {
            // 1、获得 PathList
            Object pathLoaderPathList = getPathList(pathLoader);
            Object dexLoaderPathList = getPathList(dexLoader);

            // 2、获得 DexElements
            Object pathDexElements = getDexElements(pathLoaderPathList);
            Object dexDexElement = getDexElements(dexLoaderPathList);

            // 3、合并为新的 DexElements
            Object dexElements = combineArray(pathDexElements, dexDexElement);

            // 4、注入回 pathLoader 的 PathList 中
            setField(pathLoaderPathList, pathLoaderPathList.getClass(), "dexElements", dexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getPathList(Object loader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(loader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    private Object getDexElements(Object paramObj) throws NoSuchFieldException, IllegalAccessException {
        return getField(paramObj, paramObj.getClass(), "dexElements");
    }

    /**
     * 获得 obj 对象名为 fieldName 的变量的值
     *
     * @param obj
     * @param classObj
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Object getField(Object obj, Class<?> classObj, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field localField = classObj.getDeclaredField(fieldName);
        localField.setAccessible(true);
        return localField.get(obj);
    }

    private void setField(Object obj, Class<?> classObj, String field, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field localField = classObj.getDeclaredField(field);
        localField.setAccessible(true);
        localField.set(obj, value);
    }

    /**
     * 合并两个数组
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private Object combineArray(Object arr1, Object arr2) {
        Class<?> localClass = arr1.getClass().getComponentType();
        int len1 = Array.getLength(arr1);
        int lengthSum = len1 + Array.getLength(arr2);

        Object result = Array.newInstance(localClass, lengthSum);
        for (int i = 0; i < lengthSum; i++) {
            if (i < len1) {
                Array.set(result, i, Array.get(arr1, i));
            } else {
                Array.set(result, i, Array.get(arr2, i - len1));
            }
        }
        return result;
    }
}

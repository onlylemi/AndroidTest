// IRemoteService.aidl
package com.onlylemi.test11_aidl;

// Declare any non-default types here with import statements

interface IRemoteService {

    int getPid();

    String getMsg();

    void setMsg(String msg);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}

// IpcBridge.aidl
package com.ooftf.service;

// Declare any non-default types here with import statements

interface IpcBridge {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void requestByte(String name, inout byte[] data);
    void requestString(String name, String data);
    //void request(String name, String data);
}

package com.ooftf.hihttp.sample;

/**
 * 这是一个测试类，用于测试类的各个部分调用顺序
 */
public class Testa {
    static String staticS = "sss";
    static String staticSS = "sssss";
    String ssss = "dfs";

    public Testa() {
        super();

        staticS = "";

    }

    {
        ssss = "dfsdf";
    }

    static {
        staticSS = "ssss";
    }

    public static void main(String[] args) {
        Testa test = new Testa();
    }
}

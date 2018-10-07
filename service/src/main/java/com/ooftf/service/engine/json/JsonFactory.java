package com.ooftf.service.engine.json;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/6 0006
 */
public class JsonFactory {
    /**
     * 引入 builder 为了方便后续添加参数
     */
    static class Builder {
        IJson build() {
            return new Gson();
        }
    }

    private static IJson json = new Builder().build();

    public static IJson getDefault() {
        return json;
    }
}

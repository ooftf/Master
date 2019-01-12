package com.master.kit.application;

import com.ooftf.service.base.BaseApplication;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.iocanary.IOCanaryPlugin;
import com.tencent.matrix.iocanary.config.IOConfig;
import com.tencent.mrs.plugin.IDynamicConfig;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/3 0003
 */
public class App extends BaseApplication {
    private static App instance;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMatrix();
    }

    private void initMatrix() {
        // build matrix
        Matrix.Builder builder = new Matrix.Builder(this);
        // add general pluginListener
        builder.patchListener(new MasterPluginListener(this));
        // dynamic config
        IDynamicConfig dynamicConfig = new MasterDynamicConfig();

        // init plugin
        IOCanaryPlugin ioCanaryPlugin = new IOCanaryPlugin(new IOConfig.Builder()
                .dynamicConfig(dynamicConfig)
                .build());
        //add to matrix
        builder.plugin(ioCanaryPlugin);

        //init matrix
        Matrix.init(builder.build());

        // start plugin
        ioCanaryPlugin.start();
    }

    public static App getInstance() {
        return instance;
    }
}

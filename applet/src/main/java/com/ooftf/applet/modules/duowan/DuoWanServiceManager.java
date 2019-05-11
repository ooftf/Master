package com.ooftf.applet.modules.duowan;

import com.ooftf.hihttp.engine.ServiceGenerator;

public class DuoWanServiceManager {
    public static DuoWanApiService duoWanApiService = genDuoWanService();

    private static DuoWanApiService genDuoWanService() {
        ServiceGenerator serviceGenerator = new ServiceGenerator();
        serviceGenerator.setBaseUrl(Constants.BASE_URL);
        serviceGenerator.setLoggable(true);
        return serviceGenerator.createService(DuoWanApiService.class);
    }

    public static DuoWanApiService getDuoWanApiService() {
        return duoWanApiService;
    }
}

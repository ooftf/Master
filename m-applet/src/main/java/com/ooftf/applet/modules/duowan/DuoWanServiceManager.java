package com.ooftf.applet.modules.duowan;

import com.ooftf.hihttp.engine.ServiceGeneratorBuilder;

public class DuoWanServiceManager {
    public static DuoWanApiService duoWanApiService = genDuoWanService();

    private static DuoWanApiService genDuoWanService() {

        return  new ServiceGeneratorBuilder().setBaseUrl(Constants.BASE_URL).build().createService(DuoWanApiService.class);
    }

    public static DuoWanApiService getDuoWanApiService() {
        return duoWanApiService;
    }
}

package com.ooftf.master.sign.net;

import com.ooftf.service.net.ServiceHolder;

public class SignMobServiceHolder {
    public static SignMobService signMobService = ServiceHolder.INSTANCE.mobServiceGenerator().createService(SignMobService.class);
}

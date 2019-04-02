package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.assist.ISignService;
import com.ooftf.service.engine.router.assist.SignChannelInfo;
import com.ooftf.service.engine.router.service.IMultiSignService;
import com.ooftf.service.engine.typer.TyperFactory;
import com.ooftf.service.utils.Collections;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import kotlin.jvm.functions.Function0;


/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/18 0018
 */
@Route(path = ServiceMap.MULTI_ACCOUNT, name = "测试服务")
@DebugLog
public class MultiSignManager implements IMultiSignService {
    public static final String TYPER_KEY_CURRENT_ACCOUNT_ID = "typer_key_current_account_id";
    private static List<ChannelInfo> accountInfos = new ArrayList<>();

    static {
        accountInfos.add(new ChannelInfo(AccountId.GOOGLE, "Google", FireSignServiceImpl::getInstance));
        accountInfos.add(new ChannelInfo(AccountId.MOB, "Mob", SignServiceImpl::getInstance));
    }

    @Override
    public ISignService getCurrentService() {
        return getCurrentChannelInfo().getCreator().invoke();
    }


    @Override
    public List<SignChannelInfo> getAllChannel() {
        List<SignChannelInfo> result = new ArrayList<>();
        Collections.copy(accountInfos, result);
        return result;
    }

    @Override
    public void switchToChannel(String id) {
        TyperFactory.getDefault().put(TYPER_KEY_CURRENT_ACCOUNT_ID, id);
    }

    public ChannelInfo getCurrentChannelInfo() {
        ChannelInfo channelInfo = getChannelInfo(getCurrentChannelId());
        if (channelInfo != null) {
            return channelInfo;
        }
        return accountInfos.get(0);
    }

    public ChannelInfo getChannelInfo(String channelId) {
        for (ChannelInfo info : accountInfos) {
            if (channelId.equals(info.getId())) {
                return info;
            }
        }
        return null;
    }

    @Override
    public String getCurrentChannelId() {
        return TyperFactory.getDefault().getString(TYPER_KEY_CURRENT_ACCOUNT_ID, AccountId.GOOGLE);
    }

    @Override
    public String getChannelName(String channelId) {
        return getChannelInfo(channelId).getName();
    }

    @Override
    public ISignService getService(String channelId) {
        return null;
    }

    @Override
    public void init(Context context) {

    }

    interface AccountId {
        String GOOGLE = "google";
        String MOB = "mob";
    }

    public static class ChannelInfo extends SignChannelInfo {
        public ChannelInfo() {
        }

        public ChannelInfo(String id, String name, Function0<ISignService> creator) {
            super(id, name);
            this.creator = creator;
        }

        Function0<ISignService> creator;

        public Function0<ISignService> getCreator() {
            return creator;
        }

        public ChannelInfo setCreator(Function0<ISignService> creator) {
            this.creator = creator;
            return this;
        }
    }
}

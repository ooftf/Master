//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsLogReport.EventType;
import com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CookieManager {
    public static String LOGTAG = "CookieManager";
    private static CookieManager d;
    ArrayList<CookieManager.b> a;
    String b;
    CookieManager.a c;
    private boolean e;
    private boolean f;

    private CookieManager() {
        this.c = CookieManager.a.a;
        this.e = false;
        this.f = false;
    }

    public static CookieManager getInstance() {
        if (null == d) {
            Class var0 = CookieManager.class;
            synchronized(CookieManager.class) {
                if (null == d) {
                    d = new CookieManager();
                }
            }
        }

        return d;
    }

    public void removeSessionCookie() {
        bt var1 = bt.a();
        if (null != var1 && var1.b()) {
            DexLoader var2 = var1.c().b();
            var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        } else {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        }

    }

    public void removeSessionCookies(ValueCallback<Boolean> var1) {
        bt var2 = bt.a();
        if (null != var2 && var2.b()) {
            DexLoader var3 = var2.c().b();
            var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, new Object[]{var1});
        } else {
            if (VERSION.SDK_INT < 21) {
                return;
            }

            q.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, new Object[]{var1});
        }

    }

    public void removeAllCookie() {
        if (this.a != null) {
            this.a.clear();
        }

        bt var1 = bt.a();
        if (null != var1 && var1.b()) {
            var1.c().e();
        } else {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        }

    }

    public void removeAllCookies(ValueCallback<Boolean> var1) {
        if (this.a != null) {
            this.a.clear();
        }

        bt var2 = bt.a();
        if (null != var2 && var2.b()) {
            DexLoader var3 = var2.c().b();
            var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, new Object[]{var1});
        } else {
            if (VERSION.SDK_INT < 21) {
                return;
            }

            q.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, new Object[]{var1});
        }

    }

    public void flush() {
        bt var1 = bt.a();
        if (null != var1 && var1.b()) {
            DexLoader var2 = var1.c().b();
            var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else {
            if (VERSION.SDK_INT < 21) {
                return;
            }

            q.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }

    }

    public void removeExpiredCookie() {
        bt var1 = bt.a();
        if (null != var1 && var1.b()) {
            DexLoader var2 = var1.c().b();
            var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        } else {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        }

    }

    public synchronized void setAcceptCookie(boolean var1) {
        bt var2 = bt.a();
        if (null != var2 && var2.b()) {
            DexLoader var3 = var2.c().b();
            var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, new Object[]{var1});
        } else {
            try {
                android.webkit.CookieManager.getInstance().setAcceptCookie(var1);
            } catch (Throwable var4) {
                var4.printStackTrace();
            }
        }

    }

    public synchronized void setAcceptThirdPartyCookies(WebView var1, boolean var2) {
        bt var3 = bt.a();
        if (null != var3 && var3.b()) {
            DexLoader var4 = var3.c().b();
            var4.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, new Object[]{var1.getView(), var2});
        } else {
            if (VERSION.SDK_INT < 21) {
                return;
            }

            q.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{android.webkit.WebView.class, Boolean.TYPE}, new Object[]{var1.getView(), var2});
        }

    }

    public synchronized boolean acceptThirdPartyCookies(WebView var1) {
        bt var2 = bt.a();
        if (null != var2 && var2.b()) {
            DexLoader var5 = var2.c().b();
            Object var4 = var5.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, new Object[]{var1.getView()});
            return var4 != null ? (Boolean)var4 : true;
        } else if (VERSION.SDK_INT < 21) {
            return true;
        } else {
            Object var3 = q.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{android.webkit.WebView.class}, new Object[]{var1.getView()});
            return var3 != null ? (Boolean)var3 : false;
        }
    }

    public synchronized void setCookie(String var1, String var2) {
        this.setCookie(var1, var2, false);
    }

    public synchronized void setCookie(String var1, String var2, boolean var3) {
        bt var4 = bt.a();
        if (null != var4 && var4.b()) {
            DexLoader var6 = var4.c().b();
            var6.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, new Object[]{var1, var2});
        } else {
            if (this.f || var3) {
                android.webkit.CookieManager.getInstance().setCookie(var1, var2);
            }

            if (!bt.a().d()) {
                CookieManager.b var5 = new CookieManager.b();
                var5.a = 2;
                var5.b = var1;
                var5.c = var2;
                var5.d = null;
                if (this.a == null) {
                    this.a = new ArrayList();
                }

                this.a.add(var5);
            }
        }

    }

    public synchronized void setCookie(String var1, String var2, ValueCallback<Boolean> var3) {
        bt var4 = bt.a();
        if (null != var4 && var4.b()) {
            DexLoader var6 = var4.c().b();
            var6.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, new Object[]{var1, var2, var3});
        } else {
            if (!bt.a().d()) {
                CookieManager.b var5 = new CookieManager.b();
                var5.a = 1;
                var5.b = var1;
                var5.c = var2;
                var5.d = var3;
                if (this.a == null) {
                    this.a = new ArrayList();
                }

                this.a.add(var5);
            }

            if (this.f) {
                if (VERSION.SDK_INT < 21) {
                    return;
                }

                q.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, new Object[]{var1, var2, var3});
            }
        }

    }

    public boolean hasCookies() {
        bt var1 = bt.a();
        return null != var1 && var1.b() ? var1.c().h() : android.webkit.CookieManager.getInstance().hasCookies();
    }

    public boolean acceptCookie() {
        bt var1 = bt.a();
        return null != var1 && var1.b() ? var1.c().d() : android.webkit.CookieManager.getInstance().acceptCookie();
    }

    public String getCookie(String var1) {
        bt var2 = bt.a();
        if (null != var2 && var2.b()) {
            return var2.c().a(var1);
        } else {
            String var3 = null;

            try {
                var3 = android.webkit.CookieManager.getInstance().getCookie(var1);
            } catch (Throwable var5) {
                var5.printStackTrace();
            }

            return var3;
        }
    }

    public void setCookies(Map<String, String[]> var1) {
        boolean var2 = false;
        bt var3 = bt.a();
        if (null != var3 && var3.b()) {
            var2 = var3.c().a(var1);
        }

        if (!var2) {
            Iterator var4 = var1.keySet().iterator();

            while(var4.hasNext()) {
                String var5 = (String)var4.next();
                String[] var6 = (String[])var1.get(var5);
                int var7 = var6.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String var9 = var6[var8];
                    this.setCookie(var5, var9);
                }
            }
        }

    }

    synchronized void a() {
        this.f = true;
        if (this.a != null && this.a.size() != 0) {
            bt var1 = bt.a();
            Iterator var2;
            CookieManager.b var3;
            if (null != var1 && var1.b()) {
                var2 = this.a.iterator();

                while(var2.hasNext()) {
                    var3 = (CookieManager.b)var2.next();
                    switch(var3.a) {
                    case 1:
                        this.setCookie(var3.b, var3.c, var3.d);
                        break;
                    case 2:
                        this.setCookie(var3.b, var3.c);
                    }
                }
            } else {
                var2 = this.a.iterator();

                while(var2.hasNext()) {
                    var3 = (CookieManager.b)var2.next();
                    switch(var3.a) {
                    case 1:
                        if (VERSION.SDK_INT >= 21) {
                            q.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, new Object[]{var3.b, var3.c, var3.d});
                        }
                        break;
                    case 2:
                        android.webkit.CookieManager.getInstance().setCookie(var3.b, var3.c);
                    }
                }
            }

            this.a.clear();
        }
    }

    public boolean setCookieCompatialbeMode(Context var1, CookieManager.a var2, String var3, boolean var4) {
        long var5 = System.currentTimeMillis();
        if (var1 != null && TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "cookie_switch.txt")) {
            this.c = var2;
            if (var3 != null) {
                this.b = var3;
            }

            if (this.c != CookieManager.a.a && var4 && !bt.a().d()) {
                bt.a().a(var1);
            }

            return true;
        } else {
            return false;
        }
    }

    protected synchronized void a(Context var1, boolean var2, boolean var3) {
        if (this.c != CookieManager.a.a && var1 != null && TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "cookie_switch.txt") && !this.e) {
            long var4 = System.currentTimeMillis();
            long var6 = 0L;
            TbsLog.i(LOGTAG, "compatiableCookieDatabaseIfNeed,isX5Inited:" + var2 + ",useX5:" + var3);
            if (!var2 && !QbSdk.getIsSysWebViewForcedByOuter() && !QbSdk.a) {
                bt var16 = bt.a();
                var16.a(var1);
            } else {
                if (QbSdk.getIsSysWebViewForcedByOuter() || QbSdk.a) {
                    var3 = false;
                }

                boolean var8 = TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "usex5.txt");
                TbsLog.i(LOGTAG, "usex5 : mUseX5LastProcess->" + var8 + ",useX5:" + var3);
                TbsExtensionFunctionManager.getInstance().setFunctionEnable(var1, "usex5.txt", var3);
                if (var8 != var3) {
                    int var9 = 0;
                    int var10 = 0;
                    boolean var11 = false;
                    TbsLogInfo var12 = TbsLogReport.a(var1).a();
                    if (!TextUtils.isEmpty(this.b)) {
                        if (am.a().m(var1) > 0 && am.a().m(var1) < 36001) {
                            return;
                        }

                        if (var8) {
                            var9 = x.d(var1);
                            if (var9 > 0) {
                                var10 = getROMCookieDBVersion(var1);
                                if (var10 <= 0) {
                                    var11 = true;
                                }
                            }
                        } else {
                            var9 = x.d(var1);
                            if (var9 > 0) {
                                String var13 = am.a().c(var1, "cookies_database_version");
                                if (!TextUtils.isEmpty(var13)) {
                                    try {
                                        var10 = Integer.parseInt(var13);
                                    } catch (Exception var15) {
                                        ;
                                    }
                                }
                            }
                        }

                        if (!var11 && (var9 <= 0 || var10 <= 0)) {
                            var12.setErrorCode(702);
                        } else if (var10 >= var9) {
                            var12.setErrorCode(703);
                        } else {
                            x.a(var1, this.c, this.b, var11, var3);
                            var12.setErrorCode(704);
                            var6 = System.currentTimeMillis() - var4;
                        }
                    } else {
                        var12.setErrorCode(701);
                    }

                    var12.setFailDetail("x5->sys:" + var8 + " from:" + var9 + " to:" + var10 + ",timeused:" + var6);
                    TbsLogReport.a(var1).a(EventType.TYPE_COOKIE_DB_SWITCH, var12);
                }
            }
        }
    }

    public static int getROMCookieDBVersion(Context var0) {
        SharedPreferences var1;
        if (VERSION.SDK_INT >= 11) {
            var1 = var0.getSharedPreferences("cookiedb_info", 4);
        } else {
            var1 = var0.getSharedPreferences("cookiedb_info", 0);
        }

        return var1.getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context var0, int var1) {
        SharedPreferences var2 = null;
        Editor var3 = null;
        if (VERSION.SDK_INT >= 11) {
            var2 = var0.getSharedPreferences("cookiedb_info", 4);
        } else {
            var2 = var0.getSharedPreferences("cookiedb_info", 0);
        }

        var3 = var2.edit();
        var3.putInt("db_version", var1);
        var3.commit();
    }

    class b {
        int a;
        String b;
        String c;
        ValueCallback<Boolean> d;

        b() {
        }
    }

    public static enum a {
        a,
        b,
        c;

        private a() {
        }
    }
}

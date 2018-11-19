package com.ss.android.ugc.aweme.app.astispam;

import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.bytedance.ies.geckoclient.C9002o;
import com.bytedance.ies.net.http.Api;
import com.ss.android.common.applog.EagleEye;
import com.ss.android.common.applog.GlobalContext;
import com.ss.android.common.applog.HotsoonReceiver;
import com.ss.android.common.applog.NetUtil;
import com.ss.android.common.applog.UserInfo;
import com.ss.android.common.http.IProcesessUrl;
import com.ss.android.common.util.C0721h;
import com.ss.android.common.util.NetworkUtils;
import com.ss.android.deviceregister.DeviceRegisterManager;
import com.ss.android.http.legacy.message.C1041f;
import com.ss.android.product.I18nController;
import com.ss.android.ugc.aweme.app.AwemeApplication;
import com.ss.android.ugc.aweme.app.api.C2002b;
import com.ss.android.ugc.aweme.app.application.C2060t;
import com.ss.android.ugc.aweme.app.application.C9733a;
import com.ss.sys.ces.out.C10663a;
import com.ss.sys.ces.out.ISdk;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.ss.android.ugc.aweme.app.astispam.b */
class C2071b implements Antispam {
    /* renamed from: a */
    private AtomicReference<IProcesessUrl> f5649a = new AtomicReference(null);
    /* renamed from: b */
    private volatile boolean f5650b = false;
    /* renamed from: c */
    private volatile String f5651c;
    /* renamed from: d */
    private volatile String f5652d;
    /* renamed from: e */
    private volatile String f5653e;
    /* renamed from: f */
    private List<String> f5654f = new ArrayList();
    /* renamed from: g */
    private volatile boolean f5655g = false;
    /* renamed from: h */
    private final Object[] f5656h = new Object[0];

    /* renamed from: com.ss.android.ugc.aweme.app.astispam.b$a */
    private static class C2070a implements IProcesessUrl {
        private C2070a() {
        }

        static {
            C9002o.loadLibrary(GlobalContext.getContext(), "cms");
            C9002o.loadLibrary(GlobalContext.getContext(), "userinfo");
        }

        public String getUrl(String url, List<C1041f> postParams, boolean skip) {
            String str = "";
            synchronized (C2070a.class) {
                str = m3533a(url, postParams, false, null);
            }
            return str;
        }

        /* renamed from: a */
        private synchronized String m3533a(String str, List<C1041f> list, boolean z, Integer num) {
            if (!TextUtils.isEmpty(str)) {
                int i;
                String str2;
                int serverTime = num == null ? NetworkUtils.getServerTime() : num.intValue();
                if (serverTime < 0) {
                    i = 0;
                } else {
                    i = serverTime;
                }
                if (num == null) {
                    str = str + "&ts=" + i;
                }
                int i2;
                String str3;
                if (list == null) {
                    Map hashMap = new HashMap();
                    NetUtil.putCommonParams(hashMap, true);
                    if (hashMap.containsKey("_rticket")) {
                        hashMap.remove("_rticket");
                    }
                    String[] strArr = new String[(hashMap.size() * 2)];
                    i2 = 0;
                    for (String str22 : hashMap.keySet()) {
                        str3 = (String) hashMap.get(str22);
                        if (str22 == null) {
                            str22 = "";
                        }
                        if (str3 == null) {
                            str3 = "";
                        }
                        int i3 = i2 + 1;
                        strArr[i2] = str22;
                        int i4 = i3 + 1;
                        strArr[i3] = str3;
                        i2 = i4;
                    }
                    if (z) {
                        str22 = UserInfo.getUserInfoSkipGet(i, str, strArr);
                    } else {
                        str22 = DeviceRegisterManager.getDeviceId();
                        if (str.contains("&device_id=") || str.contains("?device_id=")) {
                            if (TextUtils.isEmpty(str22)) {
                                str22 = (String) C2002b.URLRequest(str).get("device_id");
                            }
                            str22 = UserInfo.getUserInfo(i, URLDecoder.decode(str), strArr, str22);
                        } else {
                            str22 = UserInfo.getUserInfo(i, URLDecoder.decode(str), strArr, "");
                        }
                    }
                } else {
                    String[] strArr2 = new String[(list.size() * 2)];
                    i2 = 0;
                    for (C1041f c1041f : list) {
                        str3 = c1041f.getName();
                        str22 = c1041f.getValue();
                        if (str3 == null) {
                            str3 = "";
                        }
                        if (str22 == null) {
                            str22 = "";
                        }
                        int i5 = i2 + 1;
                        strArr2[i2] = str3;
                        serverTime = i5 + 1;
                        strArr2[i5] = str22;
                        i2 = serverTime;
                    }
                    if (z) {
                        str22 = UserInfo.getUserInfoSkipGet(i, URLDecoder.decode(str), strArr2);
                    } else {
                        str22 = DeviceRegisterManager.getDeviceId();
                        if (str.contains("&device_id=") || str.contains("?device_id=")) {
                            if (TextUtils.isEmpty(str22)) {
                                str22 = (String) C2002b.URLRequest(str).get("device_id");
                            }
                            str22 = UserInfo.getUserInfo(i, URLDecoder.decode(str), strArr2, str22);
                        } else {
                            str22 = UserInfo.getUserInfo(i, URLDecoder.decode(str), strArr2, "");
                        }
                    }
                }
                if (TextUtils.isEmpty(str22)) {
                    str22 = str + "&as=a1iosdfgh&cp=androide1";
                } else {
                    serverTime = str22.length();
                    if (serverTime % 2 == 0) {
                        String substring = str22.substring(0, serverTime >> 1);
                        ISdk sdk = C10663a.getSDK(GlobalContext.getContext(), (long) AwemeApplication.getInst().getAid());
                        sdk.setSession(C2060t.getSessionId());
                        str22 = (str + "&as=" + substring + "&cp=" + str22.substring(serverTime >> 1, serverTime)) + "&mas=" + EagleEye.byteArrayToHexStr(sdk.encode(substring.getBytes()));
                    } else {
                        str22 = str + "&as=a1qwert123&cp=cbfhckdckkde1";
                    }
                }
                if (z && list != null) {
                    C0721h c0721h = new C0721h(str22);
                    for (C1041f c1041f2 : list) {
                        c0721h.addParam(c1041f2.getName(), c1041f2.getValue());
                    }
                    str22 = c0721h.toString();
                }
                str = str22;
            }
            return str;
        }
    }

    C2071b() {
    }

    public void init(Context context) {
        Api.setIProcesessUrl(new C2072c(this));
    }

    /* renamed from: a */
    final /* synthetic */ String m3536a(String str, List list, boolean z) {
        m3534a();
        return ((IProcesessUrl) this.f5649a.get()).getUrl(str, list, z);
    }

    public void initCanDelay(Context context, String estr) {
        m3534a();
        ISdk sdk = C10663a.getSDK(GlobalContext.getContext(), (long) AwemeApplication.getInst().getAid());
        if (sdk != null) {
            if (I18nController.isMusically()) {
                sdk.SetRegionType(3);
            } else {
                sdk.SetRegionType(2);
            }
        }
        UserInfo.setAppId(C9733a.AID);
        if (TextUtils.isEmpty(estr)) {
            estr = "a3668f0afac72ca3f6c1697d29e0e1bb1fef4ab0285319b95ac39fa42c38d05f";
        }
        UserInfo.initUser(estr);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.INSTALL_PACKAGE");
        context.registerReceiver(new HotsoonReceiver(), intentFilter);
        m3535a(context);
    }

    /* renamed from: a */
    private void m3534a() {
        if (this.f5649a.get() == null) {
            this.f5649a.compareAndSet(null, new C2070a());
        }
    }

    /* renamed from: a */
    private void m3535a(Context context) {
        synchronized (this.f5656h) {
            this.f5650b = true;
            String str = this.f5651c;
            String str2 = this.f5652d;
            String str3 = this.f5653e;
            List<String> arrayList = new ArrayList(this.f5654f);
            boolean z = this.f5655g;
            this.f5651c = null;
            this.f5652d = null;
            this.f5653e = null;
            this.f5654f.clear();
            this.f5655g = false;
        }
        if (str != null) {
            updateEstr(str);
        }
        if (!(str2 == null && str3 == null)) {
            updateDeviceId(str2, str3);
        }
        if (!arrayList.isEmpty()) {
            for (String str4 : arrayList) {
                report(context, str4);
            }
        }
        if (z) {
            initEagleEye(context);
        }
    }

    public void initEagleEye(Context context) {
        synchronized (this.f5656h) {
            if (this.f5650b) {
                EagleEye.init(context);
                return;
            }
            this.f5655g = true;
        }
    }

    public void uninitEagleEye(Context context) {
        synchronized (this.f5656h) {
            if (this.f5650b) {
                EagleEye.uninit(context);
                return;
            }
            this.f5655g = false;
        }
    }

    public void updateEstr(String estr) {
        synchronized (this.f5656h) {
            if (this.f5650b) {
                UserInfo.initUser(estr);
                return;
            }
            if (TextUtils.isEmpty(estr)) {
                estr = "a3668f0afac72ca3f6c1697d29e0e1bb1fef4ab0285319b95ac39fa42c38d05f";
            }
            this.f5651c = estr;
        }
    }

    public void updateDeviceId(String did, String iid) {
        synchronized (this.f5656h) {
            if (this.f5650b) {
                C10663a.getSDK(GlobalContext.getContext(), (long) AwemeApplication.getInst().getAid()).setParams(did, iid);
                return;
            }
            this.f5652d = did;
            this.f5653e = iid;
        }
    }

    public String getUrl(String url, List<String> postParams, int serverTime) {
        List arrayList = new ArrayList();
        if (postParams != null) {
            for (int i = 0; i < postParams.size(); i += 2) {
                if (i != postParams.size() - 1) {
                    arrayList.add(new C1041f((String) postParams.get(i), (String) postParams.get(i + 1)));
                }
            }
        }
        m3534a();
        return ((C2070a) this.f5649a.get()).m3533a(url, arrayList, false, Integer.valueOf(serverTime));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void report(android.content.Context r3, java.lang.String r4) {
        /*
        r2 = this;
        r1 = r2.f5656h;
        monitor-enter(r1);
        r0 = r2.f5650b;	 Catch:{ all -> 0x0026 }
        if (r0 != 0) goto L_0x000e;
    L_0x0007:
        r0 = r2.f5654f;	 Catch:{ all -> 0x0026 }
        r0.add(r4);	 Catch:{ all -> 0x0026 }
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
    L_0x000d:
        return;
    L_0x000e:
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        r0 = com.ss.android.ugc.aweme.app.AwemeApplication.getInst();
        r0 = r0.getAid();
        r0 = (long) r0;
        r0 = com.ss.sys.ces.out.C10663a.getSDK(r3, r0);
        r1 = android.text.TextUtils.isEmpty(r4);
        if (r1 != 0) goto L_0x0029;
    L_0x0022:
        r0.reportNow(r4);
        goto L_0x000d;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        throw r0;
    L_0x0029:
        r1 = "";
        r0.reportNow(r1);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.ugc.aweme.app.astispam.b.report(android.content.Context, java.lang.String):void");
    }
}

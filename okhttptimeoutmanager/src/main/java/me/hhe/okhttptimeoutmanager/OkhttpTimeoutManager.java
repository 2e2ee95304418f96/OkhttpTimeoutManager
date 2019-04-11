package me.hhe.okhttptimeoutmanager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/10
 * discription:
 */
public class OkhttpTimeoutManager {
    private static class OkhttpTimeoutManagerHolder {
        private static final OkhttpTimeoutManager sOkhttpTimeoutManager = new OkhttpTimeoutManager();
    }

    public static OkhttpTimeoutManager getInstance() {
        return OkhttpTimeoutManagerHolder.sOkhttpTimeoutManager;
    }

    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        Interceptor interceptor=new TimeoutInterceptor(builder);
        return builder.addInterceptor(interceptor);
    }
}

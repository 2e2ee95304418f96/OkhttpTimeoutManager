package me.hhe.okhttptimeoutmanager;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/10
 * discription:
 */
public class TimeoutInterceptor implements Interceptor {

    private final MyDns mMyDns;

    private int connectTimeout;
    private int readTimeout;
    private int writeTimeout;
    private int dnsTimeout;

    public TimeoutInterceptor(OkHttpClient.Builder okhttpBuilder) {
        mMyDns = new MyDns();
        okhttpBuilder.dns(mMyDns);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        initTimeSetting(chain);

        //移除无用header
        Request.Builder builder = chain.request().newBuilder();
        builder.removeHeader(TimeoutType.CONNECT);
        builder.removeHeader(TimeoutType.READ);
        builder.removeHeader(TimeoutType.WRITE);
        builder.removeHeader(TimeoutType.DNS);

        //动态设置dns超时时间
        mMyDns.setDnsTimeout(dnsTimeout);

        Response response = chain
                .withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .proceed(builder.build());
        return response;
    }

    /**
     * 从配置初始化各个超时时间
     *
     * @param chain
     */
    private void initTimeSetting(Chain chain) {
        Request request = chain.request();

        String allNew = request.header(TimeoutType.ALL);
        if (!TextUtils.isEmpty(allNew)) {
            //如果配置了allTimeout，则忽略其他所有配置，以这个为准
            assert allNew != null;
            int allTimeout = Integer.valueOf(allNew);
            connectTimeout = allTimeout;
            readTimeout = allTimeout;
            writeTimeout = allTimeout;
            dnsTimeout = allTimeout;
            return;
        }

        String connectNew = request.header(TimeoutType.CONNECT);
        String readNew = request.header(TimeoutType.READ);
        String writeNew = request.header(TimeoutType.WRITE);
        String dnsNew = request.header(TimeoutType.DNS);

        connectTimeout = chain.connectTimeoutMillis();
        readTimeout = chain.readTimeoutMillis();
        writeTimeout = chain.writeTimeoutMillis();
        //默认和连接超时时间一致
        dnsTimeout = connectTimeout;

        if (!TextUtils.isEmpty(connectNew)) {
            connectTimeout = Integer.valueOf(connectNew);
        }
        if (!TextUtils.isEmpty(readNew)) {
            readTimeout = Integer.valueOf(readNew);
        }
        if (!TextUtils.isEmpty(writeNew)) {
            writeTimeout = Integer.valueOf(writeNew);
        }
        if (!TextUtils.isEmpty(dnsNew)) {
            dnsTimeout = Integer.valueOf(dnsNew);
        }
    }
}

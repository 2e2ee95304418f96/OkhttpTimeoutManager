package me.hhe.okhttp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.hhe.okhttptimeoutmanager.OkhttpTimeoutManager;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/11
 * discription:
 */
public class HttpUtils {
    private Api mApi;

    private HttpUtils() {
        initRetrofit();

    }

    private static class HttpUtilsHolder {

        private static final HttpUtils sHttpUtils = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return HttpUtilsHolder.sHttpUtils;
    }


    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求的Url地址
                .baseUrl("https://www.google.com/")
                .client(getOkhttpClient())
                .addConverterFactory(new ToStringConverterFactory())
                .build();
        // 创建网络请求接口的实例
        mApi = retrofit.create(Api.class);
    }

    private OkHttpClient getOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        OkhttpTimeoutManager.getInstance().with(builder);

        List specList = new ArrayList<>();
        specList.add(ConnectionSpec.COMPATIBLE_TLS);
        specList.add(ConnectionSpec.CLEARTEXT);

        builder.connectionSpecs(specList);
        return builder.build();

    }

    public Api getApi() {
        return mApi;
    }
}

package me.hhe.okhttp;

import me.hhe.okhttptimeoutmanager.TimeoutType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/11
 * discription:
 */
public interface Api {
    @GET("/")
    Call<String> getTestContent();

    @GET("/")
    @Headers({TimeoutType.ALL+":30000"})
    Call<String> timeoutAll();

    @GET("/")
    @Headers({TimeoutType.DNS+":3000",TimeoutType.CONNECT+":3000",TimeoutType.READ+":3000",TimeoutType.WRITE+":3000"})
    Call<String> timeoutApart();
}

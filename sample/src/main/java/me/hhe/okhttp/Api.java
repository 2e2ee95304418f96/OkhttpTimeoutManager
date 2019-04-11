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
    @Headers({TimeoutType.ALL+":3000"})
    Call<String> timeoutAll();
    @GET("/")
    @Headers({TimeoutType.DNS+":1000",TimeoutType.CONNECT+":1000",TimeoutType.READ+":1000",TimeoutType.WRITE+":1000"})
    Call<String> timeoutApart();
}

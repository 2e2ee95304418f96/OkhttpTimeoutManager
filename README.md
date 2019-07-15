# OkhttpTimeoutManager
Solve the problem that set Okhttp timeout sometimes not work (acturally DNS not in control). Let Okhttp specially Retrofit support that you can set timeout time of any request dynamicly just with one line of code, include "dns timeout","connect timeout","read timeout" and "write timeout". 

用一行代码动态控制okhttp接口的超时时间，配合Retrofit使用。解决了okhttp配置timeout无效的问题，实际是因DNS查询超时，而Okhttp不支持直接配置DNS超时时间。



## Demo

[demo.apk](https://raw.githubusercontent.com/63f8eeebc79427ee/OkhttpTimeoutManager/master/sample/demo.apk)

## Download

```groovy
implementation 'me.hhe:okhttptimeoutmanager:1.0.0'
```

## Usage

If your library of network is OkHttp&Retrofit

add @Headers in the interface.

1. Quick set **all the type** of timeout include "dns timeout","connect timeout","read timeout","wirte timeout".

```java
@GET("/")
@Headers({TimeoutType.ALL+":30000"})
Call<String> timeoutAll();
```

2. **Apartly** or **combinedly** set the type of timeout.

   ```java
   @GET("/") 
   @Headers({TimeoutType.DNS+":3000"})
   Call<String> timeoutApart();
   ```

   ```java
   @GET("/") 
   @Headers({TimeoutType.DNS+":3000",TimeoutType.CONNECT+":3000"})
   Call<String> timeoutApart();
   ```


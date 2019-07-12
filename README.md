# OkhttpTimeoutManager
Solve the problem that set Okhttp timeout not work (acturally DNS not in control). Let Okhttp specially Retrofit support modify timeout time of any request dynamicly just with one row of code. 用一行代码动态控制okhttp接口的超时时间，配合Retrofit使用。解决了okhttp配置timeout无效的问题，实际是因DNS查询超时，而Okhttp不支持直接配置DNS超时时间。

# todo
1. 增加远程依赖
2. 丰富Readme

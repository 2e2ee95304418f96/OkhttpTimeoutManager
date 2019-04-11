package me.hhe.okhttptimeoutmanager;

import android.support.annotation.NonNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Dns;

/**
 * author:hhe
 * email:hhecoder@gmail.com
 * createTime:2019/4/10
 * discription:控制dns的超时时间
 */
public class MyDns implements Dns {
    private long mTimeout=15_000;

    @Override
    public List<InetAddress> lookup(@NonNull final String hostname) throws UnknownHostException {
        try {
            FutureTask<List<InetAddress>> task = new FutureTask<>(
                    new Callable<List<InetAddress>>() {
                        @Override
                        public List<InetAddress> call() throws Exception {
                            return Arrays.asList(InetAddress.getAllByName(hostname));
                        }
                    });
            //noinspection AlibabaThreadPoolCreation
            Executors.newFixedThreadPool(50).execute(task);
            return task.get(mTimeout, TimeUnit.MILLISECONDS);
        } catch (Exception var4) {
            UnknownHostException unknownHostException =
                    new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
            unknownHostException.initCause(var4);
            throw unknownHostException;
        }
    }

    public void setDnsTimeout(int dnsTimeout) {
        mTimeout=dnsTimeout;
    }

}

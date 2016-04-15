package hust.testphp.data;

import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Version;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.OkHeaders;

import java.io.IOException;
import java.net.CookieHandler;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by admin on 2016/3/11.
 */
public class okTest {

    static class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Chain chain) throws IOException {
            // 获得Request
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.i("LOG_TAG", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            // 执行request
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i("LOG_TAG", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    public static void doGet() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());

        Request request = new Request.Builder()
                .url("https://115.156.187.146/TransferServer/ServerMain.php")
                .build();

        try {
            Response response = client.newCall(request).execute();
            response.body().close();
        } catch (IOException e) {

        }
    }
}

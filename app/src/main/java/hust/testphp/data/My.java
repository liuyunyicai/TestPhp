package hust.testphp.data;

import android.graphics.BitmapFactory;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/3/10.
 */
public class My {

//
//    /**
//     * Figures out what the response source will be, and opens a socket to that
//     * source if necessary. Prepares the request headers and gets ready to start
//     * writing the request body if it exists.
//     *
//     * @throws RequestException if there was a problem with request setup. Unrecoverable.
//     * @throws RouteException if the was a problem during connection via a specific route. Sometimes
//     *     recoverable. See {@link #recover(RouteException)}.
//     * @throws IOException if there was a problem while making a request. Sometimes recoverable. See
//     *     {@link #recover(IOException)}.
//     *
//     */
//    public void sendRequest() throws RequestException, RouteException, IOException {
//        if (cacheStrategy != null) return; // Already sent.
//        if (transport != null) throw new IllegalStateException();
//
//        // 这里继续设置Request的其他信息，比如Host，Connection,Cookie等
//        Request request = networkRequest(userRequest);
//
//        // 查看缓存
//        InternalCache responseCache = Internal.instance.internalCache(client);
//        // 查找缓存中是否已经了该request想要获取的资源
//        Response cacheCandidate = responseCache != null
//                ? responseCache.get(request)
//                : null;
//
//        long now = System.currentTimeMillis();
//        // 获得缓存策略
//        cacheStrategy = new CacheStrategy.Factory(now, request, cacheCandidate).get();
//        networkRequest = cacheStrategy.networkRequest;
//        cacheResponse = cacheStrategy.cacheResponse;
//
//        if (responseCache != null) {
//            responseCache.trackResponse(cacheStrategy);
//        }
//
//        if (cacheCandidate != null && cacheResponse == null) {
//            closeQuietly(cacheCandidate.body()); // The cache candidate wasn't applicable. Close it.
//        }
//
//        // 如果需要进行网络请求，即networkRequest不为空，则进行网络操作
//        if (networkRequest != null) {
//            // Open a connection unless we inherited one from a redirect.
//            if (connection == null) {
//                // 建立连接
//                connect();
//            }
//
//            //
//            transport = Internal.instance.newTransport(connection, this);
//
//            // If the caller's control flow writes the request body, we need to create that stream
//            // immediately. And that means we need to immediately write the request headers, so we can
//            // start streaming the request body. (We may already have a request body if we're retrying a
//            // failed POST.)
//            if (callerWritesRequestBody && permitsRequestBody() && requestBodyOut == null) {
//                long contentLength = OkHeaders.contentLength(request);
//                if (bufferRequestBody) {
//                    if (contentLength > Integer.MAX_VALUE) {
//                        throw new IllegalStateException("Use setFixedLengthStreamingMode() or "
//                                + "setChunkedStreamingMode() for requests larger than 2 GiB.");
//                    }
//
//                    if (contentLength != -1) {
//                        // Buffer a request body of a known length.
//                        transport.writeRequestHeaders(networkRequest);
//                        requestBodyOut = new RetryableSink((int) contentLength);
//                    } else {
//                        // Buffer a request body of an unknown length. Don't write request
//                        // headers until the entire body is ready; otherwise we can't set the
//                        // Content-Length header correctly.
//                        requestBodyOut = new RetryableSink();
//                    }
//                } else {
//                    transport.writeRequestHeaders(networkRequest);
//                    requestBodyOut = transport.createRequestBody(networkRequest, contentLength);
//                }
//            }
//
//        } else {
//            // 声明了only-if-cached，表示不进行网络请求；如果Cache中不存在或者过期，则直接返回504
//            // We aren't using the network. Recycle a connection we may have inherited from a redirect.
//            if (connection != null) {
//                Internal.instance.recycle(client.getConnectionPool(), connection);
//                connection = null;
//            }
//
//            if (cacheResponse != null) {
//                // 如果有缓存，则直接组装成Response.
//                this.userResponse = cacheResponse.newBuilder()
//                        .request(userRequest)
//                        .priorResponse(stripBody(priorResponse))
//                        .cacheResponse(stripBody(cacheResponse))
//                        .build();
//            } else {
//                // We're forbidden from using the network, and the cache is insufficient.
//                // 如果不能使用网络，而cache中又不存在命中结果，则创建一个504错误
//                this.userResponse = new Response.Builder()
//                        .request(userRequest)
//                        .priorResponse(stripBody(priorResponse))
//                        .protocol(Protocol.HTTP_1_1)
//                        .code(504)
//                        .message("Unsatisfiable Request (only-if-cached)")
//                        .body(EMPTY_BODY)
//                        .build();
//            }
//
//            // 对Response进行解压
//            userResponse = unzip(userResponse);
//        }
//    }

    private void doGet() {

        BitmapFactory.Options options = new BitmapFactory.Options();

        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        List<String> list = new ArrayList<>();
        list.getClass().getGenericInterfaces();
        list.getClass().getTypeParameters();


        //创建一个Request
        final Request request = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                //String htmlStr =  response.body().string();
            }
        });
    }
}

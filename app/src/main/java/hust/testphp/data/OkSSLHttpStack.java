package hust.testphp.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import hust.testphp.MainActivity;
import hust.testphp.R;

/**
 * Created by admin on 2016/3/2.
 */
public class OkSSLHttpStack extends HurlStack {

    private String Default_Host = "115.156.187.146";
    private static final int DEFAULT_PORT = 443;
    private static final int DEFAULT_CRT_RES = R.raw.server;
    private static final String DEFAULT_PASSWORD = "kaiyuan";

    private OkUrlFactory okUrlFactory;
    private SSLSocketFactory sslSocketFactory;

    public OkSSLHttpStack(Context context) {
        this(context, new OkUrlFactory(new OkHttpClient()), null);
    }

    public OkSSLHttpStack(Context context, OkUrlFactory okUrlFactory, SSLSocketFactory sslSocketFactory) {
        this.okUrlFactory = okUrlFactory;
        this.sslSocketFactory = buildSSLSocketFactory(context, DEFAULT_CRT_RES);
    }

    private SSLSocketFactory buildSSLSocketFactory(Context context, int certRawResId) {
        SSLContext sslContext;
        SSLSocketFactory factory = null;
        try {
            // 创建KeyStore
            KeyStore keyStore = buildKeyStore(context, certRawResId);

            // 创建TrustManagerFactory
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // 根据SSLContext获得SSLSocketFactory
            factory = sslContext.getSocketFactory();
        } catch (Exception e) {
            Log.e(MainActivity.LOG_TAG, "buildSSLSocketFactory Error:" + e.toString());
        }
        return factory;
    }

    // 创建KeyStore实例
    private KeyStore buildKeyStore(Context context, int certRawResId)
            throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);

        // 读取Cert证书资源
        Certificate cert = readCert(context, certRawResId);
        keyStore.setCertificateEntry("ca", cert);

        return keyStore;
    }


    // 获得Certificate证书资源
    private Certificate readCert(Context context, int certResourceID) {
        InputStream inputStream = context.getResources().openRawResource(certResourceID);
        Certificate ca = null;

        CertificateFactory cf;
        try {
            cf = CertificateFactory.getInstance("X.509");
            ca = cf.generateCertificate(inputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return ca;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        if ("https".equals(url.getProtocol()) && sslSocketFactory != null) {
            HttpsURLConnection connection = (HttpsURLConnection) okUrlFactory.open(url);
            connection.setSSLSocketFactory(sslSocketFactory);
            Log.w(MainActivity.LOG_TAG, "HTTPS");
            return connection;
        } else {
            Log.w(MainActivity.LOG_TAG, "HTTP");
            return okUrlFactory.open(url);
        }
    }
}

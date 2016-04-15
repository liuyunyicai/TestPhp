package hust.testphp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import hust.testphp.data.TestGson;
import hust.testphp.data.okTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView clickBt;
    private ImageView mImageView;

    private static final String URL_GET = "http://115.156.187.146/TransferServer/ServerMain.php";
//    private static final String URL_IMAGE = "http://http://localhost/TransferServer/picture/transfer/user_pic/photo0.jpg";
    private static final String URL_IMAGE = "http://115.156.187.146/TransferServer/picture/transfer/user_pic/photo0.jpg";
    public static final String LOG_TAG = "LOG_TAG";
    public static final String LOG_TAG1 = "LOG_TAG1";

    public static final String SHARED_NAME = "TransferNfc";

    private MyHandler mHandler;
    private HttpUtils httpUtils;
    private ImageUtils imageUtils;

    private Response.Listener<String> jsonPostListener;
    private Response.Listener<JSONObject> jsonResListener;
    private Response.ErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();

    }

    private void init() {
        clickBt = $(R.id.clickBt);
        mImageView = $(R.id.mImageView);
        clickBt.setOnClickListener(this);

        mHandler = new MyHandler(this);
        httpUtils = HttpUtils.build(this);
        jsonPostListener = HttpUtils.getDefaultJSONPostSuccessListener(mHandler);
        jsonResListener = HttpUtils.getDefaultJSONSuccessListener(mHandler);
        errorListener = HttpUtils.getDefaultErrorListener(mHandler);

        imageUtils = ImageUtils.build(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clickBt:
                try {
                    httpUtils.getJsonResponse(URL_GET, httpUtils.testGetParams(), jsonResListener, errorListener);
                    httpUtils.postJsonResponse(URL_GET, httpUtils.testGetParams(), jsonPostListener, errorListener);

                    imageUtils.loadImage(URL_IMAGE, mImageView);
                    okTest.doGet();

                } catch (Exception e) {

                }

                break;
        }
    }


    private class MyHandler extends Handler {
        private WeakReference<MainActivity> act;

        public MyHandler(MainActivity activity) {
            act = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            act.get().handleMessage(msg);
        }
    }

    // 处理Handler事件
    public void handleMessage(Message msg) {
        String dataStr;
        switch (msg.what) {
            case HttpUtils.GET_SUCCESS:
                dataStr = (String) msg.obj;
                Log.i(LOG_TAG, "GET SUCCESS :" + dataStr);
                break;
            case HttpUtils.GET_FAILED:
                dataStr = (String) msg.obj;
                Log.e(LOG_TAG, "GET_FAILED :" + dataStr);
                break;
            case HttpUtils.GET_JSON_SUCCESS:
                dataStr = ((JSONObject) msg.obj).toString();
                Log.i(LOG_TAG, "GET_JSON_SUCCESS:" + dataStr);
                break;
        }
    }


    private <T> T $(int resId) {
        return (T) findViewById(resId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package hust.testphp.data;

import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import hust.testphp.MainActivity;

/**
 * Created by admin on 2016/3/1.
 */
public class TestGson {

    private Gson gson;

    public TestGson() {
        // Builder模式
        GsonBuilder gsonBuilder = new GsonBuilder();
        // 注册自定义的Adapter;
        gsonBuilder.registerTypeAdapter(Data.class, new TypeAdapter<Data>() {
            // 由对象组装成json
            @Override
            public void write(JsonWriter out, Data value) throws IOException {
            }

            // 解析成对应类对象
            @Override
            public Data read(JsonReader in) throws IOException {
                return null;
            }
        }.nullSafe());
        gson = gsonBuilder.create();

        String str = "";
        Gson gson1 = new Gson();
        gson1.fromJson(str, Data.class);
    }

    private class Data {
        String str = "str";
        Data2  data2 = new Data2();

        // 省略了getter和setter方法
    }

    private class Data2 {
        int num = 10;
    }

    public void test() {



    }
}

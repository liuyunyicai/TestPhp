package hust.testphp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/3/18.
 */
public class TestFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logInfo("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInfo("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logInfo("onCreateView");
        View view = inflater.inflate(R.layout.empty_ayout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logInfo("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        logInfo("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logInfo("onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logInfo("onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        logInfo("onViewStateRestored");
    }

    @Override
    public void onPause() {
        super.onPause();
        logInfo("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logInfo("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logInfo("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logInfo("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logInfo("onDetach");
    }

    private void logInfo(String info) {
        Log.i(MainActivity.LOG_TAG1, "Fragment==" + info);
    }
}

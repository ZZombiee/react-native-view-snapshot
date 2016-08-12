package com.example.react_native_test_view;

import android.util.Log;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by guoshengsheng on 2016/8/10.
 */
public class ReactTestManager extends ViewGroupManager<ReactTestView> {
    public static final String REACT_CLASS="RCTTestView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }


    @Override
    protected ReactTestView createViewInstance(ThemedReactContext reactContext) {
        Log.d("测试自定义view","初始化");
        return new ReactTestView(reactContext);
    }


}

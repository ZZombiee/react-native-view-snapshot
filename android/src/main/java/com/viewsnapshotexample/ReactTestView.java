package com.example.react_native_test_view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by guoshengsheng on 2016/8/10.
 */
public class ReactTestView extends LinearLayout {
    public Context mContext;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("测试自定义view", "已接到消息");
            addview();
        }
    };

    public ReactTestView(Context context) {
        this(context, null);
    }

    public ReactTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReactTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("测试自定义view", "初始化ReactTestView");
        mContext = context;
        this.setOrientation(LinearLayout.VERTICAL);
        addview();
        addview();
        addview();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("测试自定义view", "触摸" + mContext);
        mHandler.sendEmptyMessage(0);
        Log.d("测试自定义view", this.getChildCount() + "");
        Toast.makeText(mContext, "测试点击", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void addview() {
        TextView tv = new TextView(mContext);
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);
        tv.setText("呵呵");
        tv.setBackgroundColor(Color.BLACK);
        LayoutParams params = new LayoutParams(LayoutParams
                .WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        this.addView(tv);
        this.invalidate();
    }

    @ReactMethod
    public void reactAdd() {
        addview();
    }

    class TestModule extends ReactContextBaseJavaModule {
        public TestModule(ReactApplicationContext reactContext) {
            super(reactContext);
        }

        @Override
        public String getName() {
            return null;
        }

        public Activity getActivity() {
            return getCurrentActivity();
        }
    }

}

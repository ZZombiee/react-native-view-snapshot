package com.viewsnapshotexample;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by guoshengsheng on 2016/8/11.
 */
public class SnapshotterModule extends ReactContextBaseJavaModule {
    public SnapshotterModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ViewSnapshotter";
    }

    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }

    @ReactMethod
    public void screenshot(final int tag, final String path, final Callback successCall) {
        // 获取屏幕
        final UIManagerModule uiManager = getReactApplicationContext().getNativeModule
                (UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            @Override
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                View dView = nativeViewHierarchyManager.resolveView(tag);
                Log.d("测试截图", tag + "");
                Log.d("测试截图", dView == null ? "yes" : "no");
                boolean writeSuccessed = false;
                String error="";
                if (dView == null) {
                    return;
                }
                dView.setDrawingCacheEnabled(true);
                dView.buildDrawingCache();
                Bitmap bmp = dView.getDrawingCache();
                if (bmp != null) {
                    try {
                        // 获取内置SD卡路径
                        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                        // 图片文件路径
                        String filePath =  path;

                        File file = new File(filePath);
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                                Log.d("测试截图", file.exists()+"创建");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("测试截图", file.exists()+"创建成功了吗");
                        FileOutputStream os = new FileOutputStream(file);
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                        os.flush();
                        os.close();
                        writeSuccessed = true;
                    } catch (Exception e) {
                        writeSuccessed = false;
                        error=e.toString();
                        Log.d("测试截图", e.toString());
                    } finally {
                        successCall.invoke(error,writeSuccessed);
                    }
                }
            }
        });
    }
}

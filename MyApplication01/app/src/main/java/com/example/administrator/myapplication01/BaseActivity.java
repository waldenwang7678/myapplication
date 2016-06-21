package com.example.administrator.myapplication01;

import android.app.Activity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
}

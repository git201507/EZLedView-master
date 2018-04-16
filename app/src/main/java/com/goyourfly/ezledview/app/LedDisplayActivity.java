package com.goyourfly.ezledview.app;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.goyourfly.ezledview.EZLedView;

//展示LED效果
public class LedDisplayActivity extends AppCompatActivity {
    Handler handler = new Handler();
    int scrollX = 0;
    int direct = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面没有title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_led_display);
        //隐藏状态栏
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        final int windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        final EZLedView ledView = (EZLedView) findViewById(R.id.ledView);
      /*  ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) ledView.getLayoutParams();
        layoutParams.height = ledView.getWidth() + scrollView.getWidth();
        ledView.setLayoutParams(layoutParams);*/
       /* layoutParams.rightMargin = ledView.getWidth() + scrollView.getWidth();
        ledView.setLayoutParams(layoutParams);*/
        ledView.setText("      " + "你好！" + "      ");
       /* ledView.setLedRadius(4);
        ledView.setLedSpace(0);
        ledView.setDrawable(getResources().getDrawable(R.drawable.simpson));*/
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(scrollX, 0);
                scrollX += (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                if (scrollX <= 0 || scrollX >= ledView.getWidth() - scrollView.getWidth()) {
                    Log.i("ledView宽度", "---" + ledView.getWidth());
                    Log.i("scrollView", "---" + scrollView.getWidth());
                    // direct = -direct;
                    scrollX = (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                    Log.i("滚动距离", "---" + scrollX);
                    Log.i("屏幕宽度", "---" + windowWidth);
                }
              /*  if (scrollX >= ledView.getWidth() + scrollView.getWidth()) {
                    scrollX = (ledView.getLedRadius() + ledView.getLedSpace()) * direct;
                }*/
                Log.i("滚动距离", "---" + scrollX);
                handler.postDelayed(this, 30);
            }
        });

    }
}

package com.gtl.planewar.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.Level;
import com.gtl.planewar.utils.ShowExit;
import com.gtl.planewar.view.impl.DieView;
import com.gtl.planewar.view.impl.MainView;
import com.gtl.planewar.view.impl.WinView;

public class ViewActivity extends AppCompatActivity {
    MainView mainView;
    /*Main2View main2View;*/
    WinView winView;
    DieView dieView;
    Intent intent;
    int score;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Config.DIE_VIEW:
                    score = msg.arg1;
                    toFailView(score);
                    break;
                case Config.WIN_VIEW:
                    score = msg.arg1;
                    toWinView(score);
                    break;
                case Config.MAIN_VIEW:
                    toMainView();
                    break;
                case Config.END_VIEW:
                    endView();
                    break;
                case Level.level2:
                    toMain2View();
                    break;
                default:
                    main();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = new MainView(this);
        setContentView(mainView);
    }

    public void toMainView() {
        mainView = new MainView(this);
        setContentView(mainView);
        dieView = null;
        winView = null;
    }
    public void toMain2View() {
        /*main2View = new Main2View(this);
        setContentView(main2View);*/
        mainView=null;
        dieView = null;
        winView = null;
    }

    public void endView() {
        mainView = null;
        dieView = null;
        winView = null;
        finish();
    }

    public void toWinView(int score) {
        winView = new WinView(this, score);
        setContentView(winView);
        mainView = null;
        dieView = null;
    }

    public void toFailView(int score) {
        dieView = new DieView(this, score);
        setContentView(dieView);
        mainView = null;
        winView = null;
    }

    public void main() {
        intent = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            new ShowExit(this).showDialog();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


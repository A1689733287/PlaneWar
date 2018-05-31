package com.gtl.planewar.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.gtl.planewar.R;
import com.gtl.planewar.utils.BGM;
import com.gtl.planewar.utils.Config;
import com.gtl.planewar.utils.FullScreen;
import com.gtl.planewar.utils.GameGetResource;
import com.gtl.planewar.utils.GetDM;
import com.gtl.planewar.utils.ShowExit;
import com.gtl.planewar.utils.SpUtil;
import com.gtl.planewar.utils.audio.Music;


import static com.gtl.planewar.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private ImageView start_iv, score_iv, option_iv, help_iv, exit_iv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FullScreen().setNoTitleBar(this);
        new GetDM(this);
        new GameGetResource(this);
        setContentView(activity_main);

        init();
        soundValue();
    }

    public void init() {
        start_iv = (ImageView) findViewById(R.id.start_iv);
        score_iv = (ImageView) findViewById(R.id.scroe_iv);
        option_iv = (ImageView) findViewById(R.id.option_iv);
        help_iv = (ImageView) findViewById(R.id.help_iv);
        exit_iv = (ImageView) findViewById(R.id.exit_iv);
        start_iv.setOnClickListener(new click());
        score_iv.setOnClickListener(new click());
        option_iv.setOnClickListener(new click());
        help_iv.setOnClickListener(new click());
        exit_iv.setOnClickListener(new click());
    }

    public void soundValue() {
        boolean isCheck = SpUtil.getBoolean(getApplicationContext(), Config.CHECK_BOOLEAN, true);
        if (!isCheck) {
//            BGM.mediaPlayer.setVolume(0, 0);
        } else {
//            BGM.mediaPlayer.setVolume(2, 2);

        }
    }

    public class click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_iv:
                    intent = new Intent(MainActivity.this, ViewActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.scroe_iv:
                    intent = new Intent(MainActivity.this, ScoreActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.option_iv:
                    intent = new Intent(MainActivity.this, OptionActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.help_iv:
                    intent = new Intent(MainActivity.this, HelpActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.exit_iv:
                    finish();
                    break;
            }
        }
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

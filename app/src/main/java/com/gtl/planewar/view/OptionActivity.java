package com.gtl.planewar.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.gtl.planewar.R;
import com.gtl.planewar.utils.SpUtil;



public class OptionActivity extends AppCompatActivity {
    private Button back_btn;
    private Intent intent;
    private Switch sound_sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        init();
    }

    public void init() {
        back_btn = (Button) findViewById(R.id.back);
        sound_sw = (Switch) findViewById(R.id.sound_sw);
        boolean check = SpUtil.getBoolean(this, com.gtl.planewar.utils.Config.CHECK_BOOLEAN, true);
        sound_sw.setChecked(check);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(OptionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sound_sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isCheck = sound_sw.isChecked();
                sound_sw.setChecked(isCheck);
                SpUtil.putBoolean(getApplicationContext(), com.gtl.planewar.utils.Config.CHECK_BOOLEAN, isCheck);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.gtl.planewar.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gtl.planewar.R;

public class ScoreActivity extends AppCompatActivity {
    private LayoutInflater inflater;
    private ListView listView;
    private SharedPreferences sp;
    private MyAdapter adapter;
    private TextView score_item1;
    private TextView score_item2;
    private String[] Scores;
    private Button back_btn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.activity_score);
        init();
    }

    public void init() {
        sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
        String Score = sp.getString("SCORE", "0;0;0;0;0;0;0;0;0;0");
        Scores = Score.split(";");
        listView = (ListView) findViewById(R.id.listview);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        back_btn = (Button) findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Scores.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.score_item, null);
            score_item1 = (TextView) view.findViewById(R.id.score_item1);
            score_item2 = (TextView) view.findViewById(R.id.score_item2);
            score_item1.setText("" + (position + 1));
            score_item2.setText("" + Scores[position]);
            return view;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

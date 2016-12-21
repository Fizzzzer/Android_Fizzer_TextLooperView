package com.fizzer.doraemon.textlooper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextLoopView mview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mview = (TextLoopView) findViewById(R.id.textLoop);

        final List<String> data = new ArrayList<>();
        data.add("中国台湾省");
        data.add("中国日本省");
        data.add("中国琉球省");
        data.add("中国湖北省");
        mview.setDatas(data);
        mview.setItemOnClickListener(new TextLoopView.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this,data.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

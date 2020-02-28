package com.alibaba.taurus.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toast.makeText(this,getIntent().getStringExtra("TAG"),Toast.LENGTH_SHORT).show();
    }



    public void back(View view){
        Intent intent=new Intent();
        intent.putExtra("xxx","test");
        setResult(10086,intent);
        finish();
    }


}

package com.alibaba.taurus.android;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void test(View view){
        PermissionActivity.request(this, new String[]{Manifest.permission.CALL_PHONE}, new ResultCallback() {
            @Override
            public void onSuccess(int requestCode, int resultCode, Intent data) {
                Toast.makeText(MainActivity.this,"onSuccess",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(MainActivity.this,"onError",Toast.LENGTH_SHORT).show();

            }
        });
    }

}

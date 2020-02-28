package com.alibaba.taurus.android;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void test(View view){
//        PermissionActivity.request(this, new String[]{Manifest.permission.CALL_PHONE}, new ResultCallback() {
//            @Override
//            public void onSuccess(int requestCode, int resultCode, Intent data) {
//                Toast.makeText(MainActivity.this,"onSuccess",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Toast.makeText(MainActivity.this,"onError",Toast.LENGTH_SHORT).show();
//            }
//        });

//        HolderTestActivity.startActivityForResultCallback(this, new Intent(this, SecondActivity.class), new ResultCallback() {
//            @Override
//            public void onSuccess(int requestCode, int resultCode, Intent data) {
//                Toast.makeText(MainActivity.this,"onSuccess"+data.getStringExtra("xxx"),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                Toast.makeText(MainActivity.this,"onError"+throwable.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });


        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("TAG","uuyu");
        ActivityResultHelper.startActivityForResult(getSupportFragmentManager(),intent,0)
        .subscribe(new Consumer<ActivityResult>() {
            @Override
            public void accept(ActivityResult activityResult) throws Exception {
                Toast.makeText(MainActivity.this,activityResult.getData().getStringExtra("xxx")+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

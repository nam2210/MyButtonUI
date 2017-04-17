package com.hnam.mybuttonui;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hnam.uibutton.MyTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyTextView tv = (MyTextView) findViewById(R.id.tv);
        //tv.setType(MyTextView.SOLID);
        //tv.setRadiusCorner(getResources().getDimension(R.dimen.spacing_4dp));
        //tv.setSolidColor(ContextCompat.getColor(this, R.color.green_color));

    }
}

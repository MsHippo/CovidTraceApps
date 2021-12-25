package com.coffee.covidtrace.Ui.home.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.coffee.covidtrace.R;

public class PicExpandActivity extends AppCompatActivity {

    Bundle bundle;
    byte [] image;
    ImageView iv_expand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_expand);

        iv_expand = findViewById(R.id.iv_expand);

        bundle = getIntent().getExtras();
        image = bundle.getByteArray("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        iv_expand.setImageBitmap(bmp);

        iv_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
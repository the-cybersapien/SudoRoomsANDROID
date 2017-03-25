package com.example.android.hotel;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView mRoomImage;
    private ImageView oval;
    private Uri received_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        received_uri = intent.getData();
        if(received_uri != null)
        {
            setContentView(R.layout.activity_room_details_staff);
        }
        else
        {
            setContentView(R.layout.activity_room_details);
        }
        mRoomImage = (ImageView) findViewById(R.id.room_image);
        oval = (ImageView) findViewById(R.id.oval);
        Picasso.with(this).load("http://www.chaturmusafir.com/img/M-Resort-Hotel-Room-King-Suite.jpg").into(mRoomImage);
        Picasso.with(this).load("http://cdn.shopify.com/s/files/1/0257/6087/products/Grey_Single_Front_grande.png?v=1487375795").into(oval);
        mRoomImage.setAlpha(80);

    }
}

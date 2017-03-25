package com.example.android.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView mHotelImage;
    private ImageView mCustomerImage;
    private ImageView mStaffImage;
    private Button mStaffButton;
    private Button mCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mHotelImage = (ImageView) findViewById(R.id.hotel_image_main);
        mCustomerImage = (ImageView) findViewById(R.id.customer_image);
        mStaffImage = (ImageView) findViewById(R.id.staff_image);
        mStaffButton = (Button) findViewById(R.id.staff_button);
        mCustomerButton = (Button) findViewById(R.id.customer_button);

        mStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(MainActivity.this ,DetailActivity.class);
                startActivity(list);
            }
        });

        mHotelImage.setAlpha(135);
        Picasso.with(this).load("http://top10hotelbookingsites.webs.com/besthotelsites-1.jpg").into(mHotelImage);
        Picasso.with(this).load(R.drawable.customer).into(mCustomerImage);
        Picasso.with(this).load(R.drawable.staff).into(mStaffImage);


    }
}

package com.example.android.hotel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

    private ImageView mHotelImage;
    private ImageView mCustomerImage;
    private ImageView mStaffImage;
    private Button mStaffButton;
    private Button mCustomerButton;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setNavigationBarColor(getColor(R.color.colorPrimaryDark));
        }
        mHotelImage = (ImageView) findViewById(R.id.hotel_image_main);
        mCustomerImage = (ImageView) findViewById(R.id.customer_image);
        mStaffImage = (ImageView) findViewById(R.id.staff_image);
        mStaffButton = (Button) findViewById(R.id.staff_button);
        mCustomerButton = (Button) findViewById(R.id.customer_button);

        mStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(MainActivity.this ,RoomListActivity.class);
                startActivity(list);
            }
        });

        mCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_AlertDialog);
                builder.setTitle("Access Key");
                text = "";

                Log.v("MainActivity : ", "Builder created");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                input.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text = input.getText().toString();
                        if(text != null && text.length() == 6)
                        {
                            Intent intent = new Intent(MainActivity.this , RoomDetailsActivity.class);
                            intent.putExtra("key",text);
                            startActivity(intent);
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.create().show();

            }
        });

        mHotelImage.setAlpha(0.527f);
        Picasso.with(this).load("http://top10hotelbookingsites.webs.com/besthotelsites-1.jpg").into(mHotelImage);
        Picasso.with(this).load(R.drawable.customer).into(mCustomerImage);
        Picasso.with(this).load(R.drawable.staff).into(mStaffImage);


    }
}

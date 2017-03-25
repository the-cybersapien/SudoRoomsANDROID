package com.example.android.hotel;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView mRoomImage;
    private ImageView oval;
    private Uri received_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        received_uri = intent.getData();
        if (received_uri != null) {
            setContentView(R.layout.activity_room_details_staff);
        } else {
            setContentView(R.layout.activity_room_details);
        }
        mRoomImage = (ImageView) findViewById(R.id.room_image);
        oval = (ImageView) findViewById(R.id.oval);
        Picasso.with(this).load("http://www.chaturmusafir.com/img/M-Resort-Hotel-Room-King-Suite.jpg").into(mRoomImage);
        Picasso.with(this).load("http://cdn.shopify.com/s/files/1/0257/6087/products/Grey_Single_Front_grande.png?v=1487375795").into(oval);
        mRoomImage.setAlpha(80);

    }

    private class RequestAccessAsyncTask extends AsyncTask<String, Void, Void> {

        public static final String QUERY_URL = "192.168.2.214/sudorooms/customer/request.php";

        @Override
        protected Void doInBackground(String... params) {
            if (params[0].isEmpty() || params[1].isEmpty()) {
                return null;
            }

            Uri queryUri = Uri.parse(QUERY_URL)
                    .buildUpon()
                    .appendQueryParameter("room", params[0])
                    .appendQueryParameter("action", params[1]).build();

            URL connect = createURL(queryUri.toString());

            int response = 0;
            HttpURLConnection urlConnection = null;
            if (connect != null) {
                try {
                    urlConnection = (HttpURLConnection) connect.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(10000);
                    urlConnection.setReadTimeout(15000);

                    urlConnection.connect();

                    response = urlConnection.getResponseCode();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }

                if (response == 200 && params[1].equalsIgnoreCase("OPEN")) {
                    Toast.makeText(RoomDetailsActivity.this, "Success! Opening Door now!", Toast.LENGTH_SHORT).show();
                } else if (response == 200 && params[1].equals("CLOSE")) {
                    Toast.makeText(RoomDetailsActivity.this, "Success! Closing Door now!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RoomDetailsActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
            return null;
        }
    }


    private URL createURL(String x) {
        try {
            return new URL(x);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

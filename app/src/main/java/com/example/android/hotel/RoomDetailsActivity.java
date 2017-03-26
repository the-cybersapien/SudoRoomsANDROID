package com.example.android.hotel;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import at.markushi.ui.CircleButton;

import static android.util.Log.v;

public class RoomDetailsActivity extends AppCompatActivity {

    private ImageView mRoomImage;
    private ImageView oval;
    private String received_key;
    private CircleButton customerStatus;
    private FrameLayout requestCircle;
    private FrameLayout customerCircle;
    private RadioGroup radioGroup;
    private View detailsMain;
    private String URL_MAIN = "http://192.168.2.214/sudorooms/customer/validateaccess.php?key=";
    private HttpURLConnection connection = null;
    private URL url;
    private String line;
    private String received_name;
    private String received_room;
    private TextView name_text_view;
    private TextView room_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Intent intent = getIntent();
        received_key = intent.getStringExtra("key");
        if (received_key != null) {
            radioGroup.setVisibility(View.GONE);
            setContentView(R.layout.activity_room_details);
            new StringAsyncTask().execute();

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    switch(radioGroup.getCheckedRadioButtonId())
                    {
                        case R.id.yes : Snackbar bar = Snackbar.make(detailsMain , "Room Service Ordered",Snackbar.LENGTH_LONG);
                            bar.show();
                            v("RoomDetailsActivity : ","yes");
                            break;
                        case R.id.no : v("RoomDetailsActivity : ","NO");
                            break;
                        case R.id.na : Log.v("RoomDetailsActivity : ","NA");
                            break;
                        default : Toast.makeText(RoomDetailsActivity.this, "Something Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            setContentView(R.layout.activity_room_details_staff);
            room_text_view = (TextView) findViewById(R.id.customer_room_no);
            name_text_view = (TextView) findViewById(R.id.detail_name);
            received_name = intent.getStringExtra("Name");
            received_room = intent.getStringExtra("Room");
            name_text_view.setText(received_name);
            room_text_view.setText(received_room);
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        }
        mRoomImage = (ImageView) findViewById(R.id.room_image);
        detailsMain = findViewById(R.id.details_main);
        customerStatus = (CircleButton) findViewById(R.id.customer_status);
        requestCircle = (FrameLayout) findViewById(R.id.customer_request_circle);
        customerCircle = (FrameLayout) findViewById(R.id.customer_open_circle);
        customerCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customerCircle.getSolidColor() == getResources().getColor(R.color.unlocked))
                    customerCircle.setBackgroundColor(getResources().getColor(R.color.locked));
                else
                    customerCircle.setBackgroundColor(getResources().getColor(R.color.unlocked));
            }
        });
        oval = (ImageView) findViewById(R.id.oval);
        Picasso.with(this).load("http://www.chaturmusafir.com/img/M-Resort-Hotel-Room-King-Suite.jpg").into(mRoomImage);
        Picasso.with(this).load("http://cdn.shopify.com/s/files/1/0257/6087/products/Grey_Single_Front_grande.png?v=1487375795").into(oval);
        mRoomImage.setAlpha(80);

        //Picasso.with(this).load(R.drawable.close).into(customerStatus);

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

    private class StringAsyncTask extends AsyncTask<Void, Void , String>
    {

        @Override
        protected String doInBackground(Void... params) {
            URL_MAIN = URL_MAIN + received_key;
            try {
                url = new URL(URL_MAIN);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(10000);
                connection.connect();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader1 = new BufferedReader(reader);

                StringBuilder builder = new StringBuilder();

                while ((line = reader1.readLine()) != null){
                    builder.append(line);
                }

                line = builder.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return line;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.v("Activity" , s);
            try {
                JSONObject main = new JSONObject(s);
                String room = main.getString("Room");
                String name_result = main.getString("Name");
                TextView roomno = (TextView) findViewById(R.id.customer_room_no);
                TextView name = (TextView) findViewById(R.id.detail_name);
                roomno.setText(room);
                name.setText(name_result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

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

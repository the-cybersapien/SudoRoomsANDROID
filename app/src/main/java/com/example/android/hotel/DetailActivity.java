package com.example.android.hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class DetailActivity extends AppCompatActivity {

    private ListView mRoomsList;
    private DetailAdapter mAdapter;
    private ArrayList<RoomDetail> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        roomList = new ArrayList<>();
        mRoomsList = (ListView) findViewById(R.id.rooms_list);
        roomList.add(new RoomDetail("456","Hello"));
        mAdapter = new DetailAdapter(this , roomList);
        mRoomsList.setAdapter(mAdapter);
    }
}

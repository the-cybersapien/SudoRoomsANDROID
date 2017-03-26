package com.example.android.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    private ListView mRoomsList;
    private DetailAdapter mAdapter;
    private ArrayList<RoomDetail> roomList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        roomList = new ArrayList<>();
        mRoomsList = (ListView) findViewById(R.id.rooms_list);
        roomList.add(new RoomDetail("14","One"));
        roomList.add(new RoomDetail("57","Two"));
        roomList.add(new RoomDetail("58","Three"));
        roomList.add(new RoomDetail("59","Four"));
        roomList.add(new RoomDetail("60","Five"));
        roomList.add(new RoomDetail("61","Six"));
        roomList.add(new RoomDetail("62","Seven"));
        roomList.add(new RoomDetail("63","Eight"));
        roomList.add(new RoomDetail("64","Nine"));
        roomList.add(new RoomDetail("65","Ten"));
        mAdapter = new DetailAdapter(this , roomList);
        mRoomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(R.id.name_staff_list);
                TextView room = (TextView) view.findViewById(R.id.room_no_staff_list);
                Intent intent = new Intent(RoomListActivity.this , RoomDetailsActivity.class);
                intent.putExtra("Room",room.getText().toString());
                intent.putExtra("Name",name.getText().toString());
                startActivity(intent);
            }
        });
        mRoomsList.setAdapter(mAdapter);
    }
}

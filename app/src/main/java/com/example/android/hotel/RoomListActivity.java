package com.example.android.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        roomList.add(new RoomDetail("456","One"));
        roomList.add(new RoomDetail("457","Two"));
        roomList.add(new RoomDetail("458","Three"));
        roomList.add(new RoomDetail("459","Four"));
        roomList.add(new RoomDetail("460","Five"));
        roomList.add(new RoomDetail("461","Six"));
        roomList.add(new RoomDetail("462","Seven"));
        roomList.add(new RoomDetail("463","Eight"));
        roomList.add(new RoomDetail("464","Nine"));
        roomList.add(new RoomDetail("465","Ten"));
        mAdapter = new DetailAdapter(this , roomList);
        mRoomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RoomListActivity.this , RoomDetailsActivity.class);
                intent.putExtra("list","true");
                startActivity(intent);
            }
        });
        mRoomsList.setAdapter(mAdapter);
    }
}

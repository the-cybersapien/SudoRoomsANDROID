package com.example.android.hotel;

/**
 * Created by NIKHIL on 25-03-2017.
 */

public class RoomDetail {

    private String mRoomNumber;
    private String mName;

    public RoomDetail(String roomNo , String name)
    {
        mRoomNumber = roomNo;
        mName = name;
    }

    public String getName(){return mName;}
    public String getRoomNo(){return mRoomNumber;}
    public void setName(String name)
    {
        mName = name;
    }
    public void setRoomNo(String room)
    {
        mRoomNumber = room;
    }

}

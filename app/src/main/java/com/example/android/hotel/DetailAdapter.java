package com.example.android.hotel;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.resource;

/**
 * Created by NIKHIL on 25-03-2017.
 */

public class DetailAdapter extends ArrayAdapter<RoomDetail>{

    public DetailAdapter(@NonNull Context context, ArrayList<RoomDetail> rooms) {
        super(context,0 ,rooms);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_staff,parent,false);
        }
        RoomDetail currentItem = getItem(position);

        ImageView status = (ImageView) listItemView.findViewById(R.id.status_image);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), Integer.toString(position) , Toast.LENGTH_SHORT).show();
            }
        });

        TextView name = (TextView) listItemView.findViewById(R.id.name_staff_list);
        name.setText(currentItem.getName());

        TextView roomNo = (TextView) listItemView.findViewById(R.id.room_no_staff_list);
        roomNo.setText(currentItem.getRoomNo());

        return listItemView;
    }
}

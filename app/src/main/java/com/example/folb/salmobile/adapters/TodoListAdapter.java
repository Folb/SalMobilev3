package com.example.folb.salmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.models.ATask;
import com.example.folb.salmobile.models.LouseCounting;

import java.util.List;

/**
 * Created by Follo on 1/4/2017.
 */

public class TodoListAdapter extends ArrayAdapter<ATask> {

    private static final String TAG = "TodoListAdapter";

    public TodoListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TodoListAdapter(Context context, int resource, List<ATask> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.todo_list_item, null);
        }

        ATask task = getItem(position);

        if (task instanceof LouseCounting) {
            LouseCounting c = (LouseCounting) task;
            if (c != null) {
                TextView tt1 = (TextView) v.findViewById(R.id.todo_item_location);
                TextView tt2 = (TextView) v.findViewById(R.id.todo_item_date_from_to);
                TextView tt3 = (TextView) v.findViewById(R.id.todo_item_description);

                if (tt1 != null) {
                    tt1.setText(c.getLocation() + " : " + c.getPenNmb());
                }

                if (tt2 != null) {
                    tt2.setText(c.getTimestamp());
                }
            }
        }

        return v;
    }
}


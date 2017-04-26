package com.example.folb.salmobile.activities.lousecounting.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.supportClasses.FontChanger;

import org.joda.time.DateTime;

/**
 * Created by Follo on 26.04.2017.
 */

public class LouseCountingHeader extends Fragment {

    TextView location, date;
    String loc, curDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.counting_header, container, false);

        initTextViews(v);
        getExtras();

        return v;
    }

    private void getExtras() {
        loc = getArguments().getString("location");
        curDate = formatCurrentDate();
    }

    private String formatCurrentDate() {
        DateTime dt = new DateTime();
        String ret = "" + dt.getDayOfMonth() + "." + dt.getMonthOfYear() + "." + dt.getYear();
        return ret;
    }

    private void initTextViews(View v) {
        location = (TextView) v.findViewById(R.id.counting_location);
        date = (TextView) v.findViewById(R.id.counting_date);
        date.setTypeface(FontChanger.getTypeface(getActivity(), 1));
        location.setTypeface(FontChanger.getTypeface(getActivity(), 0));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        location.setText(loc);
        date.setText(curDate);
    }

}

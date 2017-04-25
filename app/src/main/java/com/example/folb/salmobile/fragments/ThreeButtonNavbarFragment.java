package com.example.folb.salmobile.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.activities.lousecounting.LouseCountingActivity;
import com.example.folb.salmobile.models.LouseCounting;
import com.example.folb.salmobile.supportClasses.FontChanger;

import java.util.ArrayList;

public class ThreeButtonNavbarFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    public static final String TAG = "ThreeButtonNavbarFrag";

    private TextView fst, snd, thr;
    private int fstT, sndT, thrT;
    private boolean inCounting;
    private boolean done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three_button_navbar, container, false);

        getButtons();
        initButtons(v);

        return v;
    }

    private void getButtons() {
        fstT = getArguments().getInt("fst");
        sndT = getArguments().getInt("snd");
        thrT = getArguments().getInt("thr");
        inCounting = getArguments().getBoolean("inCounting");
    }

    private void initButtons(View v) {
        ArrayList<TextView> list = new ArrayList<>();
        list.add(fst = (TextView) v.findViewById(R.id.fst_button));
        list.add(snd = (TextView) v.findViewById(R.id.snd_button));
        list.add(thr = (TextView) v.findViewById(R.id.thr_button));
        setClickList(list);
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public void onClick(View v) {
        if (inCounting) {
            switch (v.getId()) {
                case (R.id.fst_button):
                    ((LouseCountingActivity) getActivity()).previousPressed();
                    break;
                case (R.id.snd_button):

                case (R.id.thr_button):
                    ((LouseCountingActivity) getActivity()).nextPressed();
                    break;

            }
        } else {
//            switch (v.getId()) {
//                case (R.id.fst_button):
//                    ((CountOverview) getActivity()).setMessage();
//                    break;
//                case (R.id.snd_button):
//                    ((CountOverview) getActivity()).setTemp();
//                    break;
//                case (R.id.thr_button):
//                    ((CountOverview) getActivity()).send();
//                    break;
//            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (inCounting && !done) {
            switch (v.getId()) {
                case (R.id.thr_button):
                    ((LouseCountingActivity) getActivity()).completeCounting();
                    return true;
            }
        }
        return false;
    }

    public void setClickList(ArrayList<TextView> list) {
        for (TextView l : list) {
            l.setOnClickListener(this);
            l.setOnLongClickListener(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        fst.setTypeface(FontChanger.getTypeface(getActivity().getParent(), 2));
        snd.setTypeface(FontChanger.getTypeface(getActivity().getParent(), 2));
        thr.setTypeface(FontChanger.getTypeface(getActivity().getParent(), 2));
        fst.setText(fstT);
        snd.setText(sndT);
        thr.setText(thrT);
    }

    public void updateThirdButton(boolean b) {
        if (b) {
            thr.setText(R.string.done);
        } else {
            thr.setText(R.string.right_arrow);
        }
    }

    public void setTempColor(int n) {
        snd.setBackgroundColor(n);
    }
}

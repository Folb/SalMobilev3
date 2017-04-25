package com.example.folb.salmobile.activities.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.models.Fish;

import java.util.List;

/**
 * Created by folb on 25.04.17.
 */

public class OverviewCategoryAdapter extends ArrayAdapter<Fish> {


    private static final String TAG = OverviewCategoryAdapter.class.toString();

    public OverviewCategoryAdapter(Context context, int resource, List<Fish> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.overview_category_item, null);
        }

        Fish f = getItem(position);

        if (f != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.fish_list_text);
            TextView fs = (TextView) v.findViewById(R.id.fs_overview_text);
            TextView lb = (TextView) v.findViewById(R.id.lb_overview_text);
            TextView sb = (TextView) v.findViewById(R.id.sb_overview_text);
            TextView kh = (TextView) v.findViewById(R.id.kh_overview_text);
            TextView sl = (TextView) v.findViewById(R.id.sl_overview_text);

            if (tt1 != null) {
                Log.i(TAG, "getView: " + f.getId() + " is bucket " + f.isBucket());
                if (f.isBucket()) {
                    tt1.setText("Stamp");
                } else {
                    tt1.setText(Integer.toString(f.getId()));
                }
            }
            if (fs != null) {
                if (f.getLice().containsKey(100)) {
                    fs.setText(Integer.toString(f.getLice().get(100)));
                } else {
                    setNull(fs);
                }
            }
            if (lb != null) {
                if (f.getLice().containsKey(103)) {
                    lb.setText(Integer.toString(f.getLice().get(103)));
                } else {
                    setNull(lb);
                }
            }
            if (sb != null) {
                if (f.getLice().containsKey(106)) {
                    sb.setText(Integer.toString(f.getLice().get(106)));
                } else {
                    setNull(sb);
                }
            }
            if (kh != null) {
                if (f.getLice().containsKey(109)) {
                    kh.setText(Integer.toString(f.getLice().get(109)));
                } else {
                    setNull(kh);
                }
            }
            if (sl != null) {
                if (f.getLice().containsKey(112)) {
                    sl.setText(Integer.toString(f.getLice().get(112)));
                } else {
                    setNull(sl);
                }
            }
        }

        return v;
    }

    private void setNull(TextView fs) {
        fs.setText("0");
    }
}

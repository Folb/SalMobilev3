package com.example.folb.salmobile.activities.lousecounting;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.activities.adapters.OverviewCategoryAdapter;
import com.example.folb.salmobile.fragments.ThreeButtonNavbarFragment;
import com.example.folb.salmobile.models.LouseCounting;
import com.google.gson.Gson;

import static java.security.AccessController.getContext;

public class LouseCountingOverviewActivity extends AppCompatActivity {

    private static final String TAG = LouseCountingOverviewActivity.class.toString();
    private LouseCounting counting;

    private ThreeButtonNavbarFragment navbar;
    private ListView listView;
    private OverviewCategoryAdapter adapter;

    private boolean tempSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_louse_counting_overview);

        getCounting();

        if(counting.getTemperature() <= -200.0) {
            tempSet = false;
        } else {
            tempSet = true;
        }

        listView = (ListView) findViewById(R.id.listview);

        adapter = new OverviewCategoryAdapter(this, R.layout.overview_category_item, counting.getFishes());
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goBackToCounting(position);
                Log.i(TAG, "onItemClick: " + position);
            }
        });

        setFragments();
    }

    private void goBackToCounting(int position) {
        Intent intent = new Intent();
        intent.setClass(this, LouseCountingActivity.class);
        intent.putExtra("counting", new Gson().toJson(counting));
        intent.putExtra("curFish", Integer.toString(position));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(tempSet) {
            navbar.setTempColor(0xFFFFFFFF);
        } else {
            navbar.setTempColor(0xFFd70000);
        }
    }

    private void getCounting() {
        String json = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            json = extras.getString("counting");
        }
        counting = new Gson().fromJson(json, LouseCounting.class);


    }

    private void setFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
//        setHeaderFrag(trans);
        setNavBarFrag(trans);

        trans.commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasFocus);
        drawLayout();
    }

    private void drawLayout() {

    }

    private void setNavBarFrag(FragmentTransaction trans) {
        navbar = new ThreeButtonNavbarFragment();
        navbar.setArguments(getNavBarBundle(R.string.edit, R.string.temp, R.string.send));
        trans.add(R.id.navbar_container, navbar, "navbar");
    }

    private Bundle getNavBarBundle(int fst, int snd, int thr) {
        Bundle b = new Bundle();
        b.putInt("fst", fst);
        b.putInt("snd", snd);
        b.putInt("thr", thr);
        b.putBoolean("inCounting", false);
        return b;
    }


}

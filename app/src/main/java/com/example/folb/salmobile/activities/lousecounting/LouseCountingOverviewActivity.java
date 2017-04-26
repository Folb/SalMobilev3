package com.example.folb.salmobile.activities.lousecounting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.activities.MenuActivity;
import com.example.folb.salmobile.activities.adapters.OverviewCategoryAdapter;
import com.example.folb.salmobile.activities.lousecounting.fragments.LouseCountingHeader;
import com.example.folb.salmobile.fragments.ThreeButtonNavbarFragment;
import com.example.folb.salmobile.models.LouseCounting;
import com.example.folb.salmobile.supportClasses.Support;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class LouseCountingOverviewActivity extends AppCompatActivity {

    private static final String TAG = LouseCountingOverviewActivity.class.toString();
    private LouseCounting counting;

    private ThreeButtonNavbarFragment navbar;
    private ListView listView;
    private OverviewCategoryAdapter adapter;
    private LouseCountingHeader header;

    private ArrayList<Integer> total;
    private ArrayList<Double> average;

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
        total = Support.calculateTotal(counting.getFishes());
        average = Support.calculateAverage(total, counting.getFishes().size());
        setAvgTot();
    }

    private void setAvgTot() {
        setTotal(initTotal());
        setAverage(initAverage());
    }

    private ArrayList<TextView> initAverage() {
        ArrayList<TextView> avg = new ArrayList<>();
        for (int id : Support.getAllLiceTypes()) {
            String tvId = Support.getLiceTypeName(id) + "_average";
            TextView tv = (TextView) findViewById(getResources().getIdentifier(tvId, "id", getPackageName()));
            avg.add(tv);
        }
        return avg;
    }

    private void setAverage(ArrayList<TextView> tvs) {
        for (int i = 0; i < average.size(); i++) {
            tvs.get(i).setText(Double.toString(Support.round(average.get(i), 2)));
            tvs.get(i).setTextColor(getResources().getColor(R.color.black));
        }
    }

    private ArrayList<TextView> initTotal() {
        ArrayList<TextView> tot = new ArrayList<>();
        for (int id : Support.getAllLiceTypes()) {
            String tvId = Support.getLiceTypeName(id) + "_total";
            TextView tv = (TextView) findViewById(getResources().getIdentifier(tvId, "id", getPackageName()));
            tot.add(tv);
        }
        return tot;
    }

    private void setTotal(ArrayList<TextView> tvs) {
        for (int i = 0; i < total.size(); i++) {
            tvs.get(i).setText(total.get(i).toString());
            tvs.get(i).setTextColor(getResources().getColor(R.color.black));
        }
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
        setHeaderFrag(trans);
        setNavBarFrag(trans);

        trans.commit();
    }

    private FragmentTransaction setHeaderFrag(FragmentTransaction trans) {
        Bundle b = new Bundle();
        String s = counting.getLocation() + " : " + counting.getPenNmb();
        b.putString("location", s);
        header = new LouseCountingHeader();
        header.setArguments(b);
        trans.add(R.id.overview_header_container, header, "header");
        return trans;
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

    public void setMessage() {
        String s = counting.getComment();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Kommentar");
        alert.setMessage(s);

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ferdig", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                counting.setComment(input.getText().toString());
                return;
            }
        });

        alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }

    public void setTemp() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        double temp = counting.getTemperature();
        alert.setTitle("Temperatur");
        alert.setMessage(Double.toString(temp));

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ferdig", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    counting.setTemperature(Double.parseDouble(input.getText().toString()));
                    tempSet = true;
                    updateColor();
                } catch (NumberFormatException e) {
                    Log.e(TAG, "onClick: ", e);
                }
                return;
            }
        });

        alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }

    private void updateColor() {
        if(tempSet) {
            navbar.setTempColor(0xFFFFFFFF);
        } else {
            navbar.setTempColor(0xFFd70000);
        }
    }

    public void send() {
        Log.i(TAG, "send: ");
        if(tempSet) {
            goBackToMenu();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            double temp = counting.getTemperature();
            alert.setTitle("Temperatur");
            alert.setMessage("Husk å sette temperatur");

            final EditText input = new EditText(this);
            alert.setView(input);

            alert.setPositiveButton("Ferdig", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    try {
                        counting.setTemperature(Double.parseDouble(input.getText().toString()));
                        tempSet = true;
                        goBackToMenu();
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "onClick: ", e);
                    }
                    return;
                }
            });

            alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            alert.show();
        }
    }

    private void goBackToMenu() {
        Intent i = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(i);
        finish();
    }
}

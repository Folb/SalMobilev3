package com.example.folb.salmobile.activities.menuActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.activities.lousecounting.LouseCountingActivity;
import com.example.folb.salmobile.models.Location;
import com.example.folb.salmobile.models.LouseCounting;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by folb on 06.04.17.
 */
public class CreateLouseCountingActivity extends AppCompatActivity {

    ArrayList<Location> locations;
    private TextView locationName;
    private TextView penNmb;
    private RelativeLayout button;
    private EditText username;

    private int curLocation;
    private int curPenNmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_louse_counting);
        locationName = (TextView) findViewById(R.id.location_name);
        penNmb = (TextView) findViewById(R.id.pen_switcher);
        button = (RelativeLayout) findViewById(R.id.confirm);
        username = (EditText) findViewById(R.id.user_name);
        initLocations();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        locationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLocation();
            }
        });
        penNmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchPen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), LouseCountingActivity.class);
                i.putExtra("counting", new Gson().toJson(getCounting()));
                startActivity(i);
                finish();
            }
        });
    }

    private LouseCounting getCounting() {
        return new LouseCounting(locations.get(curLocation).getCompany(), locations.get(curLocation).getName(), Integer.toString(curPenNmb), username.getText().toString(), false);
    }

    private void switchPen() {
        curPenNmb++;
        if(curPenNmb > locations.get(curLocation).getPens()) {
            curPenNmb = 1;
        }
        penNmb.setText(Integer.toString(curPenNmb));
    }

    private void switchLocation() {
        curLocation++;
        curPenNmb = 1;
        if (curLocation == locations.size()) {
            curLocation = 0;
        }
        locationName.setText(locations.get(curLocation).getName());
        penNmb.setText(Integer.toString(curPenNmb));
    }

    private void initLocations() {
        curLocation = 0;
        curPenNmb = 1;
        locations = new ArrayList<>();
        locations.add(new Location("Lerøy", "Hitra", 4));
        locations.add(new Location("Lerøy", "Fossen", 8));
        locations.add(new Location("Lerøy", "Aurora", 5));
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationName.setText(locations.get(curLocation).getName());
        penNmb.setText(Integer.toString(curPenNmb));
    }
}

package com.example.folb.salmobile.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.UIUtils.MenuButton;
import com.example.folb.salmobile.activities.lousecounting.LouseCountingActivity;
import com.example.folb.salmobile.activities.menuActivities.CreateAGDActivity;
import com.example.folb.salmobile.activities.menuActivities.CreateDeadFishCountingActivity;
import com.example.folb.salmobile.activities.menuActivities.CreateEnvironmentParamsActivity;
import com.example.folb.salmobile.activities.menuActivities.CreateLouseCountingActivity;
import com.example.folb.salmobile.activities.menuActivities.CreateOwnDefAvtivity;
import com.example.folb.salmobile.activities.menuActivities.SettingsActivity;
import com.example.folb.salmobile.models.ATask;
import com.example.folb.salmobile.supportClasses.FontChanger;
import com.example.folb.salmobile.supportClasses.UI;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = MenuActivity.class.toString();
    private HashMap<String, MenuButton> buttons;
    private GridLayout grid;

    private ArrayList<ATask> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (buttons == null) {
            initButtons();
        }

        tasks = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        drawButtons();
    }

    private void drawButtons() {
        grid = (GridLayout) findViewById(R.id.gridlayout);
        grid.setBackgroundColor(getResources().getColor(R.color.black));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            drawPortrait();
        } else {
            drawLandscape();
        }
    }

    private void drawLandscape() {
        grid.setColumnCount(3);
        grid.setRowCount(2);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        fillGrid(false);
    }

    private void drawPortrait() {
        grid.setColumnCount(2);
        grid.setRowCount(3);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        fillGrid(true);
    }

    private void fillGrid(boolean portrait) {
        if (portrait) {
            addView(buttons.get("DeadFish"), 0, 0);
            addView(buttons.get("EnvironmentParams"), 0, 1);
            addView(buttons.get("AGD"), 1, 0);
            addView(buttons.get("LouseCounting"), 1, 1);
            addView(buttons.get("OwnDef"), 2, 0);
            addView(buttons.get("Settings"), 2, 1);
        } else {
            addView(buttons.get("DeadFish"), 0, 0);
            addView(buttons.get("EnvironmentParams"), 1, 0);
            addView(buttons.get("AGD"), 0, 1);
            addView(buttons.get("LouseCounting"), 1, 1);
            addView(buttons.get("OwnDef"), 0, 2);
            addView(buttons.get("Settings"), 1, 2);
        }
    }

    private void addView(MenuButton view, int x, int y) {
        final Class gotoClass = view.getLinkedToActivity();
        PercentRelativeLayout rl = (PercentRelativeLayout) getLayoutInflater().inflate(R.layout.two_textview_button, null);
        rl.setBackgroundColor(getResources().getColor(view.getColor()));
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        ArrayList<Integer> dims = UI.getDims(grid);
        params.width =  dims.get(0);
        params.height = dims.get(1);
        params.leftMargin = dims.get(2);
        params.topMargin = dims.get(3);
        params.setGravity(Gravity.CENTER);
        params.columnSpec = GridLayout.spec(y);
        params.rowSpec = GridLayout.spec(x);
        rl.setLayoutParams(params);
        rl = addTextViews(rl, view);
        grid.addView(rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), gotoClass);
                Log.i(TAG, "onClick: " + gotoClass.toString());
                startActivity(i);
                finish();
            }
        });


    }

    private void initButtons() {
        buttons = new HashMap<>();
        MenuButton deadFish = new MenuButton(this, R.color.blue, R.string.dead_fish_icon, R.string.NO_countingDeadFishDesc, CreateDeadFishCountingActivity.class);
        MenuButton environmentParams = new MenuButton(this, R.color.green, R.string.enviroment_icon, R.string.NO_enviromentVariables, CreateEnvironmentParamsActivity.class);
        MenuButton agd = new MenuButton(this, R.color.orange, R.string.agd_icon, R.string.NO_agd, CreateAGDActivity.class);
        MenuButton louseCounting = new MenuButton(this, R.color.orange_red, R.string.louse_counting_icon, R.string.NO_louseCounting, CreateLouseCountingActivity.class);
        MenuButton ownDef = new MenuButton(this, R.color.yellow, R.string.own_def_icon, R.string.NO_ownDef, CreateOwnDefAvtivity.class);
        MenuButton settings = new MenuButton(this, R.color.grey, R.string.settings_icon, R.string.NO_settings, SettingsActivity.class);
        buttons.put("DeadFish", deadFish);
        buttons.put("EnvironmentParams", environmentParams);
        buttons.put("AGD", agd);
        buttons.put("LouseCounting", louseCounting);
        buttons.put("OwnDef", ownDef);
        buttons.put("Settings", settings);
    }

    private PercentRelativeLayout addTextViews(PercentRelativeLayout rl, MenuButton view) {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView upper =  new TextView(this);
        TextView lower = new TextView(this);
        upper.setId(rl.getId()+1);
        lower.setId(rl.getId()+2);
        upper.setTypeface(FontChanger.getTypeface(this, 2));
        lower.setTypeface(FontChanger.getTypeface(this, 1));
        upper.setText(view.getUpperText());
        lower.setText(view.getLowerText());
        upper.setTextColor(getResources().getColor(R.color.black));
        lower.setTextColor(getResources().getColor(R.color.black));
        upper.setGravity(Gravity.CENTER);
        lower.setGravity(Gravity.CENTER);
        // TODO: 13.04.17 Find dynamic text sizes
        upper.setTextSize(rl.getLayoutParams().height/3);
        lower.setTextSize(rl.getLayoutParams().height/10);
        upper.setPadding(10,10,10,10);
        ll.addView(upper);
        ll.addView(lower);
        rl.addView(ll, llParams);
        return rl;
    }
}

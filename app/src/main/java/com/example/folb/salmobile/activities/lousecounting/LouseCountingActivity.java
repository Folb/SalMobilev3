package com.example.folb.salmobile.activities.lousecounting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.UIUtils.CountingButton;
import com.example.folb.salmobile.activities.lousecounting.fragments.LouseCountingHeader;
import com.example.folb.salmobile.fragments.ThreeButtonNavbarFragment;
import com.example.folb.salmobile.models.LouseCounting;
import com.example.folb.salmobile.supportClasses.FontChanger;
import com.example.folb.salmobile.supportClasses.Support;
import com.example.folb.salmobile.supportClasses.UI;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class LouseCountingActivity extends AppCompatActivity {

    public final String TAG = this.getClass().toString();

    private LouseCounting counting;
    private GridLayout grid;
    private HashMap<Integer, CountingButton> buttons;
    private ArrayList<View> views;
    private boolean plus;
    private int curFish;
    private boolean bucketSet;
    private boolean finish;

    private TextView tracker;
    private ThreeButtonNavbarFragment navbar;
    LouseCountingHeader header;
    private RelativeLayout navbarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        navbarContainer = (RelativeLayout) findViewById(R.id.navbar_container);
        tracker = (TextView) findViewById(R.id.tracker);
        curFish = 0;
        getCounting();
        if (counting == null) {
            // TODO: 14.04.17 Change
            counting = new LouseCounting("something", "something Else", "8", "someone", false);
        } else {
            bucketSet = counting.isBucketSet();
        }
        if (buttons == null) {
            views = new ArrayList<>();
            initButtons();
        }

        plus = true;
        finish = false;
        bucketSet = false;
        setFragments();
    }

    private void setFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        setNavBar(trans);
        setHeader(trans);
        trans.commit();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        tracker.setText(Integer.toString(curFish+1));
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasFocus);
        drawLayout();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("counting", new Gson().toJson(counting));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counting = new Gson().fromJson(savedInstanceState.getString("counting"), LouseCounting.class);
    }

    private void getCounting() {
        String json = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            json = extras.getString("counting");
        }
        counting = new Gson().fromJson(json, LouseCounting.class);
        Log.i(TAG, "getCounting: " + counting);
    }

    private FragmentTransaction setHeader(FragmentTransaction trans) {
        Bundle b = new Bundle();
        String s = counting.getLocation() + " : " + counting.getPenNmb();
        b.putString("location", s);
        header = new LouseCountingHeader();
        header.setArguments(b);
        trans.add(R.id.counting_header_container, header, "header");
        return trans;

    }

    private FragmentTransaction setNavBar(FragmentTransaction trans) {
        navbar = new ThreeButtonNavbarFragment();
        navbar.setArguments(Support.getNavbarBundle(R.string.left_arrow, R.string.menu, R.string.right_arrow, true));
        trans.add(R.id.navbar_container, navbar, "navbar");
        return trans;
    }

    private void initButtons() {
        buttons = new HashMap<>();
        buttons.put(100, new CountingButton(R.color.blue, R.string.NO_fastsittende));
        buttons.put(103, new CountingButton(R.color.green, R.string.NO_litenBev));
        buttons.put(106, new CountingButton(R.color.orange, R.string.NO_storBev));
        buttons.put(109, new CountingButton(R.color.orange_red, R.string.NO_kjonnsmoden));
        buttons.put(112, new CountingButton(R.color.yellow, R.string.NO_skottelus));
        buttons.put(115, new CountingButton(R.color.grey, R.string.NO_plusMinus));
    }

    private void drawLayout() {
        if (grid != null) {
            grid.removeAllViews();
        }
        grid = (GridLayout) findViewById(R.id.gridlayout);
        grid.setBackgroundColor(getResources().getColor(R.color.black));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            drawPortrait();
        } else {
            drawLandscape();
        }
    }

    private void drawPortrait() {
        grid.setColumnCount(2);
        grid.setRowCount(3);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        fillGrid(true);
    }

    private void drawLandscape() {
        grid.setColumnCount(3);
        grid.setRowCount(2);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        fillGrid(false);
    }

    private void fillGrid(boolean portrait) {
        if (portrait) {
            addView(buttons.get(100), 0, 0, 100);
            addView(buttons.get(103), 0, 1, 103);
            addView(buttons.get(106), 1, 0, 106);
            addView(buttons.get(109), 1, 1, 109);
            addView(buttons.get(112), 2, 0, 112);
            addView(buttons.get(115), 2, 1, 115);
        } else {
            addView(buttons.get(100), 0, 0, 100);
            addView(buttons.get(103), 1, 0, 103);
            addView(buttons.get(106), 0, 1, 106);
            addView(buttons.get(109), 1, 1, 109);
            addView(buttons.get(112), 0, 2, 112);
            addView(buttons.get(115), 1, 2, 115);
        }
    }

    private void addView(CountingButton view, int x, int y, int id) {
        PercentRelativeLayout rl = (PercentRelativeLayout) getLayoutInflater().inflate(R.layout.two_textview_button, null);
        rl.setBackgroundColor(getResources().getColor(view.getColor()));
        GridLayout.LayoutParams params = setParams(x, y);
        rl.setLayoutParams(params);
        rl.setId(id);
        rl = addTextViews(rl, view);
        rl.isClickable();
        grid.addView(rl);
        if (id != 115) {
            views.add(rl);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPressed(v);
                }
            });
            rl.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    alertDialogOnLongClick(v, "Antall lus", "Skriv inn totalt antall lus", "Ok", "Avbryt");
                    return true;
                }
            });
        } else {
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plus = !plus;
                    updateLowerText();
                }
            });
        }
    }

    private void setTracker(View v, String s) {
        int newVal = Integer.parseInt(s);
        if (newVal > 0) {
            counting.getFishes().get(curFish).getLice().put(v.getId(), Integer.parseInt(s));
            updateTrackers(v);
        }
    }

    private void updateLowerText() {
        int c = 100;
        for (View v : views) {
            if(c != 115) {
                String s;
                if (plus) {
                    s = "+ " + getResources().getString(buttons.get(c).getLowerText());
                } else {
                    s = "- " + getResources().getString(buttons.get(c).getLowerText());
                }
                ((TextView) findViewById(v.getId() + 2)).setText(s);
                c += 3;
            }
        }
    }

    private void buttonPressed(View v) {
        if (plus) {
            counting.getFishes().get(curFish).addToType(v.getId());
        } else {
            if (counting.getFishes().get(curFish).getLice().get(v.getId()) > 0) {
                counting.getFishes().get(curFish).removeFromType(v.getId());
            }
        }
        updateTrackers(v);
    }

    private void updateTrackers(View v) {
        String s = Integer.toString(counting.getFishes().get(curFish).getLice().get(v.getId()));
        ((TextView) findViewById(v.getId()+1)).setText(s);
    }

    private GridLayout.LayoutParams setParams(int x, int y) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        ArrayList<Integer> dims = UI.getDims(grid);
        params.width =  dims.get(0);
        params.height = dims.get(1);
        params.leftMargin = dims.get(2);
        params.topMargin = dims.get(3);
        params.setGravity(Gravity.CENTER);
        params.columnSpec = GridLayout.spec(y);
        params.rowSpec = GridLayout.spec(x);

        return params;
    }

    private PercentRelativeLayout addTextViews(PercentRelativeLayout rl, CountingButton view) {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(getUpperTextView(rl, view));
        ll.addView(getLowerTextView(rl, view));
        rl.addView(ll, llParams);
        return rl;
    }

    public View getUpperTextView(PercentRelativeLayout rl, CountingButton view) {
        TextView upper =  new TextView(this);
        upper.setId(rl.getId()+1);
        upper.setTypeface(FontChanger.getTypeface(this, 0));
        int id = rl.getId();
        if (id != 115) {
            String s = Integer.toString(counting.getFishes().get(curFish).getLice().get(id));
            upper.setText(s);
        } else {
            upper.setText("+/-");
        }
        upper.setTextColor(getResources().getColor(R.color.black));
        upper.setGravity(Gravity.CENTER);
        // TODO: 13.04.17 Find dynamic text sizes
        upper.setTextSize(rl.getLayoutParams().height/3);
        upper.setPadding(10,2,10,0);
        return upper;
    }

    private View getLowerTextView(PercentRelativeLayout rl, CountingButton view) {
        TextView lower = new TextView(this);
        lower.setId(rl.getId()+2);
        lower.setTypeface(FontChanger.getTypeface(this, 1));
        String s = "";
        int id = rl.getId();
        if(id != 115) {
            if (plus) {
                s = "+ ";
            } else {
                s = "- ";
            }
        }
        s += getResources().getString(view.getLowerText());
        lower.setText(s);
        lower.setTextColor(getResources().getColor(R.color.black));
        lower.setGravity(Gravity.CENTER);
        // TODO: 13.04.17 Find dynamic text sizes
        lower.setTextSize(rl.getLayoutParams().height/12);
        lower.setPadding(10,10,10,10);
        return lower;
    }

    public void alertDialogOnLongClick(final View v, String title, String message, String positiveButton, String negativeButton) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                setTracker(v, input.getText().toString());
                return;
            }
        });

        alert.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }

    public void previousPressed() {
        if (finish) {
            finish = false;
            navbar.updateThirdButton(finish);
            navbar.setDone(false);
        }
        if (curFish > 0) {
            curFish--;
            updateAllTrackers();
        }
    }

    public void nextPressed() {
        if (finish) {
            goToOverview();
            return;
        }
        if (bucketSet && curFish == counting.getFishes().size()-2) {
            finish = true;
            navbar.updateThirdButton(finish);
        }
        curFish++;
        if (counting.getFishes().size() == curFish && !bucketSet) {
            counting.addNewFish();
            counting.getFishes().get(curFish-1).setBucket(false);
        }
        updateAllTrackers();
    }

    private void updateAllTrackers() {
        for (int i = 100; i <= 112; i += 3) {
            updateTrackers(findViewById(i));
        }

        if(!finish) {
            tracker.setText(Integer.toString(curFish + 1));
        } else {
            tracker.setText("Stamp");
        }

        if (!plus) {
            plus = true;
            updateLowerText();
        }
    }

    public void completeCounting() {
        Log.i(TAG, "completeCounting: ");
        navbar.setDone(true);
        if (!bucketSet) {
            nextPressed();
        }
        bucketSet = true;
        counting.setBucketSet(true);
        curFish = counting.getFishes().size() - 1;
        counting.getFishes().get(curFish).setBucket(true);
        finish = true;
        navbar.updateThirdButton(finish);
        updateAllTrackers();
    }

    private void goToOverview() {
        Intent intent = new Intent();
        intent.setClass(this, LouseCountingOverviewActivity .class);
        intent.putExtra("counting", new Gson().toJson(counting));
        startActivity(intent);
        finish();
    }
}

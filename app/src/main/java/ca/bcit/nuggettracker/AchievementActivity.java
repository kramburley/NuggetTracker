package ca.bcit.nuggettracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;


/**
 * Nugget Tracker Menu Screen
 *
 * Mark Tan
 * BCIT - 2018
 */

import static ca.bcit.nuggettracker.Activity2.PREF_NAME;

public class AchievementActivity extends AppCompatActivity {

    private static final int NUGGETCALORIE = 296;
    Button popUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        SQLiteHandler sql = new SQLiteHandler(this);

        TextView tv1 = (TextView) findViewById(R.id.AchTotalEaten);
        TextView tv2 = (TextView) findViewById(R.id.AchTotalCalories);

        //int total = sql.totalNuggets();

        //if (total > 0)
        //tv1.setText(" " + total);
        //Log.wtf("hello", ""+sql.totalNuggets());

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        int x = prefs.getInt("NuggetTotal", 0);

        tv1.setText("" + x);
        tv2.setText("" + (x * NUGGETCALORIE));



        // popup menu
        popUpBtn = (Button) findViewById(R.id.AchPopUpBtn);

        popUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final PopupMenu mainPopUp = new PopupMenu(AchievementActivity.this, popUpBtn);
                mainPopUp.getMenuInflater().inflate(R.menu.popup_menu, mainPopUp.getMenu());

                mainPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("Nuggets")) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            return true;
                        } else if (menuItem.getTitle().equals("Graphs")) {
                            Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                            startActivity(intent);
                            return true;
                        } else if (menuItem.getTitle().equals("Achievements")) {
                            Intent intent = new Intent(getApplicationContext(), AchievementActivity.class);
                            startActivity(intent);
                            return true;
                        } else
                            return false;
                    }
                });

                mainPopUp.show();

            }


        });
    }
}

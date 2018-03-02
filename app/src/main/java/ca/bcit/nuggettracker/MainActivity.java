package ca.bcit.nuggettracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * Nugget Tracker Landing Page
 *
 * Mark Tan
 * BCIT - 2018
 */
public class MainActivity extends AppCompatActivity {

    int counter;
    Button popUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;

        final TextView textViewCounter = (TextView) this.findViewById(R.id.mainCounterText);
        final Button mainSubmitBtn = (Button) this.findViewById(R.id.mainSubmitBtn);
        final ImageButton mainNuggetBtn = (ImageButton) this.findViewById(R.id.mainNuggetBtn);

        textViewCounter.setText("" + counter);

        //on mouse click action for clicking nugget image
        mainNuggetBtn.setOnClickListener(new View.OnClickListener() {
            //increments counter and show it on the screen
            public void onClick(View v) {
                counter++;
                textViewCounter.setText("" + counter);
            }
        });

        //on mouse click action for submit button
        mainSubmitBtn.setOnClickListener(new View.OnClickListener() {
            //opens the menu page and pass the counter value to that page for display
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("counter", counter);
                startActivity(intent);
            }

        });

        //popup menu
        popUpBtn = (Button) findViewById(R.id.mainPopUpBtn);

        popUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final PopupMenu mainPopUp = new PopupMenu(MainActivity.this, popUpBtn);
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

        Button minusBtn = (Button)findViewById(R.id.mainMinusBtn);

        minusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(counter > 0) {
                    counter--;
                }
                textViewCounter.setText(""+counter);
            }

        });

        Button plusBtn = (Button)findViewById(R.id.mainPlusBtn);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                textViewCounter.setText(""+counter);
            }

        });


    }


}

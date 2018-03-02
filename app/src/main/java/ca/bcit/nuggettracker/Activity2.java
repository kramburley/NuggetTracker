package ca.bcit.nuggettracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Nugget Tracker Menu Screen
 *
 * Mark Tan
 * BCIT - 2018
 */
public class Activity2 extends AppCompatActivity {

    public static final String PREF_NAME = "NuggetFile";

    Calendar myCalendar;
    EditText edittext;
    DatePickerDialog.OnDateSetListener date;

    Button menuPopUpBtn;

    SQLiteHandler sql;

    RadioGroup groupMeal;

    int counterCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Number of Nuggets===============

        final TextView tv1 = (TextView) findViewById(R.id.menuCountText);

        //gets counter value from main activity
        Intent menuIntent = getIntent();
        counterCurrent = menuIntent.getIntExtra("counter", 0);
        //prints counter value from main activity
        tv1.setText("" + counterCurrent);


        //When========================
        // source:: https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext
        // creates popup calendar activity

        //creates a calendar object
        myCalendar = Calendar.getInstance();

        //prepares input text field to call calendar object
        edittext= (EditText) findViewById(R.id.menuDateInput);

        //creates a date picker object
        date = new DatePickerDialog.OnDateSetListener() {

            // parses the set value to be year, month and day of month
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            // method that replaces the text whith the date picked in the calendar popup
            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        //creates a on click listener
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Activity2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //Meal=============================
        //initializes all radio buttons and radio group
        groupMeal = (RadioGroup) findViewById(R.id.menuMealRadio);
        RadioButton radiobreakfast = new RadioButton(this);
        RadioButton radiolunch = new RadioButton(this);
        RadioButton radiodinner = new RadioButton(this);

        //sets radio button texts
        radiobreakfast.setText("Breakfast");
        radiolunch.setText("Lunch");
        radiodinner.setText("Dinner");

        //adds to the radiogroup
        groupMeal.addView(radiobreakfast);
        groupMeal.addView(radiolunch);
        groupMeal.addView(radiodinner);

        //sets default radio button that is click
        groupMeal.check(radiolunch.getId());

        //Sauce===============================
        //source: https://developer.android.com/guide/topics/ui/controls/spinner.html

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.menuSauceSpinner);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menuSauceList,
                                                                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown.setAdapter(adapter);


        //Enjoyment===================================
        SeekBar sb1 = (SeekBar) findViewById(R.id.menuEnjoymentSeek);
        final TextView tvEnjoyment1 = (TextView) findViewById(R.id.menuEnjoymentMsg);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0:
                        tvEnjoyment1.setText("yuck");
                        break;
                    case 1:
                        tvEnjoyment1.setText("okaaay");
                        break;
                    case 2:
                        tvEnjoyment1.setText("meh..");
                        break;
                    case 3:
                        tvEnjoyment1.setText("gooood");
                        break;
                    case 4:
                        tvEnjoyment1.setText("YUM!");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        // Regrets==================================
        //initializes all radio buttons and radio group for regret option
        RadioGroup groupRegrets = (RadioGroup) findViewById(R.id.menuRegretsRadio);
        RadioButton radioRegYes = new RadioButton(this);
        RadioButton radioRegNo = new RadioButton(this);

        //sets radio button texts
        radioRegYes.setText("Yes");
        radioRegNo.setText("No");

        //adds to the radiogroup
        groupRegrets.addView(radioRegYes);
        groupRegrets.addView(radioRegNo);


        //popup menu
        menuPopUpBtn = (Button) findViewById(R.id.menuPopUpBtn);

        menuPopUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final PopupMenu menuPopUp = new PopupMenu(Activity2.this, menuPopUpBtn);
                menuPopUp.getMenuInflater().inflate(R.menu.popup_menu, menuPopUp.getMenu());

                menuPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
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

                menuPopUp.show();

            }


        });

        sql = new SQLiteHandler(this);

        // submit button
        Button submit = (Button) findViewById(R.id.menuSubmitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int selectedId = groupMeal.getCheckedRadioButtonId();
                Intent intent = new Intent(Activity2.this, AchievementActivity.class);
                sql.insertNugget(selectedId, counterCurrent, "Hello");

                //using shared preference
                //source: https://stackoverflow.com/questions/23024831/android-shared-preferences-example
                SharedPreferences ed1 = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
                int sum = ed1.getInt("NuggetTotal", counterCurrent);
                sum+=counterCurrent;
                editor.putInt("NuggetTotal", sum);
                editor.apply();

                startActivity(intent);
            }
        } );


        //source: https://code.tutsplus.com/tutorials/android-from-scratch-how-to-store-application-data-locally--cms-26853
        //database calls
    }
}

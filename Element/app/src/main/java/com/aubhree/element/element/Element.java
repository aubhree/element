package com.aubhree.element.element;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TimePicker;
import android.content.Intent;
import android.app.AlarmManager;
import android.content.Context;
import android.app.PendingIntent;
import java.lang.System;
import java.util.Calendar;

public class Element extends AppCompatActivity {

    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private TextView textView1;
    private TextView textView2;
    private String value1;
    private String value2;
    private EditText zipCode;
    private Button saveBtn;
    private TimePicker timePicker;
    private long time;
    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;
    private Context context;

    private static int zip;
    private static int lowRange;
    private static int highRange;
    private static int alarmHour;
    private static int alarmMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);
        initializeVariables();

        value1 = Integer.toString(seekBar1.getProgress());
        value2 = Integer.toString(seekBar2.getProgress());
        textView1.setText(value1);
        textView2.setText(value2);

        // checks and sets the low range value for sliding bar
       seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               value1 = Integer.toString(progress);
               textView1.setText(value1);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
        // checks and sets the high range value for sliding bar
        seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value2 = Integer.toString(progress);
                textView2.setText(value2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //---------------saved button is clicked--------------
        saveBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                zip = (Integer.parseInt(zipCode.getText().toString()));
                lowRange = Integer.parseInt(value1);
                highRange = Integer.parseInt(value2);
                alarmHour = timePicker.getCurrentHour();
                alarmMin = timePicker.getCurrentMinute();

                //------sets up alarm here---------------
                scheduleAlarm();
            }
        });
    }

    public void scheduleAlarm() {
        //Long time = new GregorianCalendar().getTimeInMillis() + 15000;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, alarmMin);

        //checks if pm time or else sets am
        if (alarmHour > 12) {
            alarmHour = alarmHour % 12;
            calendar.set(Calendar.HOUR, alarmHour);
            calendar.set(Calendar.AM_PM, Calendar.PM);

        } else {

            calendar.set(Calendar.HOUR, alarmHour);
            calendar.set(Calendar.AM_PM, Calendar.AM);

        }

        // create an Intent and set the class which will execute when Alarm triggers, here we have
        // given AlarmReciever in the Intent, the onRecieve() method of this class will execute when
        // alarm triggers
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);

        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(Element.this, "Temperature checker has started - zip is: " + zip, Toast.LENGTH_LONG).show();
    }

    public void toastMsg(){

        runOnUiThread(new Runnable() {
            public void run() {

                Toast.makeText(getBaseContext(), "Current milli: " + System.currentTimeMillis() + ". Alarm time: "+ time,
                        Toast.LENGTH_LONG).show();

            }
        });

    }
    private void initializeVariables() {
        seekBar1 = (SeekBar) findViewById(R.id.seek1);
        seekBar2 = (SeekBar) findViewById(R.id.seek2);
        textView1 = (TextView) findViewById(R.id.seekstatus1);
        textView2 = (TextView) findViewById(R.id.seekstatus2);
        zipCode = (EditText) findViewById(R.id.zipcode);
        saveBtn = (Button) findViewById(R.id.button);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_element, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int getZip(){

        return zip;

    }

    public static int getLowRange(){

        return lowRange;

    }

    public static int getHighRange(){

        return highRange;

    }

    public static int getAlarmHour(){

        return alarmHour;

    }

    public static int getAlarmMin(){

        return alarmMin;

    }
}

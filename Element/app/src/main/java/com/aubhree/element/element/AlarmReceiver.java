package com.aubhree.element.element;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.app.NotificationManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import android.os.AsyncTask;

import org.json.JSONException;

public class AlarmReceiver extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 1;
    private int zip = Element.getZip();
    private Context context = null;
    private float temp;
    private String data;
    private Weather weather = new Weather();
    private WeatherRetriever weatherRetriever = new WeatherRetriever();
    private WeatherParser weatherParser = new WeatherParser();

    @Override
    public void onReceive(Context context, Intent intent1) {

        this.context = context;
        this.zip = Element.getZip();

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(Integer.toString(zip));

    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {

            data = weatherRetriever.retrieveData(Integer.parseInt(params[0]));

            try {

                weather = weatherParser.getWeather(data);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            try {

                temp = weather.getTemp();
                setTemp(temp);
                //display generic message to screen
                // TODO TAKE THIS OUT WHEN NOTIFICATION IS DONE
                Toast.makeText(context, "Current temp is: " + temp, Toast.LENGTH_LONG).show();
                //create notification
                sendNotification(context);

            } catch (Exception e) {

                System.err.println("invalid entry");

            }

        }

    }

    public void sendNotification(Context context) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.drawable.ic_stat_notification);

        builder.setAutoCancel(true);

        builder.setContentTitle("Element is running");
        builder.setContentText("Plays sound based on temperature!");

        try {
            Uri sound = null;

            if (temp < Element.getLowRange()){

                sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.cold);

            }
            else if (temp > Element.getHighRange()){

                sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.hot);

            }
            else if ((temp >= Element.getLowRange()) && (temp <= Element.getHighRange())){

                sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.perfect);

            }

            builder.setSound(sound);

        } catch (Exception e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public void setTemp(float temp) {

        this.temp = temp;

    }
}

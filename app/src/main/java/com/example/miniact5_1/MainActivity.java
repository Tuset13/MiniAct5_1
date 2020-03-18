package com.example.miniact5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    ConnectivityManager cm;
    NetworkInfo activeNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView twInfo = findViewById(R.id.textView);
        TextView twState = findViewById(R.id.textView2);

        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        new CheckConnectivityTask().execute();

        if(cm != null)
        {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            twInfo.setText(activeNetwork.toString());

            new CheckConnectivityTask().execute();
        }
    }

    private class CheckConnectivityTask extends AsyncTask<Activity, Void, String>{

        @Override
        protected String doInBackground(Activity... activity) {
            TextView twInfo = findViewById(R.id.textView);
            TextView twState = findViewById(R.id.textView2);

            if(activeNetwork != null && activeNetwork.isConnectedOrConnecting())
            {
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    return (String) getText(R.string.wifiCon);
                }
                else if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    return (String) getText(R.string.mobCon);
                }
            }
            return (String) getText(R.string.noCon);
        }
    }
}
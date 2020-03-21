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

        seeNetworkState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        seeNetworkState();
    }

    private class CheckConnectivityTask extends AsyncTask<Activity, Void, String>{

        @Override
        protected String doInBackground(Activity... activity) {

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

        @Override
        protected void onPostExecute(String result) {
            TextView twState = findViewById(R.id.textView2);
            twState.setText(result);
        }
    }

    public void seeNetworkState(){
        TextView twInfo = findViewById(R.id.textView);

        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm != null)
        {
            activeNetwork = cm.getActiveNetworkInfo();

            twInfo.setText(activeNetwork.toString());

            new CheckConnectivityTask().execute();
        }
    }
}
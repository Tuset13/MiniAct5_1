package com.example.miniact5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView twInfo = findViewById(R.id.textView);
        TextView twState = findViewById(R.id.textView2);

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm != null)
        {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected)
            {
                twInfo.setText(activeNetwork.toString());
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    twState.setText(R.string.wifiCon);
                }
                else if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    twState.setText(R.string.mobCon);
                }
            } else {
                twState.setText(R.string.noCon);
            }
        }
    }
}
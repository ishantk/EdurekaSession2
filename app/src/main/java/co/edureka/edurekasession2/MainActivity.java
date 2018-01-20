package co.edureka.edurekasession2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    MyReceiver myReceiver;

    MyLocalReceiver localReceiver;

    void initLocalReceiver(){
        localReceiver = new MyLocalReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("action1");
        filter.addAction("action1.myaction");
        filter.addAction("action1.action2");

        LocalBroadcastManager.getInstance(this).registerReceiver(localReceiver,filter);
    }

    // Explicit Way | is always fast
    void initMyReceiver(){

        myReceiver = new MyReceiver(); // Create The Object of MyReceiver

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_PACKAGE_ADDED); // Application Installed
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED); // Application UnInstalled
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);

        //        filter.addAction(Intent.ACTION_BATTERY_LOW);
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        filter.addAction("hello");

        filter.addDataScheme("package");

        // Registration of BroadcastReceiver
        registerReceiver(myReceiver,filter);

        Toast.makeText(this,"Receiver Registered",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMyReceiver();
        initLocalReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(localReceiver);
    }

    public void fireAction(View view){

        Intent intent = new Intent("action1");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}

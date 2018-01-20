package co.edureka.edurekasession2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName,eTxtAge;
    Button btnSubmit;

    User user;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        initViews();
    }

    @Override
    public void onClick(View view) {

        // Explicit Intent
        //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

        // Implicit Intent
        //Intent intent = new Intent("co.edureka.edurekasession2.activitytwo");

        // Pass the data in intent from A1 to A2 | Forward Passing

        //String name = eTxtName.getText().toString();
        //String age = eTxtAge.getText().toString();


        // HashMap | Key Value Pair, Key is always a String
        //intent.putExtra("keyName",name);
        //intent.putExtra("keyAge",age);

        /*Bundle bundle = new Bundle();
        bundle.putString("keyName",name);
        bundle.putString("keyAge",age);

        intent.putExtra("keyBundle",bundle);*/

        //user = new User();
        //user.name = eTxtName.getText().toString();
        //user.age = eTxtAge.getText().toString();

        //intent.putExtra("keyUser",user);

        /*if(user.validateUser()) {

            startActivity(intent);
        }else{
            Toast.makeText(this,"Please Enter All Details",Toast.LENGTH_LONG).show();
        }*/

        //Intent i = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        //i.putExtra(Intent.EXTRA_TEXT,"This is a hello from Edureka");
        //startActivity(Intent.createChooser(i,"Choose an Email Client"));

        //Intent i = new Intent(Intent.ACTION_DIAL);
        //i.setData(Uri.parse("tel:+91934567889"));
        //startActivity(i);

        // Get the data back from A2 into A1 | Backward Passing
        //startActivityForResult(intent,101);

        showNotification();
    }


    void showNotification(){

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel("myChannel", "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("My Channel");

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);

            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000}); // Needs Permission in the Manifest file
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myChannel");

        builder.setSmallIcon(R.drawable.music);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text");
        builder.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("This is a Big Title"));

        builder.addAction(android.R.drawable.ic_menu_add,"Add",null);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",null);

        Notification notification = builder.build();

        notificationManager.notify(201, notification);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            String name = data.getStringExtra("keyName");
            String age = data.getStringExtra("keyAge");

            eTxtName.setText("Name Obtained: "+name);
            eTxtAge.setText("Age Obtained: "+age);
        }
    }
}

package co.edureka.edurekasession2;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileDemoActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener{

    Button btnRW;
    EditText eTxtQuote;
    ImageView imageView;

    SharedPreferences preferences; // XML File Storage in internal memory
    // data is stored as key value pair

    void initViews(){
        eTxtQuote = findViewById(R.id.editTextQuote);
        btnRW = findViewById(R.id.buttonReadWrite);
        imageView = findViewById(R.id.imageView);

        btnRW.setOnClickListener(this);

        preferences = getSharedPreferences("edurekaPrefs",MODE_PRIVATE);

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.alpha);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.rotate);
        eTxtQuote.startAnimation(animation1);
        btnRW.startAnimation(animation2);

        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
        animationDrawable.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_demo);
        initViews();
    }


    void writeInternal(){
        try{

            String quote = eTxtQuote.getText().toString().trim();

            FileOutputStream outputStream = openFileOutput("myFile.txt",MODE_PRIVATE);
            outputStream.write(quote.getBytes());
            outputStream.close();

            Toast.makeText(this,"Quote Saved..",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readInternal(){
        try{

            FileInputStream inputStream = openFileInput("myFile.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);

            String quote = buffer.readLine();

            Toast.makeText(this,"Quote is "+quote,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void writeExternal(){
        try{

            String quote = eTxtQuote.getText().toString().trim();

            // Path to the SD Card
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            //File file = new File(path+"/edureka.txt");
            File file = new File("/storage/16F7-330D/myfile.txt");
            Toast.makeText(this,"Path: "+path,Toast.LENGTH_LONG).show();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(quote.getBytes());
            outputStream.close();

            Toast.makeText(this,"Quote Saved.."+path,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readExternal(){
        try{
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(path+"/edureka.txt");
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);

            String quote = buffer.readLine();

            Toast.makeText(this,"Quote is "+quote,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void persistQuote(){

        String quote = eTxtQuote.getText().toString().trim();

        //preferences.edit().putString("keyQuote",quote).commit(); // Saves data in Same UI Thread
        preferences.edit().putString("keyQuote",quote).apply(); // Saves data in BackGround Thread
        preferences.edit().putInt("keyAge",30).apply();

        Toast.makeText(this,"Quote Saved..",Toast.LENGTH_LONG).show();
    }

    void readQuote(){
        String data = preferences.getString("keyQuote","NA");
        int age = preferences.getInt("keyAge",0);
        eTxtQuote.setText(data);
    }

    MediaPlayer mediaPlayer;

    void playMusic(){
        mediaPlayer = new MediaPlayer();
        String songToPlay = "/storage/16F7-330D/Avicii_The_Nights.mp3";

        try {
            //mediaPlayer.setDataSource(songToPlay);

            String url = "http://abc.com/xyz.mp3";

            // Streaming the Song from Network
            mediaPlayer.setDataSource(this, Uri.parse(url));

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //mediaPlayer.pause();

        //mediaPlayer.seekTo(2500);

        //mediaPlayer.stop();
        //mediaPlayer.release();
    }

    @Override
    public void onClick(View v) {
        //writeInternal();
        //readInternal();

        writeExternal();
        //readExternal();

        //persistQuote();
        //readQuote();

        //playMusic();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        sharedPreferences.getString(key,"NA");
    }
}

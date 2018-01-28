package co.edureka.edurekasession2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyBookIntentService extends IntentService {

    StringBuffer response = new StringBuffer();
    String url = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

    public MyBookIntentService(){
        super("IntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try{

            URL fetchBooksUrl = new URL(url);

            // Android Client sends a request to the URL to get the data
            HttpURLConnection urlConnection = (HttpURLConnection) fetchBooksUrl.openConnection();

            // Reading the Response
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = "";

            while((line = reader.readLine())!=null){
                response.append(line);
            }

            Intent intent1 = new Intent("books.fetched");
            intent1.putExtra("keyResponse",response.toString());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

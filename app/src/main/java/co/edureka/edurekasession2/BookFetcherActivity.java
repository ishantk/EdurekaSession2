package co.edureka.edurekasession2;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookFetcherActivity extends AppCompatActivity {

    ListView listView;
    ProgressDialog progressDialog;

    StringBuffer response = new StringBuffer();

    String url = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

    ArrayAdapter<String> adapter;

    ArrayList<Book> bookList;

    RequestQueue requestQueue; // Process Requests
    StringRequest stringRequest; // Request

    boolean isInternetConnected(){

        ConnectivityManager manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();
    }


    // 2.
    void retrieveBooks(){

        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jObj = jsonArray.getJSONObject(i);

                                String price = jObj.getString("price");
                                String name = jObj.getString("name");
                                String author = jObj.getString("author");

                                //Book book = new Book(name,price,author);
                                //bookList.add(book);

                                adapter.add(name+"\n"+author+"\n"+price);
                            }

                            listView.setAdapter(adapter);

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        progressDialog.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookFetcherActivity.this,"Some Error",Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }

        )
        /*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> map = new HashMap<>();
                map.put("param1","value1");
                map.put("param2","value2");

                return map;
            }
        }*/
        ;

        /*stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });*/
        requestQueue.add(stringRequest); // Process the Request
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fetcher);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        MyReceiver receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("books.fetched");

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        if(isInternetConnected()) {
            //progressDialog.show();
            //new RetrieveBooksThread().start(); //retrieveBooks(); //new RetrieveBooks().execute(); // Starting a Thread in Android i.e. AsyncTask

            Intent intent = new Intent(BookFetcherActivity.this,MyBookIntentService.class);
            startService(intent);

        }else {
            Toast.makeText(this, "Please check Internet and Try Again!", Toast.LENGTH_LONG).show();
        }
    }




    //1.

    class RetrieveBooks extends AsyncTask{ // Thread in Android

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

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

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            try {

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    String price = jObj.getString("price");
                    String name = jObj.getString("name");
                    String author = jObj.getString("author");

                    //Book book = new Book(name,price,author);
                    //bookList.add(book);

                    adapter.add(name+"\n"+author+"\n"+price);
                }

                listView.setAdapter(adapter);

            }catch (Exception e){
                e.printStackTrace();
            }

            progressDialog.dismiss();
        }
    }


    //3.

    class RetrieveBooksThread extends Thread{

        @Override
        public void run() {

            Looper.prepare();

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

                handler.sendEmptyMessage(101);

            }catch (Exception e){
                e.printStackTrace();
            }


            Looper.loop();

        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        String price = jObj.getString("price");
                        String name = jObj.getString("name");
                        String author = jObj.getString("author");

                        //Book book = new Book(name,price,author);
                        //bookList.add(book);

                        adapter.add(name+"\n"+author+"\n"+price);
                    }

                    listView.setAdapter(adapter);
                    progressDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    };


    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            String res = intent.getStringExtra("keyResponse");
            try {

                JSONObject jsonObject = new JSONObject(res);
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);

                    String price = jObj.getString("price");
                    String name = jObj.getString("name");
                    String author = jObj.getString("author");

                    //Book book = new Book(name,price,author);
                    //bookList.add(book);

                    adapter.add(name+"\n"+author+"\n"+price);
                }

                listView.setAdapter(adapter);
                //progressDialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}

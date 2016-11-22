package com.example.jim.jimboelrijkpset3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieInformationActivity extends AppCompatActivity {

    private String TAG = MovieInformationActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    ArrayList<String> MovieInformation;

    // URL to get contacts JSON
    private static String url1 = "http://www.omdbapi.com/?t=";
    private static String url2 = "&y=&plot=full&r=json";
    String completeURL;
    String chosen_tag;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

        Bundle extras = getIntent().getExtras();
        chosen_tag = extras.getString("Search");

        completeURL = url1 + chosen_tag + url2;
        MovieInformation = new ArrayList<>();


        lv = (ListView) findViewById(R.id.list);

        new GetMovie().execute();
    }

    private class GetMovie extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MovieInformationActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        String jsonStr = sh.makeServiceCall(completeURL);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String title = jsonObj.getString("Title");
                String Year = jsonObj.getString("Year");
                String Genre = jsonObj.getString("Genre");
                String Plot = jsonObj.getString("Plot");
                String Image = jsonObj.getString("Poster");

                MovieInformation.add(title);
                MovieInformation.add(Year);
                MovieInformation.add(Genre);
                MovieInformation.add(Plot);
                MovieInformation.add(Image);

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }

                });
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (pDialog.isShowing())
            pDialog.dismiss();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MovieInformationActivity.this, android.R.layout.simple_list_item_1, MovieInformation);


        // Get the ListView so we can work with it
        ListView theListView = (ListView) findViewById(R.id.list);

        // Connect the ListView with the Adapter that acts as a bridge between it and the array
        theListView.setAdapter(arrayAdapter);



    }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.search) {
            Intent search_activity = new Intent(this, SearchActivity.class);
            startActivity(search_activity);
            finish();
        }
        if (id == R.id.watch_list) {
                Intent search_activity = new Intent(this, MainActivity.class);
                startActivity(search_activity);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void save(View view) {

    }
}

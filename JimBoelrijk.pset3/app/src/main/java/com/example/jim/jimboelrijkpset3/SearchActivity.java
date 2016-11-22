package com.example.jim.jimboelrijkpset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    String search_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View view) {
        EditText input = (EditText) findViewById(R.id.input);
        search_request = input.getText().toString();

        if (!(search_request.length() == 0)){
            Intent LookUp = new Intent(this, MovieInformationActivity.class);
            LookUp.putExtra("Search", search_request);
            startActivity(LookUp);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.watch_list) {
            Intent search_activity = new Intent(this, MainActivity.class);
            startActivity(search_activity);
            finish();
        }
        if (id == R.id.activity_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

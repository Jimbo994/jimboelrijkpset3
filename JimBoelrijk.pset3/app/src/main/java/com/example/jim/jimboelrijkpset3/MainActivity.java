package com.example.jim.jimboelrijkpset3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simple array with a list of my favorite TV shows
        String[] favoriteTVShows = {"Pushing Daisies", "Better Off Ted",
                "Twin Peaks", "Freaks and Geeks", "Orphan Black", "Walking Dead",
                "Breaking Bad", "The 400", "Alphas", "Life on Mars"};


        ListAdapter theAdapter = new MyAdapter(this, favoriteTVShows);

        ListView theListView = (ListView) findViewById(R.id.watch_list);

        theListView.setAdapter(theAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void delete(View view) {
    }
}


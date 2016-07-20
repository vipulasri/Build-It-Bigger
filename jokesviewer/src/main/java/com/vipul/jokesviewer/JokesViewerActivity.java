package com.vipul.jokesviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HP-HP on 17-07-2016.
 */

public class JokesViewerActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "EXTRA_JOKE";

    private TextView jokeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);

        jokeTextView = (TextView) findViewById(R.id.jokeTextView);

        if (getIntent() != null && getIntent().hasExtra(EXTRA_JOKE)) {
            jokeTextView.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
            default:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

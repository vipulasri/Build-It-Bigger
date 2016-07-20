package com.vipul.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.vipul.jokesviewer.JokesViewerActivity;


public class MainActivityFragment extends Fragment implements GetJokesTask.Callbacks{

    private Button tellJoke;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        tellJoke = (Button) root.findViewById(R.id.tellJoke);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTellJoke();
            }
        });

        return root;
    }

    public void onTellJoke(){
        //noinspection unchecked
        new GetJokesTask(this).execute();
    }

    @Override
    public void onJokeLoadStart() {
        progressBar.setVisibility(View.VISIBLE);
        tellJoke.setVisibility(View.GONE);
    }

    @Override
    public void onJokeLoaded(String joke) {
        progressBar.setVisibility(View.GONE);

        Intent intent = new Intent(getContext(), JokesViewerActivity.class);
        intent.putExtra(JokesViewerActivity.EXTRA_JOKE, joke);
        startActivity(intent);

        tellJoke.setVisibility(View.VISIBLE);
    }
}

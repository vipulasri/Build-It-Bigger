package com.vipul.builditbigger;

import android.test.AndroidTestCase;

public class GetJokeTaskTest extends AndroidTestCase implements GetJokesTask.Callbacks{

    public void testVerifySuccessResponse() {
        String joke = new GetJokesTask(this).doInBackground();
        assertFalse(joke.isEmpty());
    }

    @Override
    public void onJokeLoadStart() {

    }

    @Override
    public void onJokeLoaded(String joke) {

    }
}
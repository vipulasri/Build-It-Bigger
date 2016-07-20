package com.vipul.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.vipul.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by HP-HP on 17-07-2016.
 */
public class GetJokesTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private Callbacks mCallbacks;

    public GetJokesTask(Callbacks callbacks) {
        mCallbacks = callbacks;
    }

    interface Callbacks {
        void onJokeLoadStart();
        void onJokeLoaded(String joke);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallbacks.onJokeLoadStart();
    }

    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJokes().execute().getData();
        } catch (IOException e) {
            Log.e("GetJokesTask", e.getMessage(), e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallbacks.onJokeLoaded(result);
    }
}

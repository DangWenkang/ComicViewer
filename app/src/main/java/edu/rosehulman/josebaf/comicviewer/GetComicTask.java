package edu.rosehulman.josebaf.comicviewer;

import android.os.AsyncTask;
import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;

/**
 * Created by josebaf on 1/14/2016.
 */
public class GetComicTask extends AsyncTask<String, Void, Comic> {

    private static final String ERROR_TAG = "ERROR";
    private ComicConsumer mComicConsumer;

    public GetComicTask(ComicConsumer activity){
        mComicConsumer = activity;
    }

    @Override
    protected Comic doInBackground(String... urlStrings) {
        String urlString = urlStrings[0];
        Comic comic = null;

        try{
            comic = new ObjectMapper().readValue(new URL(urlString), Comic.class);
        }catch (IOException e){
            Log.d(ERROR_TAG, "ERROR: " + e.toString());
        }

        return comic;
    }

    @Override
    protected void onPostExecute(Comic comic){
        super.onPostExecute(comic);
        mComicConsumer.onComicLoaded(comic);
    }

    public interface ComicConsumer{
        void onComicLoaded(Comic comic);
    }
}

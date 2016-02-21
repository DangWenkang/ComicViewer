package edu.rosehulman.josebaf.comicviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by josebaf on 1/14/2016.
 */
public class GetImageTask extends AsyncTask<String, Void, Bitmap>{

    private static final String ERROR_TAG = "ERROR";
    private ComicImage mComicImage;

    public GetImageTask(ComicImage activity){
        mComicImage = activity;
    }

    @Override
    protected Bitmap doInBackground(String... urlStrings) {
        String urlString = urlStrings[0];
        Bitmap bitmap;
        InputStream in = null;

        try {
            in = new java.net.URL(urlString).openStream();
        } catch (IOException e) {
            Log.d(ERROR_TAG, "ERROR: " + e.toString());
        }
        bitmap = BitmapFactory.decodeStream(in);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        mComicImage.onImageLoaded(bitmap);
    }

    public interface ComicImage{
        void onImageLoaded(Bitmap bitmap);
    }
}

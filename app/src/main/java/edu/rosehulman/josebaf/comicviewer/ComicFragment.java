package edu.rosehulman.josebaf.comicviewer;

/**
 * Created by josebaf on 1/13/2016.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A placeholder fragment containing a simple view.
 */
public class ComicFragment extends Fragment implements GetComicTask.ComicConsumer, GetImageTask.ComicImage{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_COMIC_WRAPPER = "comic_wrapper";
    private ComicWrapper mCurrentComicWrapper;
    private String mComicUrlString;
    private String mImageUrlString;
    private TextView mTextView;
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;

    public ComicFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ComicFragment newInstance(ComicWrapper comicWrapper) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COMIC_WRAPPER, comicWrapper);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mCurrentComicWrapper = getArguments().getParcelable(ARG_COMIC_WRAPPER);

            mTextView = (TextView) rootView.findViewById(R.id.section_label);
            mImageView = (ImageView) rootView.findViewById(R.id.comic_view);
            rootView.setBackgroundColor(ContextCompat.getColor(getContext(), mCurrentComicWrapper.getmBackgroudColor()));
            setHasOptionsMenu(true);

            mAttacher = new PhotoViewAttacher(mImageView);

            mComicUrlString = String.format(getString(R.string.url_string), mCurrentComicWrapper.getmIssue());
            new GetComicTask(this).execute(mComicUrlString);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_info:
                showInfo();
                return true;

            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showInfo(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_COMIC_WRAPPER, mCurrentComicWrapper);

        InfoDialogFragment df = new InfoDialogFragment();
        df.setArguments(bundle);
        
        df.show(getFragmentManager(), "Info");
    }

    public void onComicLoaded(Comic comic){
        Log.d("COMIC", "Comic Object\n" + comic);
        mCurrentComicWrapper.setmComic(comic);
        mTextView.setText(comic.getSafe_title());

        mImageUrlString = mCurrentComicWrapper.getmComic().getImg();
        new GetImageTask(this).execute(mImageUrlString);
    }

    public void onImageLoaded(Bitmap bitmap){
        Log.d("IMAGE", "Image Object\n" + bitmap);
        mImageView.setImageBitmap(bitmap);
        mAttacher.update();
    }

}

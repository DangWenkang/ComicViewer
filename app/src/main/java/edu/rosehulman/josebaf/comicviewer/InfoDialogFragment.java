package edu.rosehulman.josebaf.comicviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Created by josebaf on 1/15/2016.
 */
public class InfoDialogFragment extends DialogFragment {

    private ComicWrapper mComicWrapper;

    public InfoDialogFragment(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mComicWrapper = getArguments().getParcelable(ComicFragment.ARG_COMIC_WRAPPER);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_info, null);
        builder.setView(view);
        builder.setTitle(getString(R.string.dialog_info_title_format, mComicWrapper.getmIssue()));
        builder.setMessage(mComicWrapper.getmComic().getAlt());

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}

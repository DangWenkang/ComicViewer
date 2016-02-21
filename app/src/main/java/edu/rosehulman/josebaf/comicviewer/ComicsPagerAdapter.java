package edu.rosehulman.josebaf.comicviewer;

/**
 * Created by josebaf on 1/13/2016.
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class ComicsPagerAdapter extends FragmentPagerAdapter {

    private List<ComicWrapper> mComicWrapperList = new ArrayList();
    private int[] Colors = {
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
    };

    public ComicsPagerAdapter(FragmentManager fm) {
        super(fm);

        for(int i = 0; i < 5; i++){
            this.mComicWrapperList.add(new ComicWrapper(Utils.getRandomCleanIssue(), getRotatedColor(i)));
        }
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return ComicFragment.newInstance(mComicWrapperList.get(position));
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return mComicWrapperList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Issue " + mComicWrapperList.get(position).getmIssue();
    }

    public int getRotatedColor(int position){
        if(position >= 4){
            return Colors[position%4];
        }
        else return Colors[position];
    }

    public void addComic(){
        int position = mComicWrapperList.size();
        int backgroundColor = getRotatedColor(position);
        int issue = Utils.getRandomCleanIssue();

        ComicWrapper comicWrapper = new ComicWrapper(issue, backgroundColor);

        this.mComicWrapperList.add(comicWrapper);
        notifyDataSetChanged();
    }

}

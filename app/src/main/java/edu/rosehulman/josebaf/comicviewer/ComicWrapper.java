package edu.rosehulman.josebaf.comicviewer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by josebaf on 1/14/2016.
 */
public class ComicWrapper implements Parcelable{

    private int mIssue;
    private int mBackgroudColor;
    private Comic mComic;

    public ComicWrapper(){
    }

    public ComicWrapper(int mIssue, int mBackgroudColor) {
        this.mIssue = mIssue;
        this.mBackgroudColor = mBackgroudColor;
    }

    protected ComicWrapper(Parcel in) {
        mIssue = in.readInt();
        mBackgroudColor = in.readInt();
    }

    public static final Creator<ComicWrapper> CREATOR = new Creator<ComicWrapper>() {
        @Override
        public ComicWrapper createFromParcel(Parcel in) {
            return new ComicWrapper(in);
        }

        @Override
        public ComicWrapper[] newArray(int size) {
            return new ComicWrapper[size];
        }
    };

    public int getmIssue() {
        return mIssue;
    }

    public Comic getmComic(){
        return mComic;
    }

    public int getmBackgroudColor(){
        return mBackgroudColor;
    }

    public void setmComic(Comic mComic) {
        this.mComic = mComic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIssue);
        dest.writeInt(mBackgroudColor);
    }
}

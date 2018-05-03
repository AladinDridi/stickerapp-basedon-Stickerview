package com.example.aladin_bureau.tutorial;

import android.os.Parcel;
import android.os.Parcelable;

public class mystickers implements Parcelable {
  public   int img;
    public  mystickers(){

    }
    public mystickers(int img){
        this.img=img;
    }

    protected mystickers(Parcel in) {
        img = in.readInt();
    }

    public static final Creator<mystickers> CREATOR = new Creator<mystickers>() {
        @Override
        public mystickers createFromParcel(Parcel in) {
            return new mystickers(in);
        }

        @Override
        public mystickers[] newArray(int size) {
            return new mystickers[size];
        }
    };

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(img);
    }
}

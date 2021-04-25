package com.example.engineeringmode.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ljt
 * Date: 4/25/21
 * Time: 11:09 AM
 * Description:
 */
@SuppressLint("ParcelCreator")
public class ManualBean implements Parcelable{

    public String name;
    public int age;

    public ManualBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

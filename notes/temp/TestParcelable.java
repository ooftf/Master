package com.ooftf.docking.sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/21 0021
 */
public class TestParcelable implements Parcelable {
    String content;

    protected TestParcelable(Parcel in) {
        content = in.readString();
    }
    protected TestParcelable() {

    }

    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel in) {
            return new TestParcelable(in);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }
}

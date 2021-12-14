package ru.geekbrains.notes.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notes implements Parcelable {
    private final String title;
    private final String description;
    private final boolean like;
    private final Date date;

    public Notes(String title, String description, boolean like, Date date) {
        this.title = title;
        this.description = description;
        this.like = like;
        this.date = date;
    }

    protected Notes(Parcel in) {
        title = in.readString();
        description = in.readString();
        like = in.readByte() != 0;
        date = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeLong(date.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLike() {
        return like;
    }

    public Date getDate() {
        return date;
    }
}

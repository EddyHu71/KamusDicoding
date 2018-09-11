package com.example.user.kamusdicoding;

import android.os.Parcel;
import android.os.Parcelable;

public class Kamus implements Parcelable {
    private int id;
    private String judul;
    private String terjemahan;

    public Kamus() {

    }

    public Kamus(int id, String judul, String terjemahan) {
        this.id = id;
        this.judul = judul;
        this.terjemahan = terjemahan;
    }

    public Kamus(String judul, String terjemahan) {
        this.judul = judul;
        this.terjemahan = terjemahan;
    }

    public Kamus(Parcel p) {
        this.id = p.readInt();
        this.judul = p.readString();
        this.terjemahan = p.readString();
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    public int getId () {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.judul);
        parcel.writeString(this.terjemahan);
    }

    public static final Creator<Kamus> CREATOR = new Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel p) {
            return new Kamus(p);
        }

        @Override
        public Kamus[] newArray(int i) {
            return new Kamus[i];
        }
    };
}

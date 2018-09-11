package com.example.user.kamusdicoding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KamusModel {
    ArrayList<Kamus> kamusArrayList = new ArrayList<>();

    public KamusModel() {

    }

    public ArrayList<Kamus> getKamusArrayList() {
        return kamusArrayList;
    }

    public void setKamusArrayList(ArrayList<Kamus> kamusArrayList) {
        this.kamusArrayList = kamusArrayList;
    }
    public KamusModel(ArrayList<Kamus> kamusArrayList) {
        this.kamusArrayList = kamusArrayList;
    }

}

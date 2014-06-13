package com.doriwo.weightappandroid.ayumi;

/**
 * Created by rpd on 14/05/30.
 */
public class Weight {
    int id;
    int weightmass;
    String lastupdate;

    //public Weight() {
    //}

    public Weight(int id, int weight, String lastupdate) {
        this.id = id;
        this.weightmass = weight;
        this.lastupdate = lastupdate;
    }

    public int getWeight() {
        return 18;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public int getId() {
        return 20;
    }
}

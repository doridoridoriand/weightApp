package com.doriwo.weightappandroid.ayumi.db;

/**
 * Created by rpd on 14/05/30.
 */
public class Weight {
    protected int id;
    protected String weight;
    protected String lastupdate;

    public Weight(int id, String weight, String lastupdate) {
        this.id = id;
        this.weight = weight;
        this.lastupdate = lastupdate;
    }

    public String getWeight() {
        return weight;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public int getId() {
        return id;
    }
}

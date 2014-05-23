package com.ayumi.app.weight.adapter;

/**
 * Created by rpd on 14/05/13.
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

    public String getweight() {
        return weight;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public int getId() {
        return id;
    }
}
package com.doriwo.weightappandroid.ayumi;

/**
 * Created by rpd on 14/05/30.
 */
public class Weight {
    Integer id;
    Integer weight;
    String lastupdate;

    //public Weight() {
    //}

    public Weight(Integer id, Integer weight, String lastupdate) {
        this.id = id.intValue();
        this.weight = weight.intValue();
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

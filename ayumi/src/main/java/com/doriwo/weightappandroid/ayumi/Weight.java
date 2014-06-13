package com.doriwo.weightappandroid.ayumi;

/**
 * Created by rpd on 14/05/30.
 */
public class Weight {
    int _id;
    int _weightmass;
    String _lastupdate;

    public Weight() {
    }

    public Weight(int id, int weightmass, String lastupdate) {
        this._id = id;
        this._weightmass = weightmass;
        this._lastupdate = lastupdate;
    }

    public Weight(int weightmass, String lastupdate) {
        this._weightmass = weightmass;
        this._lastupdate = lastupdate;

    }

    public int get_id(int a) {
        return _id;
    }

    public int get_weightmass(int a) {
        return _weightmass;
    }

    public String get_lastupdate(String a) {
        return _lastupdate;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_weightmass(int _weightmass) {
        this._weightmass = _weightmass;
    }

    public void set_lastupdate(String _lastupdate) {
        this._lastupdate = _lastupdate;
    }
}

package vn.tranthanhtu.sunshine.models;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class ModelLocation extends RealmObject{
    private String location;

    public String getLocation() {
        return location;
    }
}

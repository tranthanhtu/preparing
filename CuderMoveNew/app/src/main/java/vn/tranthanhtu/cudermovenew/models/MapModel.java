package vn.tranthanhtu.cudermovenew.models;

import java.util.ArrayList;
import java.util.List;


public class MapModel {
    private final String square;

    public MapModel(String square) {
        this.square = square;
    }

    public String getSquare() {
        return square;
    }

    public static final List<MapModel> list = new ArrayList<>();
}

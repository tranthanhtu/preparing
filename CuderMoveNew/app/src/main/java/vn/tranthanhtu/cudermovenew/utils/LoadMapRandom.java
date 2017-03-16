package vn.tranthanhtu.cudermovenew.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;


public final class LoadMapRandom {

    private static StringBuilder stringBuilder;

    public static void loadRandomMap(Context context) {
        try {
            List<String> listNameMap = new ArrayList<>();
            String[] list;
            list = context.getAssets().list(Constants.PATH_MAP);
            Collections.addAll(listNameMap, list);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader
                            (
                                    context.getAssets().open(
                                            Constants.PATH_MAP
                                                    + "/"
                                                    + listNameMap.get(new Random()
                                                    .nextInt(listNameMap.size())
                                            )
                                    )
                                    , Constants.UNICODE_FORM)
            );
            stringBuilder = new StringBuilder();
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                stringBuilder.append(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= stringBuilder.length() - 1; i++) {
            MapModel.list.add(new MapModel(stringBuilder.substring(i, i + 1)));
        }
    }
}
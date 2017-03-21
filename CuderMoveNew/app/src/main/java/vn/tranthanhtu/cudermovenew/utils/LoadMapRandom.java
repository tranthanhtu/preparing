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
    public static final List<String> listNameMap = new ArrayList<>();
    private static String nameMap = null;
    private static int rd;
    private static final Random random = new Random();

    public static void loadRandomMap(Context context) {
        String[] list;
        try {
            list = context.getAssets().list(Constants.PATH_MAP);
            Collections.addAll(listNameMap, list);
            rd = random.nextInt(listNameMap.size());
            nameMap = listNameMap.get(rd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadMap(context, nameMap);
    }

    public static void checkRandom(Context context) {
        listNameMap.remove(rd);
        if (listNameMap.size() == 0) {
            loadRandomMap(context);
        }else {
            rd = random.nextInt(listNameMap.size());
            nameMap = listNameMap.get(rd);
            loadMap(context, nameMap);
        }
    }

    public static void loadMap(Context context, String nameMap){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader
                            (
                                    context.getAssets().open(
                                            Constants.PATH_MAP
                                                    + "/"
                                                    + nameMap
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

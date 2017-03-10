package vn.tranthanhtu.cudermovenew.utils;

import android.widget.ImageView;

import vn.tranthanhtu.cudermovenew.models.Constants;

public final class ConvertLocationUtils {
    public static void setLocationStart(int locationStart, ImageView imv) {
        int column;
        int row;
        int check;
        check = (locationStart + Constants.DIFFERENCE_POSITION_IN_LIST) % Constants.SPAN_COUNT;
        if (check == Constants.DIVISIBLE) {
            column = Constants.SPAN_COUNT;
            row = (locationStart + Constants.DIFFERENCE_POSITION_IN_LIST) / Constants.SPAN_COUNT;
//            Log.d(TAG, String.format("setLocationStart: %s", row));
//            Log.d(TAG, String.format("setLocationStart: %s", column));
        } else {
            column = (locationStart + Constants.DIFFERENCE_POSITION_IN_LIST) % Constants.SPAN_COUNT;
            row = (locationStart + Constants.DIFFERENCE_POSITION_IN_LIST) / Constants.SPAN_COUNT + 1;
//            Log.d(TAG, String.format("setLocationStart: %s", row));
//            Log.d(TAG, String.format("setLocationStart: %s", column));
        }
        imv.setX(column * Constants.WIDTH_ITEM_MAP);
        imv.setY(row * Constants.HEIGHT_ITEM_MAP);
    }

    public static int convertStringToPosition(String locationEnd) {
        int lenghString = locationEnd.length();
        String row;
        int position;

        row = locationEnd.substring(1, lenghString);
        char[] charArray = locationEnd.toCharArray();
        int columInt = charArray[0];
        position =
                (columInt - Constants.FIRST_NUMBER_APHABET + Constants.DIFFERENCE_POSITION_IN_APHABET_TABLE)
                        + (Integer.parseInt(row) - 1) * Constants.SPAN_COUNT;

        return position;
    }

    public static String convertPositionToString(int position) {
        int column;
        int row;
        String location;
        int check;
        check = (position + Constants.DIFFERENCE_POSITION_IN_LIST) % Constants.SPAN_COUNT;
        if (check == Constants.DIVISIBLE) {
            column = Constants.SPAN_COUNT;
            row = (position + Constants.DIFFERENCE_POSITION_IN_LIST) / Constants.SPAN_COUNT;

        } else {
            column = (position + Constants.DIFFERENCE_POSITION_IN_LIST) % Constants.SPAN_COUNT;
            row = (position + Constants.DIFFERENCE_POSITION_IN_LIST) / Constants.SPAN_COUNT + 1;

        }
        location = String.valueOf((char)(column + Constants.FIRST_CHARACTER_IN_APHABET_TABLE - 1)) + String.valueOf(row);
        return location;
    }

    public static int convertToLocation(float x, float y) {
        int position;
        position = (int) ((x / Constants.WIDTH_ITEM_MAP) + (y / Constants.HEIGHT_ITEM_MAP - 1) * Constants.SPAN_COUNT);
        return position;
    }
}

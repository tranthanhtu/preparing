package vn.tranthanhtu.cudermovenew.utils;

import android.util.Log;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

public final class ConvertLocationUtils {
    public static void setLocationStart(int locationStart, ImageView imv) {
        int column;
        int row;
        int check;
        check = (locationStart + 1) % 14;
        if (check == 0) {
            column = 14;
            row = (locationStart + 1) / 14;
            Log.d(TAG, String.format("setLocationStart: %s", row));
            Log.d(TAG, String.format("setLocationStart: %s", column));
        } else {
            column = (locationStart + 1) % 14;
            row = (locationStart + 1) / 14 + 1;
            Log.d(TAG, String.format("setLocationStart: %s", row));
            Log.d(TAG, String.format("setLocationStart: %s", column));
        }
        imv.setX(column * 42);
        imv.setY(row * 42);
    }

    public static int convertStringToPosition(String locationEnd) {
        int lenghString = locationEnd.length();
        String row;
        String column;
        int position = 0;

        column = locationEnd.substring(0, 1);
        row = locationEnd.substring(1, lenghString);
        switch (column) {
            case "A":
                position = 1 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "B":
                position = 2 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "C":
                position = 3 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "D":
                position = 4 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "E":
                position = 5 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "F":
                position = 6 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "G":
                position = 7 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "H":
                position = 8 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "I":
                position = 9 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "J":
                position = 10 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "K":
                position = 11 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "L":
                position = 12 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "M":
                position = 13 + (Integer.parseInt(row) - 1) * 14;
                break;
            case "N":
                position = 14 + (Integer.parseInt(row) - 1) * 14;
                break;
        }
        return position;
    }

    public static String convertPositionToString(int position) {
        int column;
        String columnConvert = null;
        int row;
        String location;
        int check;
        check = (position + 1) % 14;
        if (check == 0) {
            column = 14;
            row = (position + 1) / 14;
            Log.d(TAG, String.format("setLocationStart: %s", row));
            Log.d(TAG, String.format("setLocationStart: %s", column));
        } else {
            column = (position + 1) % 14;
            row = (position + 1) / 14 + 1;
            Log.d(TAG, String.format("setLocationStart: %s", row));
            Log.d(TAG, String.format("setLocationStart: %s", column));
            switch (column) {
                case 1:
                    columnConvert = "A";
                    break;
                case 2:
                    columnConvert = "B";
                    break;
                case 3:
                    columnConvert = "C";
                    break;
                case 4:
                    columnConvert = "D";
                    break;
                case 5:
                    columnConvert = "E";
                    break;
                case 6:
                    columnConvert = "F";
                    break;
                case 7:
                    columnConvert = "G";
                    break;
                case 8:
                    columnConvert = "H";
                    break;
                case 9:
                    columnConvert = "I";
                    break;
                case 10:
                    columnConvert = "J";
                    break;
                case 11:
                    columnConvert = "K";
                    break;
                case 12:
                    columnConvert = "L";
                    break;
                case 13:
                    columnConvert = "M";
                    break;
                case 14:
                    columnConvert = "N";
                    break;
            }

        }
        location = columnConvert + String.valueOf(row);
        return location;
    }

    public static int convertToLocation (float x, float y){
        int position;
        position = (int) ((x / 42) + (y / 42 - 1) * 14);
        return position;
    }
}

package vn.tranthanhtu.cudermovenew.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.tranthanhtu.cudermovenew.controllers.MapAdapter;
import vn.tranthanhtu.cudermovenew.models.MapModel;

import static android.content.ContentValues.TAG;


public final class AnimationUtils {

    private static final String MOVE_LEFT = "moveLeft";
    private static final String MOVE_RIGHT = "moveRight";
    private static final String MOVE_UP = "moveUp";
    private static final String MOVE_DOWN = "moveDown";
    private static float x;
    private static float y;

    public static void playAnimation(ArrayList<String> listMove, ImageView imageView, MapAdapter adapter,Context context, String edt){
        x = imageView.getX();
        y = imageView.getY();
        createAnimatorList(listMove, imageView, adapter, context, edt);
        Log.d(TAG, String.format("onItemClick: %s", x));
        Log.d(TAG, String.format("onItemClick: %s", y));

    }

    private static void createAnimatorList(ArrayList<String> listMove, ImageView imageView, MapAdapter adapter, Context context, String edt) {
        for (int count = 0; count < listMove.size(); count++) {
            MapModel.list.set(ConvertLocationUtils.convertToLocation(x, y) - 1, new MapModel("3"));
            adapter.notifyDataSetChanged();
            Log.d(TAG, String.format("onItemClick: %s", x));
            Log.d(TAG, String.format("onItemClick: %s", y));
            if (listMove.get(count).equals(MOVE_LEFT)) {
                addAnimatorLeft(imageView, count);
            }
            else if (listMove.get(count).equals(MOVE_RIGHT)) {
                addAnimatorRight(imageView, count);

            }
            else if (listMove.get(count).equals(MOVE_UP)) {
                addAnimatorUp(imageView, count);
            }
            else if (listMove.get(count).equals(MOVE_DOWN)) {
                addAnimatorDown(imageView, count);
            }
            if (MapModel.list.get(ConvertLocationUtils.convertToLocation(x, y) - 1).getSquare().equals("1")){
                NotificationUtils.notification(
                                    "Oops!Cuder đã bỏ lỡ cuộc họp!",
                                    context
                            );
                            count = listMove.size() - 1;
            }
            if (ConvertLocationUtils.convertToLocation(x, y) == ConvertLocationUtils.convertStringToPosition(edt)){
                NotificationUtils.notification(
                                    "Chúc mừng!Cuder đã tới cuộc họp!",
                                    context
                            );
                            count = listMove.size() - 1;
            }
        }
    }

    private static void addAnimatorDown(ImageView imv, int count) {
        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(
                imv,
                "y",
                y,
                y + 42);
        y = y + 42;
        animatorDown.setDuration(1500);
        animatorDown.setStartDelay(1500*(count));
        animatorDown.start();
    }

    private static void addAnimatorUp(ImageView imv, int count) {
        ObjectAnimator animatorUp = ObjectAnimator.ofFloat(
                imv,
                "y",
                y,
                y - 42);
        y = y - 42;
        animatorUp.setDuration(1500);
        animatorUp.setStartDelay(1500*count);
        animatorUp.start();

    }

    private static void addAnimatorRight(ImageView imv, int count) {
        ObjectAnimator animatorRight = ObjectAnimator.ofFloat(
                imv,
                "x",
                x,
                x + 42);
        x = x + 42;
        animatorRight.setDuration(1500);
        animatorRight.setStartDelay(1500*(count));
        animatorRight.start();

    }

    private static void addAnimatorLeft(ImageView imv, int count) {
        ObjectAnimator animatorLeft = ObjectAnimator.ofFloat(
                imv,
                "x",
                x,
                x - 42);
        x = x - 42;
        animatorLeft.setDuration(1500);
        animatorLeft.setStartDelay(1500*(count));
        animatorLeft.start();

    }


}

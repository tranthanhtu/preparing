package vn.tranthanhtu.cudermovenew.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.tranthanhtu.cudermovenew.controllers.MapAdapter;
import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;


public final class AnimationUtils {

    private static float x;
    private static float y;

    public static void playAnimation(ArrayList<String> listMove, ImageView imageView, MapAdapter adapter,Context context, String edt){
        x = imageView.getX();
        y = imageView.getY();
        createAnimatorList(listMove, imageView, adapter, context, edt);
//        Log.d(TAG, String.format("onItemClick: %s", x));
//        Log.d(TAG, String.format("onItemClick: %s", y));

    }

    private static void createAnimatorList(ArrayList<String> listMove, ImageView imageView, MapAdapter adapter, Context context, String edt) {
        for (int count = 0; count < listMove.size(); count++) {
            MapModel.list.set(ConvertLocationUtils.convertToLocation(x, y) - 1, new MapModel(Constants.WAY_WILL_GO));
            adapter.notifyDataSetChanged();
//            Log.d(TAG, String.format("onItemClick: %s", x));
//            Log.d(TAG, String.format("onItemClick: %s", y));
            if (listMove.get(count).equals(Constants.MOVE_LEFT)) {
                addAnimatorLeft(imageView, count);
            }
            else if (listMove.get(count).equals(Constants.MOVE_RIGHT)) {
                addAnimatorRight(imageView, count);

            }
            else if (listMove.get(count).equals(Constants.MOVE_UP)) {
                addAnimatorUp(imageView, count);
            }
            else if (listMove.get(count).equals(Constants.MOVE_DOWN)) {
                addAnimatorDown(imageView, count);
            }
            if (MapModel.list.get(ConvertLocationUtils.convertToLocation(x, y) - 1).getSquare().equals(Constants.IMPEDIMENT)){
                NotificationUtils.notification(
                        Constants.NOTI_GO_MEETING_FAIL,
                                    context
                            );
                            count = listMove.size() - 1;
            }
            if (ConvertLocationUtils.convertToLocation(x, y) == ConvertLocationUtils.convertStringToPosition(edt)){
                NotificationUtils.notification(
                                    Constants.NOTI_GO_MEETING_COMPLETE,
                                    context
                            );
                            count = listMove.size() - 1;
            }
        }
    }

    private static void addAnimatorDown(ImageView imv, int count) {
        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(
                imv,
                Constants.PROPERTY_ANIMATION_Y,
                y,
                y + Constants.HEIGHT_ITEM_MAP);
        y = y + Constants.HEIGHT_ITEM_MAP;
        animatorDown.setDuration(Constants.DELAY_ANIMATION);
        animatorDown.setStartDelay(Constants.DELAY_ANIMATION*(count));
        animatorDown.start();
    }

    private static void addAnimatorUp(ImageView imv, int count) {
        ObjectAnimator animatorUp = ObjectAnimator.ofFloat(
                imv,
                Constants.PROPERTY_ANIMATION_Y,
                y,
                y - Constants.HEIGHT_ITEM_MAP);
        y = y - Constants.HEIGHT_ITEM_MAP;
        animatorUp.setDuration(Constants.DELAY_ANIMATION);
        animatorUp.setStartDelay(Constants.DELAY_ANIMATION*count);
        animatorUp.start();

    }

    private static void addAnimatorRight(ImageView imv, int count) {
        ObjectAnimator animatorRight = ObjectAnimator.ofFloat(
                imv,
                Constants.PROPERTY_ANIMATION_X,
                x,
                x + Constants.WIDTH_ITEM_MAP);
        x = x + Constants.WIDTH_ITEM_MAP;
        animatorRight.setDuration(Constants.DELAY_ANIMATION);
        animatorRight.setStartDelay(Constants.DELAY_ANIMATION*(count));
        animatorRight.start();

    }

    private static void addAnimatorLeft(ImageView imv, int count) {
        ObjectAnimator animatorLeft = ObjectAnimator.ofFloat(
                imv,
                Constants.PROPERTY_ANIMATION_X,
                x,
                x - Constants.WIDTH_ITEM_MAP);
        x = x - Constants.WIDTH_ITEM_MAP;
        animatorLeft.setDuration(Constants.DELAY_ANIMATION);
        animatorLeft.setStartDelay(Constants.DELAY_ANIMATION*(count));
        animatorLeft.start();

    }


}

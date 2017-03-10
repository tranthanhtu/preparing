package vn.tranthanhtu.cudermovenew.controllers;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import vn.tranthanhtu.cudermovenew.R;
import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;

class MapViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvSquare;
    MapViewHolder(View itemView) {
        super(itemView);
        tvSquare = (TextView) itemView.findViewById(R.id.tv_square);
    }

    public void bind(MapModel mapModel){
        if (TextUtils.equals(mapModel.getSquare(), Constants.WAY)){
            tvSquare.setText(Constants.NULL_TEXT);
            tvSquare.setBackgroundResource(android.R.color.white);
        }if (TextUtils.equals(mapModel.getSquare(), Constants.IMPEDIMENT)){
            tvSquare.setText(Constants.NULL_TEXT);
            tvSquare.setBackgroundResource(android.R.color.holo_blue_bright);
        }if (TextUtils.equals(mapModel.getSquare(), Constants.MEETING)){
            tvSquare.setText(Constants.NULL_TEXT);
            tvSquare.setBackgroundResource(R.color.colorAccent);
        }if (TextUtils.equals(mapModel.getSquare(), Constants.WAY_WILL_GO)){
            tvSquare.setText(Constants.NULL_TEXT);
            tvSquare.setBackgroundResource(R.color.move_step);
        }

    }


}

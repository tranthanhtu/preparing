package vn.tranthanhtu.cudermovenew.controllers;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import vn.tranthanhtu.cudermovenew.R;
import vn.tranthanhtu.cudermovenew.models.MapModel;

class MapViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvSquare;

    MapViewHolder(View itemView) {
        super(itemView);
        tvSquare = (TextView) itemView.findViewById(R.id.tv_square);
    }

    void bind(MapModel mapModel){
        if (mapModel.getSquare().equals("0")){
            tvSquare.setText("");
            tvSquare.setBackgroundResource(android.R.color.white);
        }if (TextUtils.equals(mapModel.getSquare(), "1")){
            tvSquare.setText("");
            tvSquare.setBackgroundResource(android.R.color.holo_blue_bright);
        }if (mapModel.getSquare().equals("2")){
            tvSquare.setText("");
            tvSquare.setBackgroundResource(R.color.colorAccent);
        }if (mapModel.getSquare().equals("3")){
            tvSquare.setText("");
            tvSquare.setBackgroundResource(R.color.move_step);
        }
    }


}

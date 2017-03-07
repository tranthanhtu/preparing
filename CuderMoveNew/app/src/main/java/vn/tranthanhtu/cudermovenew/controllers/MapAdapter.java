package vn.tranthanhtu.cudermovenew.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tranthanhtu.cudermovenew.R;
import vn.tranthanhtu.cudermovenew.models.MapModel;

import static vn.tranthanhtu.cudermovenew.models.MapModel.list;


public class MapAdapter extends RecyclerView.Adapter<MapViewHolder> {


    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_gridview, parent, false);
        return new MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {
        MapModel mapModel = list.get(position);
        holder.bind(mapModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

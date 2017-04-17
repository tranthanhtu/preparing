package vn.tranthanhtu.sunshine.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.viewholders.NextDayViewHolder;

import static vn.tranthanhtu.sunshine.models.NextDayModel.list;


public class NextDayWeatherAdapter extends RecyclerView.Adapter<NextDayViewHolder> {

    @Override
    public NextDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_next_day_weather, parent, false);
        return new NextDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NextDayViewHolder holder, int position) {
        NextDayModel dayModel = list.get(position);
        holder.bind(dayModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

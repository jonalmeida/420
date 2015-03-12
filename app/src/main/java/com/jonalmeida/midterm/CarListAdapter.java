package com.jonalmeida.midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class CarListAdapter extends ArrayAdapter<CarInfo> {

    private static final String TAG = CarListAdapter.class.toString();

    private Context context;
    private int mListItemLayoutResId;
    private List<CarInfo> dataList;

    public CarListAdapter(Context context, int resource) {
        super(context, resource);
        Log.v(TAG, "CarListAdapter initializing..");
        this.context = context;
        this.mListItemLayoutResId = resource;
        this.dataList = new LinkedList<>();
    }

    public CarListAdapter(Context context, int resource, List<CarInfo> objects) {
        super(context, resource, objects);
        Log.v(TAG, "CarListAdapter initializing..");
        this.context = context;
        this.mListItemLayoutResId = resource;
        this.dataList = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.v(TAG, "getView called");
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = inflater.inflate(R.layout.car_list_item, parent, false);

        ImageView thumbnail = (ImageView) listItemView.findViewById(R.id.thumbnail);

        int possibleThumbnail = parent.getResources()
                .getIdentifier("thumbnail_" + position, "drawable", context.getPackageName());
        if (possibleThumbnail == 0) {
            thumbnail.setImageResource(R.drawable.placeholder_thumbnail);
        } else {
            thumbnail.setImageResource(possibleThumbnail);
        }

        CarInfo carInfo = dataList.get(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(carInfo.getName());

        TextView speed = (TextView) listItemView.findViewById(R.id.details);
        speed.setText(carInfo.getSpeed());

        TextView model = (TextView) listItemView.findViewById(R.id.model);
        model.setText(carInfo.getModel());

        return listItemView;
    }

    @Override
    public void add(CarInfo carInfo) {
        dataList.add(carInfo);
    }

}

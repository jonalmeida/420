package com.jonalmeida.project420;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ContactListAdapter extends ArrayAdapter<ContactItem> {

    private Context context;
    private int listItemResId;

    public ContactListAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, (ContactItem[]) objects);
        this.context = context;
        this.listItemResId = resource;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = inflater.inflate(this.listItemResId, parent, false);

        ContactItem contact = getItem(position);

        ImageView image = (ImageView) view.findViewById(R.id.contact_image);
        image.setImageResource(R.drawable.default_icon);

        return listItemView;
    }
}

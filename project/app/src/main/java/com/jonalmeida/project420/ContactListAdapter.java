package com.jonalmeida.project420;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListAdapter extends ArrayAdapter<ContactItem> {

    private Context context;
    private int listItemResId;
    private LayoutInflater inflater;

    public ContactListAdapter(Context context, int resource, ArrayList<ContactItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listItemResId = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View listItemView = inflater.inflate(this.listItemResId, null);

        ContactItem contact = getItem(position);

//        ImageView image = (ImageView) view.findViewById(R.id.contact_image);
//        image.setImageResource(R.drawable.default_icon);

        TextView name = (TextView) listItemView.findViewById(R.id.contact_name);
        name.setText(contact.getDisplayName());

        TextView desc = (TextView) listItemView.findViewById(R.id.last_message);
        desc.setText(contact.getLastConversationLine());

        return listItemView;
    }
}

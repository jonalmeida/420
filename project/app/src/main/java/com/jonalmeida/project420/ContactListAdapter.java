package com.jonalmeida.project420;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListAdapter extends ArrayAdapter<ContactItem> {

    private static final String TAG = ContactListAdapter.class.toString();

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

        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.contact_list_item, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) view.findViewById(R.id.contact_name);
            holder.lastMessageView = (TextView) view.findViewById(R.id.last_message);
            holder.imageView = (ImageView) view.findViewById(R.id.layout_circle_mask);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ContactItem contact = getItem(position);

        holder.nameView.setText(contact.getDisplayName());
        holder.lastMessageView.setText(contact.getLastConversationLine());

        if (holder.imageView != null) {
            // ImageLoader task goes here
            Log.d(TAG, "Trying to get profile image of " + contact);
            new ImageViewAsyncLoader(context, holder.imageView).execute(contact.getDisplayName());
        }

        return view;
    }

    public static class ViewHolder {
        TextView nameView;
        TextView lastMessageView;
        ImageView imageView;
    }
}

package com.jonalmeida.project420;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MessageThreadAdapter extends RecyclerView.Adapter<MessageThreadAdapter.TextMessageViewHolder> {

    private List<TextMessage> messageThread;

    public MessageThreadAdapter(List<TextMessage> messageThread) {
        this.messageThread = messageThread;
    }

    /**
     * Called when RecyclerView needs a new {@link TextMessageViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(TextMessageViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(TextMessageViewHolder, int)
     */
    @Override
    public TextMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_message, parent, false);
        return new TextMessageViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link TextMessageViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link TextMessageViewHolder#getPosition()} which will have the
     * updated position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(TextMessageViewHolder holder, int position) {
        TextMessage tm = messageThread.get(position);
        holder.messageTextView.setText(tm.message);
        holder.nameTextView.setText(tm.name);
        holder.timestampTextView.setText(Long.toString(tm.timestamp));
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return messageThread.size();
    }

    public static class TextMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView messageTextView;
        protected TextView timestampTextView;

        public TextMessageViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.message_name);
            messageTextView = (TextView) v.findViewById(R.id.message_text);
            timestampTextView = (TextView) v.findViewById(R.id.message_timestamp);
        }
    }
}

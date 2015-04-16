package com.jonalmeida.project420;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

public class MessageThreadAdapter extends RecyclerView.Adapter<MessageThreadAdapter.TextMessageViewHolder> {

    private static final String TAG = "MessageThreadAdapter";

    private List<TextMessage> messageThread;

    private PrettyTime prettyTime;

    private View itemView;
    private TextMessageViewHolder itemViewHolder;

    public MessageThreadAdapter(List<TextMessage> messageThread) {
        this.messageThread = messageThread;
        this.prettyTime = new PrettyTime();
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
        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_message, parent, false);
        itemViewHolder = new TextMessageViewHolder(itemView);
        return itemViewHolder;
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
        holder.timestampTextView.setText(prettyTime.format(new Date(tm.timestamp)));
        Log.d(TAG, "Message from " + (tm.receipient ? "receipient" : "me"));
        Log.d(TAG, "ItemView is " + (itemView == null ?  "null" : "not null"));
        holder.bindMessage(tm);
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

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static class TextMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView messageTextView;
        protected TextView timestampTextView;
        protected CardView itemView;

        public TextMessageViewHolder(View v) {
            super(v);
            itemView = (CardView) v.findViewById(R.id.card_view);
            nameTextView = (TextView) v.findViewById(R.id.message_name);
            messageTextView = (TextView) v.findViewById(R.id.message_text);
            timestampTextView = (TextView) v.findViewById(R.id.message_timestamp);
        }

        public void bindMessage(TextMessage tm) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
            final int backgroundColor;
            final int fontColor;
            if (tm.receipient) {
                backgroundColor = R.color.accent;
                fontColor = android.R.color.primary_text_dark;
                layoutParams.setMargins(
                        Math.round(convertDpToPixel(26, itemView.getContext())),
                        Math.round(convertDpToPixel(5, itemView.getContext())),
                        Math.round(convertDpToPixel(0, itemView.getContext())),
                        Math.round(convertDpToPixel(5, itemView.getContext()))
                );
            } else {
                backgroundColor = android.R.color.background_light;
                fontColor = android.R.color.primary_text_light;
                layoutParams.setMargins(
                        Math.round(convertDpToPixel(0, itemView.getContext())),
                        Math.round(convertDpToPixel(5, itemView.getContext())),
                        Math.round(convertDpToPixel(26, itemView.getContext())),
                        Math.round(convertDpToPixel(5, itemView.getContext()))
                );
            }
            View cardLayout = itemView.findViewById(R.id.card_layout);
            cardLayout.setBackgroundColor(itemView.getResources().getColor(backgroundColor));

            int colorAsInt = itemView.getResources().getColor(fontColor);

            ((TextView) itemView.findViewById(R.id.message_name)).setTextColor(colorAsInt);
            ((TextView) itemView.findViewById(R.id.message_text)).setTextColor(colorAsInt);
            ((TextView) itemView.findViewById(R.id.message_timestamp)).setTextColor(colorAsInt);
            itemView.setLayoutParams(layoutParams);
        }
    }
}

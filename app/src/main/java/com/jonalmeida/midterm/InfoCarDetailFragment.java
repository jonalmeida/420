package com.jonalmeida.midterm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.jonalmeida.midterm.data.DummyData;

/**
 * A fragment representing a single InfoCar detail screen.
 * This fragment is either contained in a {@link InfoCarListActivity}
 * in two-pane mode (on tablets) or a {@link InfoCarDetailActivity}
 * on handsets.
 */
public class InfoCarDetailFragment extends Fragment implements InfoCarDetailActivity.OnFragmentCallDealerListener {
    private static final String TAG = InfoCarDetailFragment.class.toString();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private CarInfo mItem;
    private int imageIndex;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private View rootView;

    private ImageView imageView;

    public InfoCarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            imageIndex = getArguments().getInt(ARG_ITEM_ID);
            // `imageIndex` can also be used to retrieve the mItem.
            mItem = DummyData.DATA.get(imageIndex);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_infocar_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textView_dealer_contact))
                    .setText(mItem.getDealerContact());
            ((TextView) rootView.findViewById(R.id.textView_finance_rate))
                    .setText(mItem.getFinanceRate());
            ((TextView) rootView.findViewById(R.id.textView_price))
                    .setText(mItem.getPrice());
            ((TextView) rootView.findViewById(R.id.textView_lease_rate))
                    .setText(mItem.getLeaseRate());
        }

        // Check for image and set it here, otherwise the placeholder image shows up
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.setImageResource(getResources()
                .getIdentifier("full_" + imageIndex, "drawable", getActivity().getPackageName()));
        //imageView.setImageDrawable(getResources().getDrawable(R.drawable.placeholder_thumbnail));

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        imageView.setImageDrawable(null);
    }

    @Override
    public void callDealer() {
        TextView callText = (TextView) rootView.findViewById(R.id.textView_dealer_contact);
        if (callText != null) {
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getActivity(), callText.getText(), duration);
            toast.show();
        } else {
            Log.v(TAG, "Couldn't find dealer's contact information.");
        }
    }
}

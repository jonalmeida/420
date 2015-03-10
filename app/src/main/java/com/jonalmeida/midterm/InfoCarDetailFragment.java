package com.jonalmeida.midterm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private View rootView;

    public InfoCarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyData.DATA.get(getArguments().getInt(ARG_ITEM_ID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_infocar_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.infocar_detail)).setText(mItem.getModel());
        }

        return rootView;
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

package com.jonalmeida.project420;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Contact detail screen.
 * This fragment is either contained in a {@link ContactListActivity}
 * in two-pane mode (on tablets) or a {@link ContactDetailActivity}
 * on handsets.
 */
public class ContactDetailFragment extends Fragment {

    private static final String TAG = "ContactDetailFragment";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_THREAD_ID = Telephony.Sms.THREAD_ID;
    public static final String ARG_PERSON = Telephony.Sms.PERSON;
    public static final String ARG_ADDRESS = Telephony.Sms.ADDRESS;
    public static final String ARG_DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;

    /**
     * The dummy content this fragment is presenting.
     */
    private String personId;
    private int threadId;
    private String address;
    private String display_name;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PERSON)) {
            personId = getArguments().getString(ARG_PERSON);
        }
        if (getArguments().containsKey(ARG_DISPLAY_NAME)) {
            display_name = getArguments().getString(ARG_DISPLAY_NAME);
        }
        if (getArguments().containsKey(ARG_THREAD_ID)) {
            threadId = getArguments().getInt(ARG_THREAD_ID);
        }
        if (getArguments().containsKey(ARG_ADDRESS)) {
            address = getArguments().getString(ARG_ADDRESS);
            Log.v("DetailFragment", "Got the address in args: " + address);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (address != null) {
            ((TextView) rootView.findViewById(R.id.contact_detail)).setText(address);
        }

        return rootView;
    }
}

package com.jonalmeida.project420;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComposeSmsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComposeSmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComposeSmsFragment extends Fragment {
    private static final String TAG = "ComposeSmsFragment";

    /**
     * For Contact picking
     */
    private static final int CONTACT_PICKER_RESULT = 1001;
    private EditText contactSearch;

    /**
     * For checking if we're in a master-details view
     */
    public static final String ARG_IS_TWO_PANE = "two_pane";

    private boolean mIsTwoPane = false;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param twoPane Parameter 1.
     * @return A new instance of fragment ComposeSmsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComposeSmsFragment newInstance(boolean twoPane) {
        ComposeSmsFragment fragment = new ComposeSmsFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_TWO_PANE, twoPane);
        fragment.setArguments(args);
        return fragment;
    }

    public ComposeSmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsTwoPane = getArguments().getBoolean(ARG_IS_TWO_PANE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_compose_sms, container, false);
        contactSearch = (EditText) v.findViewById(R.id.compose_contact_search_edit_text);
        contactSearchEditText(v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void contactSearchEditText(View v) {

        contactSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(pickContactIntent, CONTACT_PICKER_RESULT);
                } else {
                    // use spinner
                }
            }
        });

        contactSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "Just typed: " + editable.toString());
                // TODO: Interact with spinner here
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String phoneNo = null;
        Uri uri = data.getData();
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        phoneNo = cursor.getString(phoneIndex);
        Log.d(TAG, "We got this number: " + phoneNo);

        if (phoneNo != null) {
            // TODO: Check if the number exists in thread
            // If true, open the new activity/fragment with that number

            // Else, new conversation with number in it.
            contactSearch.append(phoneNo);
        }

        cursor.close();
    }

}

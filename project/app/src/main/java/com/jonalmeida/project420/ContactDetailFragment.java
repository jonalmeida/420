package com.jonalmeida.project420;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

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
     * Contact specific data.
     */
    private String personId;
    private int threadId = -1;
    private String address;
    private String display_name;
    private AccountUtils.UserProfile userProfile;
    private ContactItem contactItem;
    private ImageView contactImage;
    private SmsSender smsSender;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactItem = new ContactItem();

        if (getArguments().containsKey(ARG_PERSON)) {
            personId = getArguments().getString(ARG_PERSON);
        }
        if (getArguments().containsKey(ARG_DISPLAY_NAME)) {
            display_name = getArguments().getString(ARG_DISPLAY_NAME);
            contactItem.setDisplayName(display_name);
        }
        if (getArguments().containsKey(ARG_THREAD_ID)) {
            threadId = getArguments().getInt(ARG_THREAD_ID);
            contactItem.setThreadId(threadId);
        }
        if (getArguments().containsKey(ARG_ADDRESS)) {
            address = getArguments().getString(ARG_ADDRESS);
            contactItem.setAddress(address);
            Log.v("DetailFragment", "Got the address in args: " + address);
        }

        userProfile = AccountUtils.getUserProfile(getActivity());

        smsSender = new SmsSender(getActivity(), threadId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        // Show the dummy content as text in a TextView.
        //if (address != null) {
        //    ((TextView) rootView.findViewById(R.id.contact_detail)).setText(address);
        //}

        new ContactInfoAsyncLoader(getActivity(), contactItem, contactImage, null)
                .execute(address);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setStackFromEnd(true);
        recList.setLayoutManager(llm);
        recList.setAdapter(new MessageThreadAdapter(getConversationThread()));


        // Alternatively, for scrolling to the bottom..
        //recList.scrollToPosition(threadMessage.size()-1);

        setNewEditTextListener(rootView);
        setSendButtonListener(rootView);

        return rootView;
    }

    private List<TextMessage> getConversationThread() {
        // Getting threaded conversation
        // If person == null, the person is you.

        // Use as a wildcard
        //final String[] projection = new String[]{"*"};

        List<TextMessage> threadMessage = new LinkedList<>();

        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri uri = Uri.parse("content://mms-sms/conversations/" + threadId);
        Cursor cursor = contentResolver.query(
                uri, new String[] {"body", "person", "address", "normalized_date"},
                null, null,
                "normalized_date"
        );
        if (cursor.moveToFirst()) {
            do {
                // Print thread
                //String msgData = "";
                //for(int idx=0;idx<cursor.getColumnCount();idx++)
                //{
                //    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                //}
                //Log.d(TAG, msgData);

                TextMessage tm = new TextMessage();

                final int person_type = cursor.getType(cursor.getColumnIndex("person"));
                if (person_type != Cursor.FIELD_TYPE_NULL) {
                    tm.receipient = true;
                    tm.name = cursor.getString(cursor.getColumnIndex("address"));
                    try {
                        tm.name = this.contactItem.getDisplayName();
                    } catch (NullPointerException e) {
                        Log.e(TAG, "There wasn't a displayable name passed to the message thread."
                            + " We're using this phone number instead"
                        );
                    }
                } else {

                    if (userProfile != null) {
                        if (userProfile.possibleNames().size() > 0) {
                            tm.name = userProfile.possibleNames().get(0);
                        } else {
                            tm.name = userProfile.possiblePhoneNumbers().get(0);
                        }
                    }
                }

                tm.message = cursor.getString(cursor.getColumnIndex("body"));
                tm.timestamp = Long.parseLong(cursor.getString(cursor.getColumnIndex("normalized_date")));


                threadMessage.add(tm);

            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "Cursor is empty. Flop like a fish!");
        }
        cursor.close();

        return threadMessage;
    }

    private void setNewEditTextListener(View v) {
        EditText editText = (EditText) v.findViewById(R.id.edit_text_compose);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() != 0) {

                }
            }
        });
    }

    private void setSendButtonListener(View v) {
        ImageView sendButton = (ImageView) v.findViewById(R.id.send_button);
        final EditText editText = (EditText) v.findViewById(R.id.edit_text_compose);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked!");
                if (editText.getText().length() != 0) {
                    smsSender.sendSmsMessage(editText.getText().toString(), address);
                    editText.setText("");
                }
            }
        });
    }
}

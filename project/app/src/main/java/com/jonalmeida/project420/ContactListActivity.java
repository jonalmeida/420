package com.jonalmeida.project420;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;


/**
 * An activity representing a list of Contacts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ContactDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ContactListFragment} and the item details
 * (if present) is a {@link ContactDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ContactListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ContactListActivity extends ActionBarActivity
        implements ContactListFragment.Callbacks, ComposeSmsFragment.OnFragmentInteractionListener {

    private static final String TAG = "ContactListActivity";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        if (findViewById(R.id.contact_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ContactListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.contact_list))
                    .setActivateOnItemClick(true);
        }

        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

        setTitle(R.string.conversation_list_name);

        setFabIconListener();
        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link ContactListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String display_name, int threadId, String address) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ContactDetailFragment.ARG_DISPLAY_NAME, display_name);
            arguments.putInt(ContactDetailFragment.ARG_THREAD_ID, threadId);
            arguments.putString(ContactDetailFragment.ARG_ADDRESS, address);
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contact_detail_container, fragment)
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ContactDetailActivity.class);
            Log.d(TAG, "Adding address: " + address);
            detailIntent.putExtra(ContactDetailFragment.ARG_DISPLAY_NAME, display_name);
            detailIntent.putExtra(ContactDetailFragment.ARG_THREAD_ID, threadId);
            detailIntent.putExtra(ContactDetailFragment.ARG_ADDRESS, address);
            startActivity(detailIntent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    private void setFabIconListener() {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_1);
            fab.setOnCheckedChangeListener(new FloatingActionButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(FloatingActionButton fabView, boolean isChecked) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putBoolean(ComposeSmsFragment.ARG_IS_TWO_PANE, mTwoPane);
                        ComposeSmsFragment fragment = new ComposeSmsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contact_detail_container, fragment)
                                .commit();
                    } else {
                        Intent composeIntent = new Intent(fabView.getContext(), ComposeSmsActivity.class);
                        composeIntent.putExtra(ComposeSmsFragment.ARG_IS_TWO_PANE, mTwoPane);
                        startActivity(composeIntent);
                    }
                }
            });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

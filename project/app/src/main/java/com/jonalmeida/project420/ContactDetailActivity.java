package com.jonalmeida.project420;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * An activity representing a single Contact detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ContactListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ContactDetailFragment}.
 */
public class ContactDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ContactDetailFragment.ARG_DISPLAY_NAME,
                    getIntent().getStringExtra(ContactDetailFragment.ARG_DISPLAY_NAME));
            arguments.putInt(ContactDetailFragment.ARG_THREAD_ID,
                    getIntent().getIntExtra(ContactDetailFragment.ARG_THREAD_ID, -1));
            arguments.putString(ContactDetailFragment.ARG_ADDRESS,
                    getIntent().getStringExtra(ContactDetailFragment.ARG_ADDRESS));


            setTitle(getIntent().getStringExtra(ContactDetailFragment.ARG_DISPLAY_NAME));

            ContactItem contactItem = new ContactItem();

            new ContactInfoAsyncLoader(this, contactItem, getSupportActionBar())
                    .execute(getIntent().getStringExtra(ContactDetailFragment.ARG_ADDRESS));


            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_detail_container, fragment)
                    .commit();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ContactListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    private TextView getTextViewTitle() {
        TextView foo = (TextView) this.findViewById(R.id.title);

        if (foo != null) {
            foo.setText("FOOWOW");
        }

        return (TextView) findViewById(R.id.action_bar_title);
    }
}

package com.jonalmeida.midterm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;


/**
 * An activity representing a single InfoCar detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link InfoCarListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link InfoCarDetailFragment}.
 */
public class InfoCarDetailActivity extends ActionBarActivity {

    private static final String TAG = InfoCarDetailActivity.class.toString();

    private OnFragmentCallDealerListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infocar_detail);

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

        // TODO: Get the listView from this bundle to avoid duplication

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(InfoCarDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(InfoCarDetailFragment.ARG_ITEM_ID, -1));
            InfoCarDetailFragment fragment = new InfoCarDetailFragment();
            fragment.setArguments(arguments);
            mListener = fragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.infocar_detail_container, fragment)
                    .commit();
        }

        ActivityTracker.getInstance().ourActivityStack.add(this);

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
            Log.v(TAG, "Button clicked.");
            navigateUpTo(new Intent(this, InfoCarListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewButtonClicked(View view) {
        navigateUpTo(new Intent(this, InfoCarListActivity.class));
    }

    public void exitButtonClicked(View view) {
        Log.v(TAG, "Exiting..");
        for (Activity e : ActivityTracker.getInstance().ourActivityStack) {
            e.finish();
        }
    }

    public void callDealerButtonClicked(View view) {
        if (mListener != null) {
            mListener.callDealer();
        }
    }

    public interface OnFragmentCallDealerListener {
        public void callDealer();
    }

}

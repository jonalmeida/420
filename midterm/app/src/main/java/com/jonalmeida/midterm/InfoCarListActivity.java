package com.jonalmeida.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * An activity representing a list of InfoCars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link InfoCarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link InfoCarListFragment} and the item details
 * (if present) is a {@link InfoCarDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link InfoCarListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class InfoCarListActivity extends FragmentActivity
        implements InfoCarListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infocar_list);

        if (findViewById(R.id.infocar_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((InfoCarListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.infocar_list))
                    .setActivateOnItemClick(true);
        }

        ActivityTracker.getInstance().ourActivityStack.add(this);

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link InfoCarListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(InfoCarDetailFragment.ARG_ITEM_ID, id);
            InfoCarDetailFragment fragment = new InfoCarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.infocar_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, InfoCarDetailActivity.class);
            detailIntent.putExtra(InfoCarDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public void onIndexItemSelected(int index) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(InfoCarDetailFragment.ARG_ITEM_ID, index);
            InfoCarDetailFragment fragment = new InfoCarDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.infocar_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, InfoCarDetailActivity.class);
            detailIntent.putExtra(InfoCarDetailFragment.ARG_ITEM_ID, index);
            startActivity(detailIntent);
        }
    }
}

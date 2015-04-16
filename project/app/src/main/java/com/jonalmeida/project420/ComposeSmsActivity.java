package com.jonalmeida.project420;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class ComposeSmsActivity extends FragmentActivity
        implements ComposeSmsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_sms);

        if (savedInstanceState == null) {
            // Do stuff with the bundle
            ComposeSmsFragment fragment = new ComposeSmsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.compose_sms_container, fragment)
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
        //overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

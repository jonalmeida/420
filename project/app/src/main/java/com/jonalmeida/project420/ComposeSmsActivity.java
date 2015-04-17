package com.jonalmeida.project420;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ComposeSmsActivity extends FragmentActivity
        implements ComposeSmsFragment.OnFragmentInteractionListener {

    @Override
    protected void onResume() {
        super.onResume();

        final String myPackageName = getPackageName();
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            // App is not default.
            // Show the "not currently set as the default SMS app" interface
//            View viewGroup = findViewById(R.id.not_default_app);
//            viewGroup.setVisibility(View.VISIBLE);
//
//            // Set up a button that allows the user to change the default SMS app
//            Button button = (Button) findViewById(R.id.change_default_app);
//            button.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    Intent intent =
//                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
//                            myPackageName);
//                    startActivity(intent);
//                }
//            });
            new AlertDialog.Builder(this)
                    .setTitle("Default SMS app")
                    .setMessage("Do you want to set 420 as the default SMS app?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            Intent intent =
                                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                                    myPackageName);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }


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

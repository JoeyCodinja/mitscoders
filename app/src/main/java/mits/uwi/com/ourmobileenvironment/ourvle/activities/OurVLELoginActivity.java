package mits.uwi.com.ourmobileenvironment.ourvle.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.ourvle.fragments.OurVLELoginFragment;

public class OurVLELoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final OurVLELoginFragment fragment = OurVLELoginFragment.newInstance();

        final android.support.v4.app.FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this
        // fragment,
        transaction.replace(R.id.fragment, fragment);

        // Commit the transaction
        transaction.commit();
    }

}

package mits.uwi.com.ourmobileenvironment;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import mits.uwi.com.ourmobileenvironment.homepagefragments.HomeActivityFragment;

/**
 * Created by Danuel on 28/10/2016.
 */
public class StartSplashFragment extends Fragment {
    Activity activity;
    public StartSplashFragment(){
        // Empty Constructor
    }

    public Fragment newInstance(){
        return new StartSplashFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_splash_screen, null);

        TextView name = (TextView)v.findViewById(R.id.uwi_name);
        TextView name2 = (TextView)v.findViewById(R.id.uwi_name2);
        Typeface uwi_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TrajanPro-Bold.otf");

        name.setTypeface(uwi_font);
        name2.setTypeface(uwi_font);

        return v;
    }

    @Override
    public void onStart() {
        Thread goToHomeFragment = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized(this){
                    try {
                        wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ((HomeActivity)activity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        removeSplashScreen();
                    }
                });
            }
        });
        goToHomeFragment.start();
        super.onStart();
    }

    private void removeSplashScreen(){
        FragmentManager fm = getFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        for(Fragment fragment: fragments){
            if (fragment instanceof StartSplashFragment){
                fm.beginTransaction()
                        .replace(R.id.home_content, new HomeActivityFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
            }
        }
        ((HomeActivity)getActivity()).getSupportActionBar().show();
    }
}

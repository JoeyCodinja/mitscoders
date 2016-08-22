package mits.uwi.com.ourmobileenvironment.campusinformationfragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.campusinformationfragments.UWIInformationFragments.Faculties;

/**
 * Created by Danuel on 21/8/2016.
 */

public class ChooseFacultyFragment extends Fragment {
    int[] facultiesToChooseFrom = new int[]{R.id.medsci_faculty_chosen,
            R.id.fst_faculty_chosen,
            R.id.humed_faculty_chosen,
            R.id.law_faculty_chosen,
            R.id.socsci_faculty_chosen};


    public static ChooseFacultyFragment newInstance(){
        return new ChooseFacultyFragment();
    }

    public ChooseFacultyFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_faculties_choosefaculties,
                container,
                false);

        for (int faculty = 0; faculty < facultiesToChooseFrom.length; faculty++){
            Button faculty_choice = (Button)v.findViewById(facultiesToChooseFrom[faculty]);
            faculty_choice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction =
                            getFragmentManager()
                                .beginTransaction();

                    transaction.replace(((View)v.getParent().getParent()).getId(),
                                        Faculties.newInstance());
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        return v;
    }
}


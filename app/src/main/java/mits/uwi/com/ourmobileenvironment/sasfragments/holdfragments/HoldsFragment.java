package mits.uwi.com.ourmobileenvironment.sasfragments.holdfragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import mits.uwi.com.ourmobileenvironment.R;
import mits.uwi.com.ourmobileenvironment.sasfragments.Holds;

/**
 * Created by User on 10/9/2015.
 */
public class HoldsFragment extends Fragment {

    TableLayout mHoldTable;
    TableRow mHoldRow,mHoldRow1,mHoldRow2,mHoldRow3,mHoldRow4,mHoldRow5,mHoldRow6;
    ArrayList<Holds> mRows ;//= Holds.getmHolds();
    TextView mHoldInfo1,mHoldInfo2,mHoldInfo3,mHoldInfo4,mHoldInfo5,mHoldInfo6,mHoldInfo7;
    TextView mHoldInfo8,mHoldInfo9,mHoldInfo10,mHoldInfo11,mHoldInfo12,mHoldInfo13,mHoldInfo14;
    TextView mBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_holds, container, false);

        mBack = (TextView)v.findViewById(R.id.HoldBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        mRows = Holds.getmHolds();
        mHoldTable = (TableLayout)v.findViewById(R.id.Administrative_Holds);

        for (int i=0; i< mRows.size(); i++){
            //ROW
            mHoldRow = new TableRow(getActivity());
            mHoldRow.setId(100 + i);
            mHoldRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         /* Label  */
            mHoldInfo1 = new TextView(getActivity());
            mHoldInfo1.setId(200 + i);
            mHoldInfo1.setText("Hold Type");

            mHoldInfo2 = new TextView(getActivity());
            mHoldInfo2.setId(300 + i);
            mHoldInfo2.setText(mRows.get(i).getHoldType());
            //Add to Row
            mHoldRow.addView(mHoldInfo1);
            mHoldRow.addView(mHoldInfo2);
            // Add to Table
            mHoldTable.addView(mHoldRow);
           // ROW
            mHoldRow1 = new TableRow(getActivity());
            mHoldRow1.setId(400 + i);
            mHoldRow1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        //Label
            mHoldInfo3 = new TextView(getActivity());
            mHoldInfo3.setId(500 + i);
            mHoldInfo3.setText("From Date");

            mHoldInfo4 = new TextView(getActivity());
            mHoldInfo4.setId(600 + i);
            mHoldInfo4.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mRows.get(i).getFrmDt()));
            //Add to Row
            mHoldRow1.addView(mHoldInfo3);
            mHoldRow1.addView(mHoldInfo4);
            // Add to Table
            mHoldTable.addView(mHoldRow1);

            //ROW
            mHoldRow2 = new TableRow(getActivity());
            mHoldRow2.setId(700 + i);
            mHoldRow2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // Label
            mHoldInfo5 = new TextView(getActivity());
            mHoldInfo5.setId(800 + i);
            mHoldInfo5.setText("To Date");

            mHoldInfo6 = new TextView(getActivity());
            mHoldInfo6.setId(900 + i);
            mHoldInfo6.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mRows.get(i).getToDt()));
            //Add to Row
            mHoldRow2.addView(mHoldInfo5);
            mHoldRow2.addView(mHoldInfo6);
           // Add to Table
            mHoldTable.addView(mHoldRow2);
            //ROW
            mHoldRow3 = new TableRow(getActivity());
            mHoldRow3.setId(1000 + i);
            mHoldRow3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

         //Label
            mHoldInfo7 = new TextView(getActivity());
            mHoldInfo7.setId(1100 + i);
            mHoldInfo7.setText("Amount");

            mHoldInfo8 = new TextView(getActivity());
            mHoldInfo8.setId(1200 + i);
            mHoldInfo8.setText(String.format("$ %10.0f", mRows.get(i).getAmount()));

            //Add to Row
            mHoldRow3.addView(mHoldInfo7);
            mHoldRow3.addView(mHoldInfo8);
            // Add to Table
            mHoldTable.addView(mHoldRow3);
            //ROW
            mHoldRow4 = new TableRow(getActivity());
            mHoldRow4.setId(1300 + i);
            mHoldRow4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            //Label
            mHoldInfo9 = new TextView(getActivity());
            mHoldInfo9.setId(1400 + i);
            mHoldInfo9.setText("Reason");

            mHoldInfo10 = new TextView(getActivity());
            mHoldInfo10.setId(1500 + i);
            mHoldInfo10.setText(mRows.get(i).getReason());

            //Add to Row
            mHoldRow4.addView(mHoldInfo9);
            mHoldRow4.addView(mHoldInfo10);

            // Add to Table
            mHoldTable.addView(mHoldRow4);

           // ROW
            mHoldRow5 = new TableRow(getActivity());
            mHoldRow5.setId(1600 + i);
            mHoldRow5.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            //Label
            mHoldInfo11 = new TextView(getActivity());
            mHoldInfo11.setId(1700 + i);
            mHoldInfo11.setText("Originator");

            mHoldInfo12 = new TextView(getActivity());
            mHoldInfo12.setId(1800 + i);
            mHoldInfo12.setText(mRows.get(i).getOriginator());
            //Add to Row
            mHoldRow5.addView(mHoldInfo11);
            mHoldRow5.addView(mHoldInfo12);
            // Add to Table
            mHoldTable.addView(mHoldRow5);

            //ROW
            mHoldRow6 = new TableRow(getActivity());
            mHoldRow6.setId(1900 + i);
            mHoldRow6.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            //Label
            mHoldInfo13 = new TextView(getActivity());
            mHoldInfo13.setId(2000 + i);
            mHoldInfo13.setText("Processes Affected");

            mHoldInfo14 = new TextView(getActivity());
            mHoldInfo14.setId(1800 + i);
            mHoldInfo14.setText(mRows.get(i).getProcessed_Affected());
            //Add to Row
            mHoldRow6.addView(mHoldInfo13);
            mHoldRow6.addView(mHoldInfo14);
            // Add to Table
            mHoldTable.addView(mHoldRow6);

        }
        return v;
    }
}

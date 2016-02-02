package mits.uwi.com.ourmobileenvironment.sas.holds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import mits.uwi.com.ourmobileenvironment.sas.Holds;

/**
 * Created by User on 10/9/2015.
 */
public class HoldsFragment extends Fragment {

    TableLayout mHoldTable;
    TableRow mHoldRow,mHoldRow1,mHoldRow2,mHoldRow3,mHoldRow4,mHoldRow5,mHoldRow6,mRowMarker,mRowMarker1,mRowMarker2;
    ArrayList<Holds> mRows = new ArrayList<Holds>() ;//= Holds.getmHolds();
    TextView mHoldInfo1,mHoldInfo2,mHoldInfo3,mHoldInfo4,mHoldInfo5,mHoldInfo6,mHoldInfo7;
    TextView mHoldInfo8,mHoldInfo9,mHoldInfo10,mHoldInfo11,mHoldInfo12,mHoldInfo13,mHoldInfo14;
    TextView mBack;
    View mNotify;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.admin_hold);
        //mRows = Holds.getmHolds();
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.admin_hold);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_holds, container, false);


        mNotify  = (View)v.findViewById(R.id.holdsnotify);

        mBack = (TextView)v.findViewById(R.id.HoldBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });


        mHoldTable = (TableLayout)v.findViewById(R.id.Administrative_Holds);
        if (mRows.isEmpty()){
            mNotify.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldRow = new TableRow(getActivity());
            mHoldRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         /* Label  */
            mHoldInfo1 = new TextView(getActivity());
            mHoldInfo1.setText("There are currently no holds on your account. Congrats?");
            mHoldInfo1.setTextSize(10);
            mHoldInfo1.setHorizontallyScrolling(true);
            mHoldInfo1.setPadding(5, 50, 5, 50);
            mHoldRow.addView(mHoldInfo1);
            mHoldTable.addView(mHoldRow);
        }else{
        for (int i=0; i< mRows.size(); i++) {
            //ROW
            mHoldRow = new TableRow(getActivity());
            mHoldRow.setId(100 + i);
            mHoldRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         /* Label  */
            mHoldInfo1 = new TextView(getActivity());
            mHoldInfo1.setId(200 + i);
            mHoldInfo1.setText("Hold Type");
            mHoldInfo1.setHorizontallyScrolling(true);
            //  mHoldInfo1.setTextColor(getResources().getColor(R.color.white));
            //  mHoldInfo1.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo1.setPadding(5, 5, 5, 5);

            mHoldInfo2 = new TextView(getActivity());
            mHoldInfo2.setId(300 + i);
            mHoldInfo2.setText(mRows.get(i).getHoldType());
            mHoldInfo2.setPadding(10, 5, 5, 5);
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
            mHoldInfo3.setHorizontallyScrolling(true);
            // mHoldInfo3.setTextColor(getResources().getColor(R.color.white));
            //  mHoldInfo3.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo3.setPadding(5, 5, 5, 5);

            mHoldInfo4 = new TextView(getActivity());
            mHoldInfo4.setId(600 + i);
            mHoldInfo4.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mRows.get(i).getFrmDt()));
            mHoldInfo4.setPadding(10, 5, 5, 5);
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
            mHoldInfo5.setHorizontallyScrolling(true);
            //  mHoldInfo5.setTextColor(getResources().getColor(R.color.white));
            //  mHoldInfo5.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo5.setPadding(5, 5, 5, 5);

            mHoldInfo6 = new TextView(getActivity());
            mHoldInfo6.setId(900 + i);
            mHoldInfo6.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(mRows.get(i).getToDt()));
            mHoldInfo6.setPadding(10, 5, 5, 5);
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
            mHoldInfo7.setHorizontallyScrolling(true);
            // mHoldInfo7.setTextColor(getResources().getColor(R.color.white));
            // mHoldInfo7.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo7.setPadding(10, 5, 5, 5);

            mHoldInfo8 = new TextView(getActivity());
            mHoldInfo8.setId(1200 + i);
            mHoldInfo8.setText(String.format("$%10.0f", mRows.get(i).getAmount()));
            mHoldInfo8.setPadding(10, 5, 5, 5);

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
            mHoldInfo9.setHorizontallyScrolling(true);
            // mHoldInfo9.setTextColor(getResources().getColor(R.color.white));
            // mHoldInfo9.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo9.setPadding(10, 5, 5, 5);

            mHoldInfo10 = new TextView(getActivity());
            mHoldInfo10.setId(1500 + i);
            mHoldInfo10.setText(mRows.get(i).getReason());
            mHoldInfo10.setPadding(10, 5, 5, 5);

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
            mHoldInfo11.setHorizontallyScrolling(true);
            // mHoldInfo11.setTextColor(getResources().getColor(R.color.white));
            //  mHoldInfo11.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo11.setPadding(5, 5, 5, 5);

            mHoldInfo12 = new TextView(getActivity());
            mHoldInfo12.setId(1800 + i);
            mHoldInfo12.setText(mRows.get(i).getOriginator());
            mHoldInfo12.setPadding(10, 5, 5, 5);

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
            mHoldInfo13.setHorizontallyScrolling(true);
            // mHoldInfo13.setTextColor(getResources().getColor(R.color.white));
            // mHoldInfo13.setBackgroundColor(getResources().getColor(R.color.accent_1));
            mHoldInfo13.setPadding(5, 5, 5, 5);

            mHoldInfo14 = new TextView(getActivity());
            mHoldInfo14.setId(1800 + i);
            mHoldInfo14.setText(mRows.get(i).getProcessed_Affected());
            mHoldInfo14.setPadding(10, 5, 5, 5);
            //Add to Row
            mHoldRow6.addView(mHoldInfo13);
            mHoldRow6.addView(mHoldInfo14);
            // Add to Table
            mHoldTable.addView(mHoldRow6);
            if (i != mRows.size() - 1) {
                mRowMarker1 = new TableRow(getActivity());
                mRowMarker1.setId(2100 + i);
                mRowMarker1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                mRowMarker1.setBackgroundColor(getResources().getColor(R.color.white));
                mRowMarker1.setPadding(10, 10, 10, 10);
                mHoldTable.addView(mRowMarker1);

                mRowMarker = new TableRow(getActivity());
                mRowMarker.setId(2100 + i);
                mRowMarker.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                mRowMarker.setBackgroundColor(getResources().getColor(R.color.holds));
                mRowMarker.setPadding(10, 10, 10, 10);
                mHoldTable.addView(mRowMarker);

                mRowMarker2 = new TableRow(getActivity());
                mRowMarker2.setId(2100 + i);
                mRowMarker2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                mRowMarker2.setBackgroundColor(getResources().getColor(R.color.white));
                mRowMarker2.setPadding(10, 10, 10, 10);

                mHoldTable.addView(mRowMarker2);
            }
        }
        }
        return v;
    }
}

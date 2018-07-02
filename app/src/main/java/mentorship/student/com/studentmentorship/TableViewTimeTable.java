package mentorship.student.com.studentmentorship;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TableViewTimeTable extends AppCompatActivity{

    TableLayout tl;
    TableRow tr;
    TextView day,ts1,ts2,ts3,ts4,ts5,ts6,ts7,ts8,ts9,ts10;
    public String[][] time_table;
    public int w_days =5;
    public int num_periods =10;

    String nosubjects;
    int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view_time_table);
        tl = (TableLayout) findViewById(R.id.maintable);
        Bundle ob=getIntent().getExtras();
       // nosubjects=ob.getString("nosubjects");

      //  no=Integer.parseInt(nosubjects);

        String[][] time_table1=new String[w_days][num_periods];
        time_table=new String[w_days][num_periods+1];
        time_table[0][0]="MON";
        time_table[1][0]="TUE";
        time_table[2][0]="WED";
        time_table[3][0]="THUR";
        time_table[4][0]="FRI";

        for (int k = 0; k < w_days; k++)
        {
            for (int j = 0; j < num_periods; j++)
            {
                time_table1[k][j]=ob.getString("TT"+k+j);
               //  Toast.makeText(TableViewTimeTable.this,time_table1[k][j],Toast.LENGTH_LONG).show();
            }
        }

        for (int k = 0; k < w_days; k++)
        {
            for (int j = 0; j < num_periods; j++)
            {
                time_table[k][j+1]=time_table1[k][j];
                // Toast.makeText(TableViewTimeTable.this,time_table[k][j],Toast.LENGTH_LONG).show();
            }
        }
        for (int k = 0; k < w_days; k++)
        {
            for (int j = 0; j < num_periods+1; j++)
            {
              //  time_table[k][j+1]=time_table1[k][j];
              //  Toast.makeText(TableViewTimeTable.this,time_table[k][j],Toast.LENGTH_LONG).show();
            }
        }

        addHeaders();
        addData();
    }

    private void addHeaders()
    {
        tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView day = new TextView(this);
        day.setText("day");
        day.setTextColor(Color.GRAY);
        day.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       // day.setPadding(5, 5, 5, 0);
        tr.addView(day);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView ts1 = new TextView(this);
        ts1.setText("8-9");
        ts1.setTextColor(Color.GRAY);
        ts1.setPadding(5, 5, 5, 0);
        ts1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts1); // Adding textView to tablerow.

        TextView ts2 = new TextView(this);
        ts2.setText("9-10");
        ts2.setTextColor(Color.GRAY);
       // ts2.setPadding(5, 5, 5, 0);
        ts2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts2);


        TextView ts3 = new TextView(this);
        ts3.setText("10-11");
        ts3.setTextColor(Color.GRAY);
      //  ts3.setPadding(5, 5, 5, 0);
        ts3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts3);


        TextView ts4 = new TextView(this);
        ts4.setText("11-12");
        ts4.setTextColor(Color.GRAY);
      //  ts4.setPadding(5, 5, 5, 0);
        ts4.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts4);


        TextView ts5 = new TextView(this);
        ts5.setText("12-1:15");
        ts5.setTextColor(Color.GRAY);
      //  ts5.setPadding(5, 5, 5, 0);
        ts5.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts5);


        TextView ts6 = new TextView(this);
        ts6.setText("1:15-2:15");
        ts6.setTextColor(Color.GRAY);
       // ts6.setPadding(5, 5, 5, 0);
        ts6.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts6);


        TextView ts7 = new TextView(this);
        ts7.setText("2:15-3:15");
        ts7.setTextColor(Color.GRAY);
      //  ts7.setPadding(5, 5, 5, 0);
        ts7.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts7);


        TextView ts8 = new TextView(this);
        ts8.setText("3:15-4:15");
        ts8.setTextColor(Color.GRAY);
      //  ts8.setPadding(5, 5, 5, 0);
        ts8.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts8);


        TextView ts9 = new TextView(this);
        ts9.setText("4:15-5:15");
        ts9.setTextColor(Color.GRAY);
       // ts9.setPadding(5, 5, 5, 0);
        ts9.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts9);


        TextView ts10 = new TextView(this);
        ts10.setText("5:15-6:15");
        ts10.setTextColor(Color.GRAY);
      //  ts10.setPadding(5, 5, 5, 0);
        ts10.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(ts10);

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
       /* tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
       /* TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.GREEN);
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.*/

        // Add the TableRow to the TableLayout
       /* tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));*/


    }

    private void addData() {
        /*
        for (int i = 0; i < companies.length; i++)
        {
            // Create a TableRow dynamically
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // Creating a TextView to add to the row
            companyTV = new TextView(this);
            companyTV.setText(companies[i]);
            companyTV.setTextColor(Color.RED);
            companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            companyTV.setPadding(5, 5, 5, 5);
            tr.addView(companyTV);  // Adding textView to tablerow.

            // Creating another textview
            valueTV = new TextView(this);
            valueTV.setText(os[i]);
            valueTV.setTextColor(Color.GREEN);
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }*/
        for (int i = 0; i < w_days; i++)
        {

            int j = 0;
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView ts0 = new TextView(this);
            ts0.setText(time_table[i][j]);
            ts0.setTextColor(Color.RED);
            ts0.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            ts0.setPadding(5, 5, 5, 5);
            tr.addView(ts0);  // Adding textView to tablerow.
            Toast.makeText(TableViewTimeTable.this,time_table[i][j],Toast.LENGTH_LONG).show();
            j++;

            TextView ts1 = new TextView(this);
            ts1.setText(time_table[i][j]);
            ts1.setTextColor(Color.GRAY);
            ts1.setPadding(5, 5, 5, 5);
            ts1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts1); // Adding textView to tablerow.

            j++;

            TextView ts2 = new TextView(this);
            ts2.setText(time_table[i][j]);
            ts2.setTextColor(Color.GRAY);
            ts2.setPadding(5, 5, 5, 5);
            ts2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts2);

            j++;

            TextView ts3 = new TextView(this);
            ts3.setText(time_table[i][j]);
            ts3.setTextColor(Color.GRAY);
            ts3.setPadding(5, 5, 5, 5);
            ts3.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts3);

            j++;

            TextView ts4 = new TextView(this);
            ts4.setText(time_table[i][j]);
            ts4.setTextColor(Color.GRAY);
            ts4.setPadding(5, 5, 5, 5);
            ts4.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts4);

            j++;

            TextView ts5 = new TextView(this);
            ts5.setText(time_table[i][j]);
            ts5.setTextColor(Color.GRAY);
            ts5.setPadding(5, 5, 5, 5);
            ts5.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts5);

            j++;

            TextView ts6 = new TextView(this);
            ts6.setText(time_table[i][j]);
            ts6.setTextColor(Color.GRAY);
            ts6.setPadding(5, 5, 5, 5);
            ts6.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts6);

            j++;

            TextView ts7 = new TextView(this);
            ts7.setText(time_table[i][j]);
            ts7.setTextColor(Color.GRAY);
            ts7.setPadding(5, 5, 5, 5);
            ts7.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts7);

            j++;

            TextView ts8 = new TextView(this);
            ts8.setText(time_table[i][j]);
            ts8.setTextColor(Color.GRAY);
            ts8.setPadding(5, 5, 5, 5);
            ts8.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts8);


            j++;

            TextView ts9 = new TextView(this);
            ts9.setText(time_table[i][j]);
            ts9.setTextColor(Color.GRAY);
            ts9.setPadding(5, 5, 5, 5);
            ts9.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts9);


            j++;

            TextView ts10 = new TextView(this);
            ts10.setText(time_table[i][j]);
            ts10.setTextColor(Color.GRAY);
            ts10.setPadding(5, 5, 5, 5);
            ts10.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts10);

           /* j++;

            TextView ts11 = new TextView(this);
            ts10.setText(time_table[i][j]);
            ts10.setTextColor(Color.GRAY);
            ts10.setPadding(5, 5, 5, 5);
            ts10.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(ts11);*/

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

    }



        //end of addData function
    }

    //end of class
}


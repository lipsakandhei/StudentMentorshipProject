package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Subject;


public class ShowTimeTable extends AppCompatActivity implements View.OnClickListener {
    public int nosubject;
    public String nosubjectEt;
    public int working_days = 5;
    public int num_periods = 9;
    EditText edtView;
    private String[] ETtext;
    private Subject[] subjects;
    LinearLayout.LayoutParams lParamsMW;

    String[] words;
    List<EditText> allEds = new ArrayList<EditText>();

    String sequence = "PT";//For checking if its TP or PT sequence
    String prac_first = "Not fixed"; //To check whether practical is in first half or second half on Wednesday
    String[][] tp_time_table = {{"TA", "TB", "TC", "TE", "LUNCH", "PA", "PA", "PA", "TF", "ZA"}, {"TB", "TC", "TD", "TE", "LUNCH", "PB", "PB", "PB", "TF", "ZB"}, {" ", "PX", "PX", "PX", "LUNCH", "TG1", "SA1", "SB1", "SC1", "ZA"}, {"SM1", "SJ1", "SK1", "SL1", "LUNCH", " ", "PY", "PY", "PY", " "}, {"TC", "TD", "TA", "TG2", "LUNCH", "PC", "PC", "PC", "TF", "ZC"}, {"TD", "TA", "TB", "TE", "LUNCH", "PD", "PD", "PD", "TG3", "ZA"}};
    String[][] pt_time_table = {{"PE", "PE", "PE", "TE", "LUNCH", "TJ", "TK", "TL", "TF", "ZA"}, {"PF", "PF", "PF", "TE", "LUNCH", "TK", "TL", "TM", "TF", "ZB"}, {" ", "PX", "PX", "PX", "LUNCH", "TG1", "SA1", "SB1", "SC1", "ZA"}, {"SM1", "SJ1", "SK1", "SL1", "LUNCH", " ", "PY", "PY", "PY", " "}, {"PG", "PG", "PG", "TG2", "LUNCH", "TL", "TM", "TJ", "TF", "ZC"}, {"PH", "PH", "PH", "TE", "LUNCH", "TM", "TJ", "TK", "TG3", "ZA"}};
    public String[][] time_table;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_table);
        Bundle ob = getIntent().getExtras();
        nosubjectEt = ob.getString("nosubjectEt");
        nosubject = Integer.parseInt(nosubjectEt);
        lParamsMW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //linearLayout.setGravity(Gravity.CENTER);

        // Toast.makeText(ShowTimeTable.this,nosubject,Toast.LENGTH_LONG).show();
        for (int i = 1; i <= nosubject; i++) {
            //  LinearLayout linearLayout1 = new LinearLayout(this);
            //  linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            edtView = new EditText(this);
            allEds.add(edtView);
            edtView.setHint(i + " subject-subject code-timeslot-credits");
            edtView.setLayoutParams(lParamsMW);


            // edtView.setPadding(2, 2, 2, 2);
            // lParamsMW.setMargins(50, 300, 30, 100);

            linearLayout.addView(edtView);


        }
        Button button = new Button(this);
        button.setLayoutParams(lParamsMW);
        button.setText("submit");
        linearLayout.addView(button);

        this.setContentView(linearLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

       // try{
            time_table=new String[5][allEds.size()+1];

           /*for (int k = 0; k < allEds.size(); k++)
            {
                for (int j = 0; j < 4; j++)
                {
                    time_table[k][j]=new String();
                }
            }*/
            ETtext = new String[(allEds.size())];
            subjects = new Subject[nosubject];
            for (int i = 0; i < nosubject; i++)
            {
                subjects[i] = new Subject();
            }

            for (int i = 0; i < allEds.size(); i++)
            {
                ETtext[i] = allEds.get(i).getText().toString();
                words = ETtext[i].split("-");
              /*  for (int j = 0; j < 4; j++)
                {
                   // ETtextdata[i][j]=words[j];
                    Toast.makeText(ShowTimeTable.this,words[j],Toast.LENGTH_LONG).show();
                }*/


                subjects[i].setSubject_name(words[0]);
                subjects[i].setSubject_code(words[1]);
                subjects[i].setTime_slot(words[2]);
                subjects[i].setCredits(Integer.parseInt(words[3]));
               // Log.d("subjects",subjects[i].getSubject_code());
               // Log.d("subjects",subjects[i].getSubject_name());
               // Log.d("subjects",subjects[i].getSubject_name());
               // Log.d("subjects",subjects[i].getSubject_name());


                for (int j = 0; j < subjects[i].getTime_slot().length(); j++)
                {
                    //char ch = newSub->time_slot[j];
                    char[] ch = subjects[i].getTime_slot().toCharArray();
                    if (Character.isLowerCase(ch[j]) || Character.isDigit(ch[j])) {
                        Toast.makeText(ShowTimeTable.this, "\nError!!! Please enter Time Slot in Capital letters as in NITRIS Portal and Try Again", Toast.LENGTH_LONG).show();
                        return;
                    }

                }


                if (subjects[i].getTime_slot().equals("PX")) {
                    prac_first = "True";
                } else if (subjects[i].getTime_slot().equals("PY")) {
                    prac_first = "False";
                }
                if (subjects[i].getTime_slot().equals("PA") || subjects[i].getTime_slot().equals("PB") || subjects[i].getTime_slot().equals("PC") || subjects[i].getTime_slot().equals("PD") || subjects[i].getTime_slot().equals("PX")) {
                    sequence = "TP";
                }


                 assign_tut_slot(subjects[i]);




                //end of subject assignment loop
            }
      /*  }
            catch (Exception e) {
            Toast.makeText(ShowTimeTable.this, "error due to :" + e, Toast.LENGTH_LONG).show();

        }*/
        student_time_table(subjects, nosubject);
        Intent intent = new Intent(ShowTimeTable.this, TableViewTimeTable.class);
       // intent.putExtra("nosubjects",nosubjectEt);

        for (int k = 0; k < 5; k++)
        {
            for (int j = 0; j < allEds.size()+1; j++)
            {
                // intent.putExtra("TT"+k+j, time_table[k][j]);
              //  Toast.makeText(ShowTimeTable.this,time_table[k][j],Toast.LENGTH_LONG).show();
                intent.putExtra("TT"+k+j,time_table[k][j]);

            }
        }
        startActivity(intent);
    }

    private void student_time_table(Subject[] subjects, int nosubject) {

        String[] w_days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        for(int i=0; i<working_days; i++)
        {
            for(int j=0; j<num_periods+1; j++)
            {
                if(j==4)
                {
                    time_table[i][j] = "LUNCH";
                }
                else
                {
                    time_table[i][j] = "N/A";
                }
            }
        }

        String[][] time_table_seq=new String[working_days+1][num_periods+1];
        if(sequence=="TP")
        {
            for(int i=0; i<working_days+1; i++)
            {
                for(int j=0; j<num_periods+1; j++)
                {
                    time_table_seq[i][j] = tp_time_table[i][j];
                }
            }
        }
        else
        {
            for(int i=0; i<working_days+1; i++)
            {
                for(int j=0; j<num_periods+1; j++)
                {
                    time_table_seq[i][j] = pt_time_table[i][j];
                }
            }
        }
        for(int i=0; i<nosubject; i++)
        {
            // cout<<"Subject_Code"<<"   "<<"Subject_Name\n";
            if(!subjects[i].equals(null))
            {
                // cout<<subjects[i]->subject_code<<"          "<<subjects[i]->subject_name<<"\n";
                update_student_time_table(subjects[i], time_table_seq);
                // cout<<"\n";
            }
            else
            {
                Toast.makeText(ShowTimeTable.this,"error",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void update_student_time_table(Subject subject, String[][] time_table_seq) {

        //int credits = sub->credits;
        int credits = subject.getCredits();
        // String time_slot = sub->time_slot;
        String time_slot = subject.getTime_slot();
        // String subject_code = sub->subject_code;
        String subject_code = subject.getSubject_code();
        // String tutorial_slot = sub->tutorial_slot;
        String tutorial_slot = subject.getTutorial_slot();
        int k = 0;//To keep track of time_table index
        int flag = 0;//To check if we got the tutorial slot for a 4 credit course in 3rd row i.e. i=2 itself or not if we did then we shall directly jump to thursday i.e. i=4 as per time_table_seq matrix not time_table matrix
        if(credits<=3)
        {
            for(int i=0; i<working_days+1; i++)
            {
                if((i==2 && prac_first.equals("False")) || (i==3 && prac_first.equals("True")))
                {
                    continue;
                }
                else if(i==2 && prac_first.equals("Not fixed"))//As for Not fixed we don't have any practicals on Wednesday so we can skip enntire wednesday and jump to thursday
                {
                    i = i+1;
                    k = k+1;//As we need to skip wednesday and move to thursday
                    continue;
                }
                for(int j=0; j<num_periods+1; j++)
                {
                    if(j!=4)
                    {
                        if(time_table_seq[i][j].equals("PA") || time_table_seq[i][j].equals("PB") || time_table_seq[i][j].equals("PC") || time_table_seq[i][j].equals("PD") || time_table_seq[i][j].equals("PX") || time_table_seq[i][j].equals("PE") || time_table_seq[i][j].equals("PF") || time_table_seq[i][j].equals("PG") || time_table_seq[i][j].equals("PH") || time_table_seq[i][j].equals("PY"))
                        {
                            if(!time_slot.equals("PA") && !time_slot.equals("PB") && !time_slot.equals("PC") && !time_slot.equals("PD") && !time_slot.equals("PX") && !time_slot.equals("PE") && !time_slot.equals("PF") && !time_slot.equals("PG")&& !time_slot.equals("PH")&& !time_slot.equals("PY"))
                            {
                                j = j+2;
                                continue;
                            }
                            else
                            {
                                if(time_table_seq[i][j].equals(time_slot))
                                {
                                    time_table[k][j] = subject_code;
                                    time_table[k][j+1] = time_table[k][j];
                                    time_table[k][j+2] = time_table[k][j];
                                    credits = 0;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            if(!time_slot.equals("PA") && !time_slot.equals("PB") && !time_slot.equals("PC") && !time_slot.equals("PD") && !time_slot.equals("PX") && !time_slot.equals("PE") && !time_slot.equals("PF") && !time_slot.equals("PG")&& !time_slot.equals("PH")&& !time_slot.equals("PY"))
                            {
                                if(time_table_seq[i][j].equals(time_slot))
                                {
                                    time_table[k][j] = subject_code;
                                    credits--;
                                    if(credits==0)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        continue;
                    }
                }
                if(credits==0)
                {
                    break;
                }
                k++;
            }
        }
        else if(credits>3)
        {

            for(int i=0; i<working_days+1; i++)
            {
                if(i==3 && flag==1)//Means tutorial was found in i==2
                {
                    continue;
                }
                else if(i==3 && flag!=1)
                {
                    k--;//Go back to index of wednesday and search again
                }
                for(int j=0; j<num_periods+1; j++)
                {
                    if(j!=4)
                    {
                        if(i==2 && j==0)//2nd row i.e. wednesday and 1st colum i.e. first subject of wednesday
                        {
                            if(prac_first.equals("True") || prac_first.equals("Not fixed"))//Practical in first half or we don't have pracical slot on Wednesday check for tutorial in second half
                            {
                                j = j+4;//Search after lunch break
                                continue;
                            }
                        }
                        if(time_table_seq[i][j].equals("PA") || time_table_seq[i][j].equals("PB") || time_table_seq[i][j].equals("PC") || time_table_seq[i][j].equals("PD") || time_table_seq[i][j].equals("PX") || time_table_seq[i][j].equals("PE") || time_table_seq[i][j].equals("PF") || time_table_seq[i][j].equals("PG") || time_table_seq[i][j].equals("PH") || time_table_seq[i][j].equals("PY"))
                        {
                            j = j+2;
                            continue;
                        }
                        else
                        {
                            if(time_table_seq[i][j].equals(time_slot))
                            {
                                time_table[k][j] = subject_code;
                                credits--;
                                if(credits==0)
                                {
                                    break;
                                }
                            }
                            else if(time_table_seq[i][j].equals(tutorial_slot))
                            {
                                String tutorial = subject_code+"_TA";
                                time_table[k][j] = tutorial;
                                credits--;
                                if(credits==0)
                                {
                                    break;
                                }
                                flag = 1;
                            }
                        }
                    }
                    else
                    {
                        if(i==3 && flag!=1)//Till lunch we couldn't find tutorial and after lunch there is practical PY slot so no chance of tutorial as per Time Table so directly go to next day and search there
                        {
                            break;
                        }
                        continue;
                    }
                }
                if(credits==0)
                {
                    break;
                }
                k++;
            }
        }
    }

    private void assign_tut_slot(Subject subject)
    {

        String time_slot =subject.getTime_slot();
        if(time_slot.equals("TA"))
        {
            subject.setTutorial_slot("SA1");
        }
        else if(time_slot.equals("TB"))
        {
            subject.setTutorial_slot("SB1");
        }
        else if(time_slot.equals("TC"))
        {
            subject.setTutorial_slot("SB1");
        }
        else if(time_slot.equals("TD"))
        {
            subject.setTutorial_slot("TG1");
        }
        else if(time_slot.equals("TE"))
        {
            subject.setTutorial_slot("TG2");
        }
        else if(time_slot.equals("TF"))
        {
            subject.setTutorial_slot("TG3");
        }
        else if(time_slot.equals("TG"))
        {
            subject.setTutorial_slot("SJ1");
        }
        else if(time_slot.equals("TK"))
        {
            subject.setTutorial_slot("SK1");
        }
        else if(time_slot.equals("TL"))
        {
            subject.setTutorial_slot("SL1");
        }
        else if(time_slot.equals("TM"))
        {
            subject.setTutorial_slot("SM1");
        }
    }




}

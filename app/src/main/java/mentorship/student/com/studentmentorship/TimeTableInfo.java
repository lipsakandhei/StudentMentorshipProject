package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimeTableInfo extends AppCompatActivity implements View.OnClickListener{
    EditText nosubjectEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_info);
        Button subjectnosubmitBTN=(Button)findViewById(R.id.subjectnosubmitBTN);
        nosubjectEt=(EditText)findViewById(R.id.nosubjectEt);
        subjectnosubmitBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String subjectno=nosubjectEt.getText().toString();
        Intent i=new Intent(TimeTableInfo.this,ShowTimeTable.class);
        i.putExtra("nosubjectEt",subjectno);
        startActivity(i);
    }
}

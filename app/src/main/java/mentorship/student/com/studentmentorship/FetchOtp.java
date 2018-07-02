package mentorship.student.com.studentmentorship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FetchOtp extends AppCompatActivity  implements View.OnClickListener{

            Button btn;
            EditText et;
    String name,rollno,password,mobileno,emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_otp);

       /* Bundle ob=getIntent().getExtras();
         name=ob.getString("name");
         rollno=ob.getString("rollno");
         password=ob.getString("password");
         mobileno=ob.getString("mobileno");
         emailid=ob.getString("emailid");*/
       /* et=(EditText)findViewById(R.id.editotp);
        btn=(Button)findViewById(R.id.SubmitOtp);
        btn.setOnClickListener(this);*/






    }

    @Override
    public void onClick(View view) {

    }
}

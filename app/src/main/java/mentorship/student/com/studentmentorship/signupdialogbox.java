package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signupdialogbox extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupdialogbox);
        Button bt1=(Button)findViewById(R.id.button8);
        Button bt2=(Button)findViewById(R.id.button9);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button8)
        {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.button9)
        {
            Intent i2=new Intent(this,signup.class);
            startActivity(i2);
        }
    }
}

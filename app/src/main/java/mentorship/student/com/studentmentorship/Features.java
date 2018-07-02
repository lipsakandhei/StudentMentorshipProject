package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Features extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        final Button academics = (Button) findViewById(R.id.acad);
        final Button hostel = (Button) findViewById(R.id.hos);
        final Button medical = (Button) findViewById(R.id.medical);
        final Button misc = (Button) findViewById(R.id.misc);
        final Button aboutus = (Button)findViewById(R.id.aboutUs);
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acad = new Intent(Features.this, Academics.class);
                startActivity(acad);
            }
        });
        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hos = new Intent(Features.this, Hostel.class);
                startActivity(hos);
            }
        });
        misc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent misc = new Intent(Features.this, Miscellaneous.class);
                startActivity(misc);
            }
        });
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medical = new Intent(Features.this, MedicalEmergency.class);
                startActivity(medical);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutus = new Intent(Features.this, About_us.class);
                startActivity(aboutus);
            }
        });
    }
}

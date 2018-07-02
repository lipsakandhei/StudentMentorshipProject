package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Miscellaneous extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous);
        final Button importantcontacts = (Button)findViewById(R.id.contact);
        importantcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent importantcontacts = new Intent(Miscellaneous.this, ImportantPhoneNumbers.class);
                startActivity(importantcontacts);
            }
        });
    }
}

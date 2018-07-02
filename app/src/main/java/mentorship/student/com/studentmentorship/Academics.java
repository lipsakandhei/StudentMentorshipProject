package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Academics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics);
        final Button booklist = (Button)findViewById(R.id.booklist);
        booklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent books = new Intent(Academics.this, BookList.class);
                startActivity(books);
            }
        });
    }
}
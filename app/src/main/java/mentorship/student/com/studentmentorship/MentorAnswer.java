package mentorship.student.com.studentmentorship;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class MentorAnswer extends AppCompatActivity implements View.OnClickListener {
    EditText et1;
    String rollno,mentor_rollno,qid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_answer);
        et1 = (EditText) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        Bundle ob=getIntent().getExtras();
        rollno =ob.getString("rollno");
         qid=ob.getString("qesId");
         mentor_rollno=ob.getString("mentor_rollno");
        Toast.makeText(MentorAnswer.this,"asked by :"+rollno+"   ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {

            insert_answer();

    }
    private String insert_answer()
    {

        String stat="fail";

        final String answer=et1.getText().toString();


        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("answer", answer);
        paramMap.put("rollno",rollno);
        paramMap.put("mentor_rollno",mentor_rollno);
        paramMap.put("quesId",qid);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.INSERT_ANSWER_PRIVATE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status))
                    {
                        Toast.makeText(MentorAnswer.this, "successfully answered", Toast.LENGTH_LONG).show();
                        /*
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        //prefs.edit().putBoolean("isMobile", Boolean.valueOf(mobile)).commit();
                        SharedPreferences.Editor editor= prefs.edit();
                        editor.putString("userid",userid);
                        editor.commit();
                        */
                        Intent i=new Intent(MentorAnswer.this,MessengerActivity.class);
                        //i.putExtra("userid",userid);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(MentorAnswer.this, "error Try Again!!!", Toast.LENGTH_LONG).show();
                    }


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d(AppConstants.TAG, e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorMsg, Throwable throwable) {
                Log.e("ERROR", errorMsg);
            }
        });



        return stat;

    }

}
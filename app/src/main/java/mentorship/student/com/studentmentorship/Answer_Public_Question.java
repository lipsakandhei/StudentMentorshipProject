package mentorship.student.com.studentmentorship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Answer_Public_Question extends AppCompatActivity
{
    private Button btnAnswer;
    private EditText editTextAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer__public__question);
        Bundle ob=getIntent().getExtras();

        final String qesId=ob.getString("qesId");
        String qesTitle=ob.getString("qesTitle");
        String qesDescription=ob.getString("qesDescription");
        final String mentor_rollno=ob.getString("mentor_rollno");
        final String rollno=ob.getString("rollno");

        Toast.makeText(Answer_Public_Question.this,"qesTitle := "+rollno,Toast.LENGTH_LONG).show();
        TextView TextViewTitle=(TextView)findViewById(R.id.TextViewTitle);
        TextView TextViewDescription=(TextView)findViewById(R.id.TextViewDescription);
        TextViewTitle.setText(qesTitle);
        TextViewDescription.setText(qesDescription);

        editTextAnswer=(EditText)findViewById(R.id.editTextAnswer);

        btnAnswer=(Button)findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String answer=editTextAnswer.getText().toString();
                AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
                Map<String,String> map=new HashMap<String, String>();
                map.put("qesId",qesId);
                map.put("answer",answer);
                map.put("mentor_rollno",mentor_rollno);
                map.put("rollno",rollno);
                RequestParams params=new RequestParams(map);
                asyncHttpClient.post(IpConfig.PUBLIC_ANSWAR,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                    {
                        super.onSuccess(statusCode, headers, response);
                        try
                        {
                            String status= response.getString("status");
                            if(status.equals("success"))
                            {
                                Log.d("status",status);
                            }
                            else
                            {
                                Log.d("status",status);
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
                    {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });

            }
        });

    }
}

package mentorship.student.com.studentmentorship;

import android.content.Intent;
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

public class signup extends AppCompatActivity implements View.OnClickListener{
    EditText et1,et2,et3,et4,et5,et6;
    String name,password,rollno,mobileno,emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btn=(Button)findViewById(R.id.buttonSignUp);

        et1=(EditText)findViewById(R.id.editTextName);
        //et2=(EditText)findViewById(R.id.editTextUserId);
        et3=(EditText)findViewById(R.id.editTextPassword);
        et4=(EditText)findViewById(R.id.editTextMobileNo);
        et5=(EditText)findViewById(R.id.editTextEmailId);
        et6=(EditText)findViewById(R.id.editTextRollNo);
        btn.setOnClickListener(this);

        Log.d("URL",IpConfig.SERVER_REGD_URL);
    }


    @Override
    public void onClick(View v) {

        //put the the data in database
        //if successfuly inserted into db then
         Register();
        // Toast.makeText(this,"sign up successful press back to exit",Toast.LENGTH_LONG).show();

        //else
       // Toast.makeText(this, "sign up unsuccessful", Toast.LENGTH_LONG).show();

    }

    private String Register()
    {
        String stat="fail";

         name=et1.getText().toString();
       // String userid=et2.getText().toString();
         password=et3.getText().toString();
         mobileno=et4.getText().toString();
        emailid=et5.getText().toString();
         rollno=et6.getText().toString();

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("name", name);
        //   paramMap.put("userid", userid);
        paramMap.put("password", password);
        paramMap.put("mobileno", mobileno);
        paramMap.put("emailid", emailid);
        paramMap.put("rollno", rollno);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.SERVER_REGD_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status)){
                        Toast.makeText(signup.this, "signup successful", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(signup.this,MainActivity.class);
                        startActivity(i);



                    }

                    else if("failed".equals(status))
                    {
                        Toast.makeText(signup.this, "invalid roll no", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(signup.this, "signup failed", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(AppConstants.TAG, e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorMsg, Throwable throwable)
            {
                Log.e("ERROR", errorMsg, throwable);
            }
        });



      /*  Map<String, String> paramMap = new HashMap<>();

        paramMap.put("name", name);
     //   paramMap.put("userid", userid);
        paramMap.put("password", password);
        paramMap.put("mobileno", mobileno);
        paramMap.put("emailid", emailid);
        paramMap.put("rollno", rollno);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.SERVER_REGD_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status)){
                        Toast.makeText(signup.this, "signup successful", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(signup.this,MainActivity.class);
                        startActivity(i);



                    }

                    else if("failed".equals(status))
                    {
                        Toast.makeText(signup.this, "invalid roll no", Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(signup.this, "signup failed", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(AppConstants.TAG, e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorMsg, Throwable throwable) {
                Log.e("ERROR", errorMsg, throwable);
            }
        });


*/

        return stat;

    }

}

package mentorship.student.com.studentmentorship;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

//public class MainActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
{

    EditText et1,et2;
    ImageView iv;
    Button btn1;
    Button btn2;
    Button btn3;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btn1=(Button)findViewById(R.id.buttonLogin);
         btn2=(Button)findViewById(R.id.buttonSkip);
        btn2.setVisibility(View.GONE);
         btn3=(Button)findViewById(R.id.buttonSignUp);

        et1=(EditText)findViewById(R.id.editTextLoginId);
        et2=(EditText)findViewById(R.id.editTextPassword);
        iv=(ImageView)findViewById(R.id.imageView);
        ToggleButton tgl=(ToggleButton)findViewById(R.id.toggleButton);
        tgl.setOnCheckedChangeListener(this);
        /*
        btn1.setOnClickListener( this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        */

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i1=new Intent(MainActivity.this,home.class);
                startActivity(i1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i2=new Intent(MainActivity.this,signup.class);
                startActivity(i2);

            }
        });

    }



    /*

    public void onClick(View v)
    {


        if(v.getId()==R.id.buttonLogin)
        {
            //validate the userid and password using server
            //if validated

            login();


            //else divert back to same page
            // Toast.makeText(this,"invalid userid or password!",Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.buttonSkip)
        {
            Toast.makeText(MainActivity.this,"yes",Toast.LENGTH_LONG).show();

            Intent i1=new Intent(this,home.class);
            startActivity(i1);

        }
        else if(v.getId()==R.id.buttonSignUp)
        {
            //Toast.makeText(this,"invalid userid or password!",Toast.LENGTH_LONG).show();
            Intent i2=new Intent(this,signup.class);
            startActivity(i2);
        }
    }
    */


    /*USER LOGIN START*/
    private String login()
    {

        String stat="fail";

        final String rollno=et1.getText().toString();
        final String password=et2.getText().toString();

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("rollno", rollno);
        paramMap.put("password", password);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.SERVER_LOGIN_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status))
                    {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        //prefs.edit().putBoolean("isMobile", Boolean.valueOf(mobile)).commit();
                        SharedPreferences.Editor editor= prefs.edit();
                        editor.putString("rollno",rollno);
                        editor.commit();

                        Intent i=new Intent(MainActivity.this,home.class);
                        //i.putExtra("userid",userid);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
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

    /*USER LOGIN START*/

    /*MENTOR LOGIN START*/
    public  String mentorLogin()
    {
        String stat="fail";

        final String rollno=et1.getText().toString();
        String password=et2.getText().toString();

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("mentor_rollno", rollno);
        paramMap.put("password", password);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.MENTOR_LOGIN_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status))
                    {
                        Toast.makeText(MainActivity.this, "Mentor Login successful", Toast.LENGTH_LONG).show();


                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        //prefs.edit().putBoolean("isMobile", Boolean.valueOf(mobile)).commit();
                        SharedPreferences.Editor editor= prefs.edit();
                        editor.putString("mentor_rollno",rollno);
                        editor.commit();
                        Intent i=new Intent(MainActivity.this,HomeMentor.class);
                        //i.putExtra("userid",userid);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
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

        return  stat;
    }
    /*MENTOR LOGIN START*/


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {

        if(b)
        {
            Toast.makeText(MainActivity.this,"Mentor Login",Toast.LENGTH_LONG).show();
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                   // Toast.makeText(MainActivity.this,"login",Toast.LENGTH_LONG).show();
                    mentorLogin();

                }
            });


        }
        else
        {
           Toast.makeText(MainActivity.this,"User Login",Toast.LENGTH_LONG).show();
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    login();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i1=new Intent(MainActivity.this,home.class);
                    startActivity(i1);
                }
            });

            btn3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i2=new Intent(MainActivity.this,signup.class);
                    startActivity(i2);
                }
            });

        }
    }
}

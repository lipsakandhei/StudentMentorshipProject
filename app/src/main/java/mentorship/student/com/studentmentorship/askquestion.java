package mentorship.student.com.studentmentorship;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class askquestion extends AppCompatActivity implements View.OnClickListener
{
    EditText etAskTitle,etAskDescription;
    SharedPreferences settings;
    String rollno;
    private Spinner spinner1, spinner2;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askquestion);

        etAskTitle=(EditText)findViewById(R.id.etAskTitle);
        etAskDescription=(EditText)findViewById(R.id.etAskDescription);

        settings = PreferenceManager.getDefaultSharedPreferences(askquestion.this);

        rollno = settings.getString("rollno", "");

        Button bt1=(Button)findViewById(R.id.button7);
        bt1.setOnClickListener(this);

       // addItemsOnSpinner2();
       // addListenerOnButton();
       // addListenerOnSpinnerItemSelection();
    }


    @Override
    public void onClick(View view)
    {
         String title=etAskTitle.getText().toString();
         String description=etAskDescription.getText().toString();

        if(title.length()==0)
        {
            Toast.makeText(askquestion.this,"Title field is blank",Toast.LENGTH_LONG).show();
        }
        else
        {

            AsyncHttpClient asyncHttpClient=new AsyncHttpClient();

            HashMap<String,String> stringHashMap=new HashMap<String, String>();
            stringHashMap.put("title",title);
            stringHashMap.put("description",description);
            stringHashMap.put("rollno",rollno);
            RequestParams params=new RequestParams(stringHashMap);
            final ProgressDialog pd = new ProgressDialog(askquestion.this);

            asyncHttpClient.post(IpConfig.ASK_QUESTION_URL, params, new AsyncHttpResponseHandler()
            {


                @Override
                public void onStart()
                {
                    super.onStart();
                    pd.setMessage("Loading....");
                    pd.show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
                {
                    try
                    {
                        JSONObject jsonObject=new JSONObject(new String(responseBody));
                        String statussuccess= jsonObject.getString("status");
                        Log.d("statussuccess",statussuccess);

                        if(statussuccess.equals("success"))
                        {
                            Toast.makeText(askquestion.this,"Thank U",Toast.LENGTH_LONG).show();
                            etAskTitle.setText("");
                            etAskDescription.setText("");
                        }
                        else
                        {
                            Toast.makeText(askquestion.this,"Error",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    Log.d("onSuccess", Integer.toString(statusCode));

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
                {
                    pd.cancel();
                    Log.d("onFailure", Integer.toString(statusCode));

                }

                @Override
                public void onFinish()
                {
                    super.onFinish();
                    pd.cancel();
                }
            });

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater= new MenuInflater(this);
        inflater.inflate(R.menu.user_logout,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int menuItem= item.getItemId();
        if(menuItem==R.id.userLogout)
        {
            Toast.makeText(askquestion.this,"Logout",Toast.LENGTH_LONG).show();
            settings = this.getSharedPreferences("rollno", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent intent=new Intent(askquestion.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void addItemsOnSpinner2() {

      //  spinner2 = (Spinner) findViewById(R.id.spinner2);
       /* List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);*/
    }

    public void addListenerOnSpinnerItemSelection() {
        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

      /*  spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(askquestion.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });*/
    }


}

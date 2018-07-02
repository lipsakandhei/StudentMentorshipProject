package mentorship.student.com.studentmentorship;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import adapter.MentorAdapter;
import adapter.PrivateAnswerAdapter;
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.Mentor;
import model.PrivateAnswer;

public class askmentor extends AppCompatActivity implements View.OnClickListener
{

    EditText et1;
    SharedPreferences settings;
    private String rollno;


    private RecyclerView recycleerviewPrivate;
    private RecyclerView.LayoutManager layoutManager;
    private PrivateAnswerAdapter privateAnswerAdapter;  //doubt
    private ArrayList<PrivateAnswer> privateAnswerlist;
    private PrivateAnswer privateAnswer;

    Map<String, String> map;


    private String qesId;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askmentor);
        Button buttonAsk=(Button)findViewById(R.id.buttonAsk);
        et1=(EditText)findViewById(R.id.editTextPrivQuestion);

        settings = PreferenceManager.getDefaultSharedPreferences(askmentor.this);
        rollno = settings.getString("rollno", "");

        buttonAsk.setOnClickListener(this);



        recycleerviewPrivate=(RecyclerView)findViewById(R.id.recycleerviewPrivate);

        if (recycleerviewPrivate != null)
        {
            recycleerviewPrivate.setHasFixedSize(true);
        }

        layoutManager = new GridLayoutManager(askmentor.this, 1);
        recycleerviewPrivate.setLayoutManager(layoutManager);

        recycleerviewPrivate.addItemDecoration(new
                DividerItemDecoration(askmentor.this,
                DividerItemDecoration.VERTICAL));

        RequestParams params = new RequestParams();
        invokeWS(params);




    }

    public void invokeWS(RequestParams params)
    {
        // Show Progress Dialog
       // prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("rollno",rollno);


        client.post(IpConfig.PRIVATE_ANSWER_URL, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                // When the response returned by REST has Http response code '200'
                // Hide Progress Dialog
               // prgDialog.hide();
                String response = null;
                try {

                    response = new String(responseBody, "UTF-8"); //DOUBT

                    JSONArray jsonArray = new JSONArray(response);
                    privateAnswerlist=new ArrayList<PrivateAnswer>();



                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj = new JSONObject(jsonArray.getString(i));
                        map = parseJsonToMap(obj, null);
                        //listItems.add(map.get("dept_name"));

                        privateAnswer=new PrivateAnswer();
                        privateAnswer.setQid(map.get("qid"));
                        privateAnswer.setQuestion(map.get("title"));

                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        privateAnswerlist.add(privateAnswer);  //DOUBT
                    }


                    privateAnswerAdapter=new PrivateAnswerAdapter(privateAnswerlist);

                    recycleerviewPrivate.setAdapter(privateAnswerAdapter);

                    privateAnswerAdapter.notifyDataSetChanged(); //for scrolling properly

                    recycleerviewPrivate.addOnItemTouchListener(new RecyclerviewListener(askmentor.this, new RecyclerviewListener.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {

                            qesId=privateAnswerlist.get(position).getQid();
                            question=privateAnswerlist.get(position).getQuestion();
                            // Toast.makeText(MessengerActivity.this,qesId+"\n"+userId+"\n"+mentorUserid,Toast.LENGTH_LONG).show();
                            Log.d("qesId",qesId);
                            Intent i=new Intent(askmentor.this,AnswerActivity.class);
                            i.putExtra("qesId",qesId);
                            startActivity(i);


                        }
                    }));



                } catch (JSONException e) {
                    Log.d("ERROR",response);
                    //Toast.makeText(getActivity().getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!:"+response, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                catch (UnsupportedEncodingException un){
                    Log.d("ERROR","Unsupported Exception");
                    un.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {

            }
        });
    }


    public static Map<String,String> parseJsonToMap(JSONObject json , Map<String,String> out) throws JSONException
    {

        if(out==null)
            out = new HashMap<String,String>();
        Iterator<String> keys = json.keys();
        while(keys.hasNext())
        {
            String key = keys.next();
            String val = null;
            try
            {
                JSONObject value = json.getJSONObject(key);
                parseJsonToMap(value,out);
            }
            catch(Exception e)
            {
                val = json.getString(key);
            }

            if(val != null)
            {
                out.put(key,val);
            }
        }
        return out;
    }

    @Override
    public void onClick(View view)
    {
         String question=et1.getText().toString();

        if(question.length()==0)
        {
            Toast.makeText(askmentor.this,"Title field is blank",Toast.LENGTH_LONG).show();
        }
        else
        {
            AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
            HashMap<String,String> stringHashMap=new HashMap<String, String>();
            stringHashMap.put("question",question);
            stringHashMap.put("rollno",rollno);
            RequestParams params=new RequestParams(stringHashMap);

            asyncHttpClient.post(IpConfig.ASK_MENTOR_URL, params, new AsyncHttpResponseHandler()
            {
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
                            Toast.makeText(askmentor.this,"Thank U",Toast.LENGTH_LONG).show();
                            et1.setText("");
                        }
                        else
                        {
                            Toast.makeText(askmentor.this,"Error",Toast.LENGTH_LONG).show();
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
            Toast.makeText(askmentor.this,"Logout",Toast.LENGTH_LONG).show();
            settings = this.getSharedPreferences("rollno", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent intent=new Intent(askmentor.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}


package mentorship.student.com.studentmentorship;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.Mentor;

public class MessengerActivity extends AppCompatActivity
{

    private RecyclerView recycleerviewMessenger;
    private RecyclerView.LayoutManager layoutManager;
    private MentorAdapter mentorAdapter;
    private ArrayList<Mentor> mentorArrayList;
    private Mentor mentor;

    Map<String, String> map;
    ProgressDialog prgDialog;

    private String qesId;
    private String rollno;

    private SharedPreferences mentorSettings;
    private String mentor_rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);


        mentorSettings = PreferenceManager.getDefaultSharedPreferences(MessengerActivity.this);

        mentor_rollno = mentorSettings.getString("mentor_rollno", "");
        Log.d("mentor_rollno",mentor_rollno);

        prgDialog = new ProgressDialog(MessengerActivity.this);
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);



        recycleerviewMessenger=(RecyclerView)findViewById(R.id.recycleerviewMessenger);

        if (recycleerviewMessenger != null)
        {
            recycleerviewMessenger.setHasFixedSize(true);
        }

        layoutManager = new GridLayoutManager(MessengerActivity.this, 1);
        recycleerviewMessenger.setLayoutManager(layoutManager);

        recycleerviewMessenger.addItemDecoration(new
                DividerItemDecoration(MessengerActivity.this,
                DividerItemDecoration.VERTICAL));

        RequestParams params = new RequestParams();
        invokeWS(params);

    }


    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params)
    {
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("mentor_rollno",mentor_rollno);


        client.post(IpConfig.PRIVATE_URL, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                // When the response returned by REST has Http response code '200'
                // Hide Progress Dialog
                prgDialog.hide();
                String response = null;
                try {

                    response = new String(responseBody, "UTF-8"); //DOUBT

                    JSONArray jsonArray = new JSONArray(response);
                    mentorArrayList=new ArrayList<Mentor>();



                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj = new JSONObject(jsonArray.getString(i));
                        map = parseJsonToMap(obj, null);
                        //listItems.add(map.get("dept_name"));

                        mentor=new Mentor();
                        mentor.setMentorQes(map.get("title"));
                        mentor.setMentorQesId(map.get("qid"));
                        mentor.setUserId(map.get("rollno"));
                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        mentorArrayList.add(mentor);  //DOUBT
                    }


                    mentorAdapter=new MentorAdapter(mentorArrayList);

                    recycleerviewMessenger.setAdapter(mentorAdapter);

                    mentorAdapter.notifyDataSetChanged(); //for scrolling properly

                    recycleerviewMessenger.addOnItemTouchListener(new RecyclerviewListener(MessengerActivity.this, new RecyclerviewListener.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {

                            qesId=mentorArrayList.get(position).getMentorQesId();
                            rollno=mentorArrayList.get(position).getUserId();
                           // Toast.makeText(MessengerActivity.this,qesId+"\n"+userId+"\n"+mentorUserid,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(MessengerActivity.this,MentorAnswer.class);
                            i.putExtra("qesId",qesId);
                            i.putExtra("rollno",rollno);
                            i.putExtra("mentor_rollno",mentor_rollno);
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
}

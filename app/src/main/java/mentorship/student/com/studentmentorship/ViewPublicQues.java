package mentorship.student.com.studentmentorship;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import adapter.PublicQuesAnsAdapter;
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.Mentor;
import model.PublicQuesAns;

public class ViewPublicQues extends AppCompatActivity
{
    Map<String, String> map;
    private String qesId,ansId,rollno;
    private PublicQuesAns publicQuesAns;
    private PublicQuesAnsAdapter publicQuesAnsAdapter;
    private ArrayList<PublicQuesAns> publicQuesAnsArrayList;
    private RecyclerView recycleerviewPublicQuesAns;
    private RecyclerView.LayoutManager layoutManager;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_public_ques);
        TextView TextViewTitle=(TextView)findViewById(R.id.TextViewTitle);
        TextView TextViewDescription=(TextView)findViewById(R.id.TextViewDescription);
        TextView TextViewAskedBy=(TextView)findViewById(R.id.TextViewAskedBy);

        Bundle ob=getIntent().getExtras();
        qesId=ob.getString("qesId");
        String title=ob.getString("title");
        String description=ob.getString("description");
        final String AskedBy=ob.getString("AskedBy");

        Toast.makeText(ViewPublicQues.this,"quesId: "+qesId,Toast.LENGTH_LONG).show();

        TextViewTitle.setText(title);
        TextViewDescription.setText(description);
        TextViewAskedBy.setText(AskedBy);

        settings = PreferenceManager.getDefaultSharedPreferences(ViewPublicQues.this);

        rollno = settings.getString("rollno", "");

        recycleerviewPublicQuesAns=(RecyclerView)findViewById(R.id.recycleerviewPublicQuesAns);
        layoutManager=new GridLayoutManager(ViewPublicQues.this,1);
        recycleerviewPublicQuesAns.setLayoutManager(layoutManager);

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
        //prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("qesId",qesId);


        client.post(IpConfig.FETECH_PUBLIC_ANSWAR, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                // When the response returned by REST has Http response code '200'
                // Hide Progress Dialog
                //prgDialog.hide();
                String response = null;
                try {

                    response = new String(responseBody, "UTF-8"); //DOUBT

                    JSONArray jsonArray = new JSONArray(response);
                    publicQuesAnsArrayList=new ArrayList<PublicQuesAns>();



                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj = new JSONObject(jsonArray.getString(i));
                        map = parseJsonToMap(obj, null);
                        //listItems.add(map.get("dept_name"));

                        publicQuesAns=new PublicQuesAns();
                        publicQuesAns.setQuesAns(map.get("answer"));
                        publicQuesAns.setQuesAnsMentor(map.get("mentorsname"));
                        publicQuesAns.setQuesAnsId(map.get("aid"));

                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        publicQuesAnsArrayList.add(publicQuesAns);  //DOUBT
                    }


                    publicQuesAnsAdapter=new PublicQuesAnsAdapter(publicQuesAnsArrayList);

                    recycleerviewPublicQuesAns.setAdapter(publicQuesAnsAdapter);

                    publicQuesAnsAdapter.notifyDataSetChanged(); //for scrolling properly

                    recycleerviewPublicQuesAns.addOnItemTouchListener(new RecyclerviewListener(ViewPublicQues.this, new RecyclerviewListener.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {


                            ansId=publicQuesAnsArrayList.get(position).getQuesAnsId();
                            AlertDialog.Builder alBuilder=new AlertDialog.Builder(ViewPublicQues.this);
                            alBuilder.setPositiveButton("Upvote", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    Log.d("ansId",ansId);
                                    AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
                                    Map<String,String> map=new HashMap<String, String>();
                                    map.put("ansId",ansId);
                                    map.put("rollno",rollno);
                                    RequestParams requestParams=new RequestParams(map);
                                    asyncHttpClient.post(IpConfig.UPVOTE_URL,requestParams,new JsonHttpResponseHandler(){

                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                                        {
                                            super.onSuccess(statusCode, headers, response);
                                            try
                                            {
                                                String status= response.getString("status");
                                                if(status.equals("present"))
                                                {
                                                    Toast.makeText(ViewPublicQues.this,"already upvoted!",Toast.LENGTH_LONG).show();
                                                }
                                                else if(status.equals("success"))
                                                {
                                                    Toast.makeText(ViewPublicQues.this,"upvoted!",Toast.LENGTH_LONG).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(ViewPublicQues.this,"ERROR!",Toast.LENGTH_LONG).show();
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


                                        }
                                    });
                                }
                            });

                            /*
                            alBuilder.setNeutralButton("Downvote", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    Log.d("ansId",ansId);
                                }
                            });
                            */
                            alBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    Log.d("ansId","Close");
                                }
                            });
                            alBuilder.show();

                           /*
                            Intent i=new Intent(MessengerActivity.this,MentorAnswer.class);
                            i.putExtra("qesId",qesId);
                            i.putExtra("userId",userId);
                            i.putExtra("mentorUserid",mentorUserid);
                            startActivity(i);
                            */

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

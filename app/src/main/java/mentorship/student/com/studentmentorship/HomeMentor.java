package mentorship.student.com.studentmentorship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
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

import adapter.PublicQuesAdapter;
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.PublicQus;

public class HomeMentor extends AppCompatActivity implements View.OnClickListener
{
    ListView lv1;
    ScrollView lv2;

    private SharedPreferences mentorSettings;
    private String mentor_rollno;

    private RecyclerView recycleerviewMentor;
    private RecyclerView.LayoutManager layoutManager;
    private PublicQuesAdapter publicQuesAdapter;
    private ArrayList<PublicQus> publicQusArrayList;
    private PublicQus publicQus;
    Map<String, String> map;
    private String qesId;
    private String qesTitle;
    private String qesDescription;
    private String rollno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mentor);
        Button bt1=(Button)findViewById(R.id.buttonInbox);
        Button bt2=(Button)findViewById(R.id.buttonAskQuestion);
        bt2.setVisibility(View.GONE);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        /*
        lv1=(ListView)findViewById(R.id.listviewfeat);
        lv2=(ScrollView)findViewById(R.id.listviewNewsMent);
        */


        mentorSettings = PreferenceManager.getDefaultSharedPreferences(HomeMentor.this);

        mentor_rollno = mentorSettings.getString("mentor_rollno", "");
       // Log.d("mentorUserid",mentorUserid);

          /**/
        recycleerviewMentor=(RecyclerView)findViewById(R.id.recycleerviewMentor);
        if (recycleerviewMentor != null)
        {
            recycleerviewMentor.setHasFixedSize(true);
        }
        layoutManager=new GridLayoutManager(HomeMentor.this,1);
        recycleerviewMentor.setLayoutManager(layoutManager);
        RequestParams params = new RequestParams();
        invokeWS(params);
        /**/

    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.activity_askquestion)
        {

        }
        else if(v.getId()==R.id.buttonInbox)
        {
            Intent intent=new Intent(HomeMentor.this,MessengerActivity.class);
            startActivity(intent);
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
            Toast.makeText(HomeMentor.this,"Logout",Toast.LENGTH_LONG).show();
            mentorSettings = this.getSharedPreferences("rollno", Context.MODE_PRIVATE);
            mentorSettings.edit().clear().commit();
            Intent intent=new Intent(HomeMentor.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params)
    {
        // Show Progress Dialog

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        //params.put("mentorUserid",mentorUserid);


        client.post(IpConfig.PUBLIC_QUES, params, new AsyncHttpResponseHandler() {
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
                    publicQusArrayList=new ArrayList<PublicQus>();



                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj = new JSONObject(jsonArray.getString(i));
                        map = parseJsonToMap(obj, null);
                        //listItems.add(map.get("dept_name"));

                        publicQus=new PublicQus();
                        publicQus.setPublicQuesTitle(map.get("title"));
                        publicQus.setPublicAskBy(map.get("name"));
                        publicQus.setPublicQuesId(map.get("qid"));
                        publicQus.setPublicUserId(map.get("rollno"));
                        publicQus.setPublicQuesDescription(map.get("description"));
                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        publicQusArrayList.add(publicQus);  //DOUBT
                    }


                    publicQuesAdapter=new PublicQuesAdapter(publicQusArrayList);

                    recycleerviewMentor.setAdapter(publicQuesAdapter);

                    publicQuesAdapter.notifyDataSetChanged(); //for scrolling properly

                    recycleerviewMentor.addOnItemTouchListener(new RecyclerviewListener(HomeMentor.this, new RecyclerviewListener.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {

                            qesId=publicQusArrayList.get(position).getPublicQuesId();
                            qesTitle=publicQusArrayList.get(position).getPublicQuesTitle();
                            qesDescription=publicQusArrayList.get(position).getPublicQuesDescription();
                            rollno=publicQusArrayList.get(position).getPublicUserId();
                           // userId=mentorArrayList.get(position).getUserId();
                            // Toast.makeText(MessengerActivity.this,qesId+"\n"+userId+"\n"+mentorUserid,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(HomeMentor.this,Answer_Public_Question.class);
                            i.putExtra("qesId",qesId);
                            i.putExtra("qesTitle",qesTitle);
                            i.putExtra("qesDescription",qesDescription);
                            i.putExtra("rollno",rollno);
                            i.putExtra("mentor_rollno",mentor_rollno);

                           // i.putExtra("userId",userId);
                           // i.putExtra("mentorUserid",mentorUserid);
                            startActivity(i);


                            /*
                            Intent i=new Intent(HomeMentor.this,Answer_Public_Question.class);
                            i.putExtra("qesId",qesId);
                            startActivity(i);
                            */
                            Log.d("qesId",qesId);
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

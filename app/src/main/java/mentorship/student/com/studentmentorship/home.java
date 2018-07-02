package mentorship.student.com.studentmentorship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
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

import adapter.DetailsAdapter;
import adapter.MentorAdapter;
import adapter.PublicQuesAdapter;
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.Details;
import model.Mentor;
import model.PublicQus;

public class home extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<String>features,newsfeed;

    SharedPreferences settings;
    String rollno;
    Intent intent;
    private RecyclerView recycleerviewDetails;
    private RecyclerView.LayoutManager layoutManager;
    private DetailsAdapter detailsAdapter;
    private ArrayList<Details> detailsArrayList;


    private RecyclerView recycleerviewNewsfeeds;
    private RecyclerView.LayoutManager newsfeedLayoutManager;
    private PublicQuesAdapter publicQuesAdapter;
    private ArrayList<PublicQus> publicQusArrayList;
    private PublicQus publicQus;
    Map<String, String> map;
    private String qesId;
    private String title;
    private String description;
    private String AskedBy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*userid=getIntent().getExtras().getString("userid");*/

        settings = PreferenceManager.getDefaultSharedPreferences(home.this);

        rollno = settings.getString("rollno", "");
        detailsArrayList=new ArrayList<Details>();
        recycleerviewDetails=(RecyclerView)findViewById(R.id.recycleerviewDetails);
        layoutManager=new GridLayoutManager(this,2);
        recycleerviewDetails.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleerviewDetails.getContext(), DividerItemDecoration.VERTICAL);
        DividerItemDecoration dividerItem = new DividerItemDecoration(recycleerviewDetails.getContext(), DividerItemDecoration.HORIZONTAL);

        recycleerviewDetails.addItemDecoration(dividerItemDecoration);
        recycleerviewDetails.addItemDecoration(dividerItem);
        loadDetails();

        /**/
        recycleerviewNewsfeeds=(RecyclerView)findViewById(R.id.recycleerviewNewsfeeds);
        if (recycleerviewNewsfeeds != null)
        {
            recycleerviewNewsfeeds.setHasFixedSize(true);
        }
        newsfeedLayoutManager=new GridLayoutManager(home.this,1);
        recycleerviewNewsfeeds.setLayoutManager(newsfeedLayoutManager);
        RequestParams params = new RequestParams();
        invokeWS(params);
        /**/


        Button btn1=(Button)findViewById(R.id.buttonInbox);
        Button buttonAskMentor=(Button)findViewById(R.id.buttonAskMentor);
        btn1.setOnClickListener(this);
        buttonAskMentor.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonInbox)
        {
            //check if user has logged in or not
            //if logged in

            if(!rollno.equals(null) && !"".equals(rollno))
            {
                Log.d("rollno",rollno);
                Intent i=new Intent(this,askquestion.class);
                startActivity(i);
            }
            else
            {
                Intent i1 =new Intent(this,signupdialogbox.class);
                startActivity(i1);
            }


            //else
            //if user has not logged in

        }
        else if(v.getId()==R.id.buttonAskMentor)
        {
            if(!rollno.equals(null) && !"".equals(rollno))
            {
                Log.d("rollno",rollno);
                Intent i1=new Intent(this,askmentor.class);
                startActivity(i1);
            }
            else
            {
                Intent i1 =new Intent(this,signupdialogbox.class);
                startActivity(i1);
            }

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
            Toast.makeText(home.this,"Logout",Toast.LENGTH_LONG).show();
            settings = this.getSharedPreferences("rollno", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            intent=new Intent(home.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void loadDetails()
    {
       // detailsArrayList.add(new Details("About Us",R.drawable.about));
       // detailsArrayList.add(new Details("Book List",R.drawable.booklist));
        detailsArrayList.add(new Details("Central Time Table",R.drawable.timetable));
        detailsArrayList.add(new Details("NITR map",R.drawable.map));
        detailsArrayList.add(new Details("Club Info",R.drawable.info));
        detailsArrayList.add(new Details("Miscellaneous",R.drawable.phone));
        detailsAdapter=new DetailsAdapter(detailsArrayList);
        recycleerviewDetails.setAdapter(detailsAdapter);
        detailsAdapter.notifyDataSetChanged();
        recycleerviewDetails.addOnItemTouchListener(new RecyclerviewListener(home.this, new RecyclerviewListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                switch (position)
                {
                    case 0:
                    {
                        //Toast.makeText(home.this,"About Us",Toast.LENGTH_LONG).show();
                        intent=new Intent(home.this,TimeTableInfo.class);
                        startActivity(intent);
                        break;
                    }

                    case 1:
                    {
                        //Toast.makeText(home.this,"Book List",Toast.LENGTH_LONG).show();
                        intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.in/maps/place/National+Institute+of+Technology,+Rourkela/@22.2534697,84.8989434,17z/data=!3m1!4b1!4m5!3m4!1s0x3a201e80b8837bb7:0xf4902f1b9293816b!8m2!3d22.2534697!4d84.9011321?hl=en"));
                        startActivity(intent);
                        break;
                    }

                    case 2:
                    {
                       // Toast.makeText(home.this,"Central Time Table",Toast.LENGTH_LONG).show();

                        intent=new Intent(home.this,Club_info.class);
                        startActivity(intent);
                        break;
                    }

                    case 3:
                    {
                       // Toast.makeText(home.this,"NITR Map",Toast.LENGTH_LONG).show();
                        intent=new Intent(home.this,Features.class);
                        startActivity(intent);
                        break;
                    }

                   /* case 4:
                    {
                        //Toast.makeText(home.this,"Club Info",Toast.LENGTH_LONG).show();
                        intent=new Intent(home.this,Club_info.class);
                        startActivity(intent);
                        break;
                    }

                    case 5:
                    {
                       // Toast.makeText(home.this,"Phone Numbers",Toast.LENGTH_LONG).show();
                        intent=new Intent(home.this,Phone_no.class);
                        startActivity(intent);
                        break;
                    }*/
                }
            }
        }));
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
                        publicQus.setPublicQuesDescription(map.get("description"));
                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        publicQusArrayList.add(publicQus);  //DOUBT
                    }


                    publicQuesAdapter=new PublicQuesAdapter(publicQusArrayList);

                    recycleerviewNewsfeeds.setAdapter(publicQuesAdapter);

                    publicQuesAdapter.notifyDataSetChanged(); //for scrolling properly

                    recycleerviewNewsfeeds.addOnItemTouchListener(new RecyclerviewListener(home.this, new RecyclerviewListener.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {

                            /*
                            qesId=mentorArrayList.get(position).getMentorQesId();
                            userId=mentorArrayList.get(position).getUserId();
                            // Toast.makeText(MessengerActivity.this,qesId+"\n"+userId+"\n"+mentorUserid,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(MessengerActivity.this,MentorAnswer.class);
                            i.putExtra("qesId",qesId);
                            i.putExtra("userId",userId);
                            i.putExtra("mentorUserid",mentorUserid);
                            startActivity(i);
                            */
                            qesId=publicQusArrayList.get(position).getPublicQuesId();
                            title=publicQusArrayList.get(position).getPublicQuesTitle();
                            description=publicQusArrayList.get(position).getPublicQuesDescription();
                            AskedBy=publicQusArrayList.get(position).getPublicAskBy();

                            Intent i=new Intent(home.this,ViewPublicQues.class);
                            i.putExtra("qesId",qesId);
                            i.putExtra("title",title);
                            i.putExtra("description",description);
                            i.putExtra("AskedBy",AskedBy);
                            startActivity(i);
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


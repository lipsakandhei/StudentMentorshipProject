package mentorship.student.com.studentmentorship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

import adapter.AnswerAdapter;
import adapter.MentorAdapter;
import cz.msebera.android.httpclient.Header;
import event.RecyclerviewListener;
import model.Answer;
import model.Mentor;

public class AnswerActivity extends AppCompatActivity
{
    private Bundle bundle;
    private String qesId;
    Map<String, String> map;
    private RecyclerView recycleerviewAnswer;
    private RecyclerView.LayoutManager layoutManager;
    private AnswerAdapter answerAdapter;
    private Answer answer;
    private Answer answerdate;

    private ArrayList<Answer> answerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        bundle=getIntent().getExtras();
        qesId=bundle.getString("qesId");

        recycleerviewAnswer=(RecyclerView)findViewById(R.id.recycleerviewAnswer);
        layoutManager=new GridLayoutManager(AnswerActivity.this,1);
        recycleerviewAnswer.setLayoutManager(layoutManager);

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

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("qesId",qesId);


        client.post(IpConfig.ANSWER_URL, params, new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                // When the response returned by REST has Http response code '200'
                // Hide Progress Dialog

                String response = null;
                try {

                    response = new String(responseBody, "UTF-8"); //DOUBT

                    JSONArray jsonArray = new JSONArray(response);
                    answerArrayList=new ArrayList<Answer>();



                    for (int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject obj = new JSONObject(jsonArray.getString(i));
                        map = parseJsonToMap(obj, null);
                        //listItems.add(map.get("dept_name"));

                        answer=new Answer();
                        answer.setQuesAnswer(map.get("answer"));
                        answer.setAnswerdate(map.get("answer_date"));
                        //answerArrayList.setMentorQesId(map.get("qid"));

                        //mentor.setUserId(map.get("userid"));
                        /*
                        departments.setNewsTitle(map.get("dh_title"));
                        departments.setNewsDate(map.get("dh_date"));
                        */
                        answerArrayList.add(answer);  //DOUBT
                    }


                    answerAdapter=new AnswerAdapter(answerArrayList);

                    recycleerviewAnswer.setAdapter(answerAdapter);

                    answerAdapter.notifyDataSetChanged(); //for scrolling properly




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

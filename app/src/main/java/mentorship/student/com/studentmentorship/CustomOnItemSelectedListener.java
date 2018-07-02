package mentorship.student.com.studentmentorship;

/**
 * Created by HP on 05-12-2017.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class CustomOnItemSelectedListener  implements OnItemSelectedListener{

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
      /*  Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/

       /* String stat="fail";

        final String type=parent.getItemAtPosition(pos).toString();
      //  String password=et2.getText().toString();

        Map<String, String> paramMap = new HashMap<>();

        paramMap.put("type", type);
     //   paramMap.put("password", password);

        RequestParams params = new RequestParams(paramMap);

        RestClient.post(IpConfig.MENTOR_LOGIN_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                try{
                    String status = jsonResponse.getString("status");
                    Log.d(AppConstants.TAG, status);

                    if("success".equals(status))
                    {
                        Toast.makeText(CustomOnItemSelectedListener.this , "Mentor Login successful", Toast.LENGTH_LONG).show();


                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        //prefs.edit().putBoolean("isMobile", Boolean.valueOf(mobile)).commit();
                        SharedPreferences.Editor editor= prefs.edit();
                        editor.putString("type",type);
                        editor.commit();
                        Intent i=new Intent(CustomOnItemSelectedListener.this,HomeMentor.class);
                        //i.putExtra("userid",userid);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(CustomOnItemSelectedListener.this, "Login failed", Toast.LENGTH_LONG).show();
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
        });*/



    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }




}

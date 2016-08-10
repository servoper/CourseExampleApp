package bg.codingschool.myapplication1.presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Nikolay Vasilev on 7/13/2016.
 */
public class WeatherPresenter {

    private static final String TAG = WeatherPresenter.class.getSimpleName();
    private static RequestQueue mRequestQueue;

    public static void getWeather(Context con, int cityId,
                                  Response.Listener<JSONObject> onSucceslistener,
                                  Response.ErrorListener errorListener) {

        mRequestQueue = Volley.newRequestQueue(con);

        String url = "http://jsonplaceholder.typicode.com/posts/1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, onSucceslistener, errorListener);

        mRequestQueue.add(request);
    }
}

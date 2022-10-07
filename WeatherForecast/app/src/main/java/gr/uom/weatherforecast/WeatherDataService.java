package gr.uom.weatherforecast;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {
    String longitude;
    String latitude;
    Context context;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(String cityLatLong);
    }

    public void getCityLatLong(String cityName, VolleyResponseListener volleyResponseListener){
        String API_KEY = context.getString(R.string.API_KEY);
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + cityName
                + "?unitGroup=us&include=current&key=" + API_KEY + "&contentType=json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        longitude = "";
                        latitude = "";
                        try {
                            longitude = response.getString("longitude");
                            latitude = response.getString("latitude");
                            volleyResponseListener.onResponse( latitude + " " + longitude);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError("Something wrong");
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface ForecastByLatLongResponse{
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    public void getCityForecastByLatLong(String LatLong, ForecastByLatLongResponse forecastByLatLongResponse){
        String API_KEY = context.getString(R.string.API_KEY);
        String url1 = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + LatLong + "?key=" + API_KEY;
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();

        //get jsonobject
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray weatherList = response.getJSONArray("days");

                            for(int i=0; i < weatherList.length(); i++){
                                WeatherReportModel one_day = new WeatherReportModel();

                                JSONObject first_day_from_api = (JSONObject) weatherList.get(i);

                                one_day.setDatetime(first_day_from_api.getString("datetime"));
                                one_day.setMax_temp(first_day_from_api.getLong("tempmax"));
                                one_day.setMin_temp(first_day_from_api.getLong("tempmin"));
                                one_day.setHumidity(first_day_from_api.getLong("humidity"));
                                one_day.setWindspeed(first_day_from_api.getLong("windspeed"));
                                one_day.setWind_direction(first_day_from_api.getLong("winddir"));
                                one_day.setCloudcover(first_day_from_api.getLong("cloudcover"));
                                weatherReportModels.add(one_day);
                            }
                            forecastByLatLongResponse.onResponse(weatherReportModels);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}

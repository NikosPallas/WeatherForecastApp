package gr.uom.weatherforecast;

public class WeatherReportModel {

    private String datetime;
    private float max_temp;
    private float min_temp;
    private float humidity;
    private float windspeed;
    private float wind_direction;
    private float cloudcover;

    public WeatherReportModel(String datetime, float max_temp, float min_temp, float humidity, float windspeed, float wind_direction, float cloudcover) {
        this.datetime = datetime;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.wind_direction = wind_direction;
        this.cloudcover = cloudcover;
    }

    public WeatherReportModel() {
    }

    @Override
    public String toString() {
        return    "datetime='" + datetime + '\'' +
                ", max_temp=" + max_temp +
                ", min_temp=" + min_temp +
                ", humidity=" + humidity +
                ", windspeed=" + windspeed +
                ", wind_direction=" + wind_direction +
                ", cloudcover=" + cloudcover +
                '}';
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(float windspeed) {
        this.windspeed = windspeed;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public float getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(float cloudcover) {
        this.cloudcover = cloudcover;
    }
}

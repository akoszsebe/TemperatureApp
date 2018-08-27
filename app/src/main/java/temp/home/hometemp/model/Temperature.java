package temp.home.hometemp.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("temperature")
    private float temperature;

    public Temperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temperature=" + temperature +
                '}';
    }
}

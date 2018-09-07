package temp.home.hometemp.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import temp.home.hometemp.model.Temperature;

public interface TemperatureApiInterface {

    @GET("api/actualtemperature")
    Call<Temperature> getTemperature();
}

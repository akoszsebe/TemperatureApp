package temp.home.hometemp.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import temp.home.hometemp.model.Temperature;

public interface TempService {

    @GET("api/actualtemperature")
    Call<Temperature> getTemperature();
}

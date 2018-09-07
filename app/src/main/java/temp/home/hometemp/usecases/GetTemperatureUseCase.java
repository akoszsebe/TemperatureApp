package temp.home.hometemp.usecases;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import temp.home.hometemp.model.Temperature;
import temp.home.hometemp.network.interfaces.TemperatureApiInterface;

public class GetTemperatureUseCase  implements  UseCase<Integer,Temperature>{

    TemperatureApiInterface temperatureApiInterface;

    @Inject
    public GetTemperatureUseCase(TemperatureApiInterface temperatureApiInterface){
        super();
        this.temperatureApiInterface = temperatureApiInterface;
    }

    @Override
    public void execute(Integer parameter, final Callback<Temperature> callback) {
        Call<Temperature> call = temperatureApiInterface.getTemperature();
        call.enqueue(new retrofit2.Callback<Temperature>() {
            @Override
            public void onResponse(@NonNull Call<Temperature> call, @NonNull Response<Temperature> response) {
                Temperature resource = response.body();
                assert resource != null;
                callback.onSuccess(resource);
            }

            @Override
            public void onFailure(@NonNull Call<Temperature> call, @NonNull Throwable t) {
                call.cancel();
                callback.onError(new Exception());
            }
        });
    }
}

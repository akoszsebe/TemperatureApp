package temp.home.hometemp.main;

import android.support.annotation.NonNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import temp.home.hometemp.model.Temperature;
import temp.home.hometemp.rest.ApiClient;
import temp.home.hometemp.rest.TempService;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView mView;
    private ScheduledExecutorService syncTemperatureScheduler;
    private TempService tempService;

    public MainPresenter(MainContract.MvpView view){
        this.mView = view;
        this.tempService = ApiClient.getClient().create(TempService.class);
        this.syncTemperatureScheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void startScheduledExecutorService() {
        this.syncTemperatureScheduler.scheduleAtFixedRate(syncData, 0, 4, TimeUnit.SECONDS);
    }

    @Override
    public void stopScheduledExecutorService() {
        if (this.syncTemperatureScheduler != null) {
            this.syncTemperatureScheduler.shutdownNow();
        }
    }

    @Override
    public void restartScheduledExecutorService() {
        if (this.syncTemperatureScheduler == null){
            this.syncTemperatureScheduler = Executors.newSingleThreadScheduledExecutor();
            this.syncTemperatureScheduler.scheduleAtFixedRate(syncData, 0, 4, TimeUnit.SECONDS);
        }
        if(this.syncTemperatureScheduler.isShutdown()) {
            this.syncTemperatureScheduler = Executors.newSingleThreadScheduledExecutor();
            this.syncTemperatureScheduler.scheduleAtFixedRate(syncData, 0, 4, TimeUnit.SECONDS);
        }
    }


    private final Runnable syncData = new Runnable() {
        @Override
        public void run() {
            Call<Temperature> call = tempService.getTemperature();
            call.enqueue(new Callback<Temperature>() {
                @Override
                public void onResponse(@NonNull Call<Temperature> call, @NonNull Response<Temperature> response) {
                    Temperature resource = response.body();
                    assert resource != null;
                    final float _temperature = resource.getTemperature();
                    mView.displayCircularProgressbarChange(_temperature);
                    mView.displayTemperatureTextChange(_temperature);
                    mView.displayChartChange(_temperature);
                }

                @Override
                public void onFailure(@NonNull Call<Temperature> call, @NonNull Throwable t) {
                    call.cancel();
                }
            });
        }
    };
}

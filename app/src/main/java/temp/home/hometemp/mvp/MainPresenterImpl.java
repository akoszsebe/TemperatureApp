package temp.home.hometemp.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import temp.home.hometemp.model.Temperature;
import temp.home.hometemp.mvp.base.BasePresenter;
import temp.home.hometemp.mvp.base.MvpView;
import temp.home.hometemp.mvp.base.Presenter;
import temp.home.hometemp.network.interfaces.TemperatureApiInterface;
import temp.home.hometemp.usecases.GetTemperatureUseCase;
import temp.home.hometemp.usecases.UseCase;

public class MainPresenterImpl<V extends MainMvpView> extends BasePresenter<V> implements MainPresenter<V>, UseCase.Callback<Temperature> {

    private ScheduledExecutorService syncTemperatureScheduler;
    private GetTemperatureUseCase getTemperatureUseCase;

    @Inject
    public MainPresenterImpl(GetTemperatureUseCase getTemperatureUseCase){
        this.getTemperatureUseCase = getTemperatureUseCase;
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
            syncData1();
        }
    };

    private void syncData1() {
            getTemperatureUseCase.execute(1,this);
    }

    @Override
    public void onSuccess(Temperature result) {
        final float _temperature = result.getTemperature();
        baseView.displayCircularProgressbarChange(_temperature);
        baseView.displayTemperatureTextChange(_temperature);
        baseView.displayChartChange(_temperature);
    }

    @Override
    public void onError(Throwable throwable) {
        final float _temperature = 0;
        baseView.displayCircularProgressbarChange(_temperature);
        baseView.displayTemperatureTextChange(_temperature);
        baseView.displayChartChange(_temperature);
        baseView.showError("Network Error");
    }
}

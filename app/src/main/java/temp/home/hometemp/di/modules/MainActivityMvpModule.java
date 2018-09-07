package temp.home.hometemp.di.modules;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.mvp.MainPresenter;
import temp.home.hometemp.mvp.MainPresenterImpl;
import temp.home.hometemp.mvp.base.BasePresenter;
import temp.home.hometemp.mvp.base.MvpView;
import temp.home.hometemp.mvp.base.Presenter;
import temp.home.hometemp.usecases.GetTemperatureUseCase;

@Module
public class MainActivityMvpModule {
    private final MvpView mView;

    public MainActivityMvpModule(MvpView mView) {
        this.mView = mView;
    }

    @Provides
    public MvpView provideView() {
        return mView;
    }

    @Provides
    public MainPresenterImpl provideMainPresenter(GetTemperatureUseCase getTemperatureUseCase) {
        return new MainPresenterImpl(getTemperatureUseCase);
    }

    @Provides
    public Presenter provideBasePresenter() {
        return new BasePresenter();
    }


}

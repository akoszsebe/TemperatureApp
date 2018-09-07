package temp.home.hometemp.mvp;

import dagger.Provides;
import temp.home.hometemp.mvp.base.Presenter;

public interface MainPresenter<V extends MainMvpView> extends Presenter<V>{
    void startScheduledExecutorService();
    void stopScheduledExecutorService();
    void restartScheduledExecutorService();
}

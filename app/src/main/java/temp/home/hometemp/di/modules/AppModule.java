package temp.home.hometemp.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.App;

@Module
public class AppModule {

    private App initApplication;

    public AppModule(App initApplication) {
        this.initApplication = initApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return initApplication;
    }
}
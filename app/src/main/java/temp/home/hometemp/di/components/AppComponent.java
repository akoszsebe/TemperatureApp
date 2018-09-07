package temp.home.hometemp.di.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import temp.home.hometemp.App;
import temp.home.hometemp.di.modules.AppModule;
import temp.home.hometemp.di.modules.ContextModule;
import temp.home.hometemp.di.modules.RetrofitModule;
import temp.home.hometemp.di.modules.UseCaseModule;
import temp.home.hometemp.network.interfaces.TemperatureApiInterface;

@Singleton
@Component(modules = {AppModule.class, ContextModule.class, RetrofitModule.class, UseCaseModule.class})
public interface AppComponent {

    TemperatureApiInterface getTemperatureApiInterface();
    Application getApplication();
    Context getContext();


    void injectApplication(App app);


}

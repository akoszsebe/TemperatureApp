package temp.home.hometemp.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import temp.home.hometemp.activities.MainActivity;;

@Singleton
@Component(modules = {AppModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class
        })
public interface AppComponent {

    void inject(MainActivity mainActivity);
}

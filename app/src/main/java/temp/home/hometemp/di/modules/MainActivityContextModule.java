package temp.home.hometemp.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.activities.MainActivity;
import temp.home.hometemp.di.qualifier.ActivityContext;
import temp.home.hometemp.di.scopes.ActivityScope;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}

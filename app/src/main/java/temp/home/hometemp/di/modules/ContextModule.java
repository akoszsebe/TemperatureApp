package temp.home.hometemp.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.di.qualifier.ApplicationContext;
import temp.home.hometemp.di.scopes.ApplicationScope;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}

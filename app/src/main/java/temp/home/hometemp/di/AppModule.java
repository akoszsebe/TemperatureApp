package temp.home.hometemp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.example.MyExample;
import temp.home.hometemp.example.MyExampleImpl;

@Module
public class AppModule {
    @Provides
    @Singleton
    static MyExample provideMyExample() {
        return new MyExampleImpl();
    }
}

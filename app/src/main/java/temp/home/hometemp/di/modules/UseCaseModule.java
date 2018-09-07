package temp.home.hometemp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.network.interfaces.TemperatureApiInterface;
import temp.home.hometemp.usecases.GetTemperatureUseCase;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    GetTemperatureUseCase provideGetTemperatureUseCase( TemperatureApiInterface temperatureApiInterface) {
        return new GetTemperatureUseCase(temperatureApiInterface);
    }
}

package temp.home.hometemp;

import android.app.Activity;
import android.app.Application;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import temp.home.hometemp.di.components.DaggerMainActivityComponent;
import temp.home.hometemp.di.components.MainActivityComponent;
import temp.home.hometemp.di.components.AppComponent;
import temp.home.hometemp.di.components.DaggerAppComponent;
import temp.home.hometemp.di.modules.ActivityModule;
import temp.home.hometemp.di.modules.AppModule;
import temp.home.hometemp.di.modules.ChartModule;
import temp.home.hometemp.di.modules.ContextModule;
import temp.home.hometemp.di.modules.LineChartModule;
import temp.home.hometemp.di.modules.MainActivityMvpModule;
import temp.home.hometemp.mvp.base.MvpView;


public class App extends Application {

    public static App get(Activity activity){
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MainActivityComponent getMainActivityComponent(Activity activity, MvpView view, LineChart lineChart){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
        appComponent.injectApplication(this);
        MainActivityComponent mainActivityComponent =  DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityMvpModule(new MainActivityMvpModule(view))
                .activityModule(new ActivityModule(activity))
                .lineChartModule(new LineChartModule(lineChart))
                .chartModule(new ChartModule(activity,lineChart,new ArrayList<Entry>()))
                .build();
        return mainActivityComponent;
    }
}

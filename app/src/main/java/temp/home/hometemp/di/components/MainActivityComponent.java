package temp.home.hometemp.di.components;

import android.app.Activity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

import dagger.Component;
import temp.home.hometemp.activities.MainActivity;
import temp.home.hometemp.chart.MyChart;
import temp.home.hometemp.di.modules.ActivityModule;
import temp.home.hometemp.di.modules.ChartModule;
import temp.home.hometemp.di.modules.EntryModule;
import temp.home.hometemp.di.modules.LineChartModule;
import temp.home.hometemp.di.modules.MainActivityMvpModule;
import temp.home.hometemp.di.scopes.ActivityScope;
import temp.home.hometemp.mvp.base.Presenter;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MainActivityMvpModule.class,ActivityModule.class, LineChartModule.class, EntryModule.class, ChartModule.class})
public interface MainActivityComponent {
    Presenter getPresenter();
    MyChart getMyChart();
    Activity getActivity();
    LineChart getLineChart();
    Entry getEntry();
    List<Entry> getListEntry();

    void inject(MainActivity mainActivity);
}

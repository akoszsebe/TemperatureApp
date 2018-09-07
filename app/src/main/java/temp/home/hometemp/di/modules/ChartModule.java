package temp.home.hometemp.di.modules;

import android.app.Activity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import temp.home.hometemp.chart.MyChart;

@Module
public class ChartModule {
    Activity activity;
    LineChart chart;
    List<Entry> yVals;

    public ChartModule(Activity activity, LineChart chart, List<Entry> yVals) {
        this.activity = activity;
        this.chart = chart;
        this.yVals = yVals;
    }

    @Provides
    MyChart provideChart(Activity activity, LineChart chart, List<Entry> yVals){
        return new MyChart(activity, chart,yVals);
    }
}

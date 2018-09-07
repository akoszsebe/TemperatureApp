package temp.home.hometemp.di.modules;

import com.github.mikephil.charting.charts.LineChart;

import dagger.Module;
import dagger.Provides;

@Module
public class LineChartModule {
    private final LineChart mChart;

    public LineChartModule(LineChart mChart) {
        this.mChart = mChart;
    }

    @Provides
    public LineChart providemLineChart() {
        return mChart;
    }
}

package temp.home.hometemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.jackandphantom.circularprogressbar.CircleProgressbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import temp.home.hometemp.chart.MyChart;
import temp.home.hometemp.main.MainContract;
import temp.home.hometemp.main.MainPresenter;

public class MainActivity extends AppCompatActivity  implements MainContract.MvpView{
    @BindView(R.id.cyrcular) CircleProgressbar circleProgressbar;
    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.chart1) LineChart lineChart;

    private static final int ANIMATION_DURATION = 2500;
    private static final float PROGRESS_SHIFT = 1.66f;

    private MainPresenter mPresenter;
    private MyChart myChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);

        displayCircularProgressbarChange(0);
        displayTemperatureTextChange(0);

        myChart = new MyChart(this,lineChart);
        mPresenter.startScheduledExecutorService();
    }

    protected void onStop() {
        super.onStop();
        mPresenter.stopScheduledExecutorService();
    }

    protected void onPause() {
        super.onPause();
        mPresenter.stopScheduledExecutorService();
    }

    protected void onResume() {
        super.onResume();
        mPresenter.restartScheduledExecutorService();
    }

    @Override
    public void displayCircularProgressbarChange(float temperature) {
        this.circleProgressbar.setProgressWithAnimation(temperature * PROGRESS_SHIFT, ANIMATION_DURATION);
    }

    @Override
    public void displayTemperatureTextChange(float temperature) {
        this.temperature.setText(String.format("%s", temperature));
    }

    @Override
    public void displayChartChange(float temperature) {
        this.myChart.addData(temperature);
    }
}

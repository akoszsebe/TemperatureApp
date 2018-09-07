package temp.home.hometemp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.jackandphantom.circularprogressbar.CircleProgressbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import temp.home.hometemp.App;
import temp.home.hometemp.R;
import temp.home.hometemp.chart.MyChart;
import temp.home.hometemp.mvp.MainMvpView;
import temp.home.hometemp.mvp.MainPresenterImpl;
import temp.home.hometemp.mvp.base.MvpView;


public class MainActivity extends AppCompatActivity  implements MainMvpView {
    @BindView(R.id.cyrcular) CircleProgressbar circleProgressbar;
    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.chart1) LineChart lineChart;

    private static final int ANIMATION_DURATION = 2500;
    private static final float PROGRESS_SHIFT = 1.66f;

    @Inject
    MyChart myChart;

    @Inject
    MainPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App.get(this).getMainActivityComponent(this,this,lineChart)
        .inject(this);

        mPresenter.onAttach(this);

        displayCircularProgressbarChange(0);
        displayTemperatureTextChange(0);

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

    @Override
    public void showError(String error) {

    }
}

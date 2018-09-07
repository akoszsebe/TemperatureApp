package temp.home.hometemp.mvp;

import temp.home.hometemp.mvp.base.MvpView;

public interface MainMvpView extends MvpView {
    void displayCircularProgressbarChange(float temperature);
    void displayTemperatureTextChange(float temperature);
    void displayChartChange(float temperature);
}

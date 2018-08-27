package temp.home.hometemp.main;

public interface MainContract {
    interface MvpView{
        void displayCircularProgressbarChange(float temperature);
        void displayTemperatureTextChange(float temperature);
        void displayChartChange(float temperature);
    }

    interface  Presenter {
        void startScheduledExecutorService();
        void stopScheduledExecutorService();
        void restartScheduledExecutorService();
    }
}

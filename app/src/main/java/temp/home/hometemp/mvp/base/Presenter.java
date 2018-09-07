package temp.home.hometemp.mvp.base;


public interface  Presenter<V extends MvpView> {
    void onAttach(V baseView);
    void onDetach();

}

package temp.home.hometemp.mvp.base;

import javax.inject.Inject;

public class BasePresenter<T extends MvpView>  implements Presenter<T>{

    protected T baseView;

    @Inject
    public BasePresenter() {
    }

    @Override
    public void onAttach(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onDetach() {
        this.baseView = null;
    }


    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }
}

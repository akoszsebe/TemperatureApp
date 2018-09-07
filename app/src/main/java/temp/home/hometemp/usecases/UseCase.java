package temp.home.hometemp.usecases;

public interface UseCase<P, R> {

    interface Callback<R>{
        void onSuccess(R result);
        void onError(Throwable throwable);
    }

    void execute(P parameter, Callback<R> callback);
}

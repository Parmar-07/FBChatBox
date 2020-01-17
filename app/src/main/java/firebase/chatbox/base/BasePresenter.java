package firebase.chatbox.base;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {

    private BaseView baseView;
    private CompositeDisposable disposables;

    public BasePresenter(BaseView view) {
        this.baseView = view;
    }

    protected void loadPresenter() {
        disposables = new CompositeDisposable();
        disposables.dispose();
    }


    protected void addDisposeEvent(Disposable disposable) {
        disposables.add(disposable);
    }

    protected void unloadPresenter() {
        disposables.clear();
        disposables.dispose();
    }

    protected <THREAD_OWNER> Disposable observeNetworkThread(Observable<THREAD_OWNER> observer) {
        return observer
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        baseView.onPreNetworkCall();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2, new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) {
                        return (throwable instanceof SocketTimeoutException);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {

                        baseView.onPostNetworkCall();
                        baseView.popMessage(throwable.getMessage());

                    }
                }).subscribe();

    }


    protected <THREAD_OWNER> Disposable singleNetworkThread(Single<THREAD_OWNER> singleObserver) {
        return singleObserver
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        baseView.onPreNetworkCall();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2, new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) {
                        return (throwable instanceof SocketTimeoutException);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        baseView.onPostNetworkCall();
                        baseView.popMessage(throwable.getMessage());
                    }
                }).subscribe();

    }


}

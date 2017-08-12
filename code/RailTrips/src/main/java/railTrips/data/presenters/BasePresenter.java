package railTrips.data.presenters;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {

    protected WeakReference<V> mView;

    public BasePresenter() {
    }

    public void bindView(final V view) {
        mView = new WeakReference<V>(view);
    }

    public void unbindView() {
        mView.clear();
        mView = null;
    }

    public V getView() {
        if (mView != null) {
            return mView.get();
        } else {
            return null;
        }
    }

}

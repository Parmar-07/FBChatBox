package firebase.chatbox.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseFragmentActivity<presenter extends BasePresenter> extends BaseActivity<presenter> implements BaseView {

    private FragmentManager fragmentManager;

    @Override
    protected void initData() {
        fragmentManager = this.getSupportFragmentManager();
    }

   protected abstract int getFragmentContainerView();

    private void removePreviousIfExists() {

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }

    public void loadFragment(Fragment fragment) {
//        removePreviousIfExists();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(getFragmentContainerView(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}

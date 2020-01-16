package firebase.chatbox;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseFragmentActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    void initData() {
        fragmentManager = this.getSupportFragmentManager();
    }

    abstract int getFragmentContainerView();

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

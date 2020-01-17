package firebase.chatbox.features.credentials.signup;

import android.view.View;

import firebase.chatbox.R;
import firebase.chatbox.base.BaseFragment;
import firebase.chatbox.presenter.SignInPresenter;

public class SignUpFragment extends BaseFragment<SignInPresenter> {
    @Override
    protected int getLayoutView() {
        return R.layout.frag_signup;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void viewListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected SignInPresenter getPresenter() {
        return null;
    }
}
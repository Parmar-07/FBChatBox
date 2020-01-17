package firebase.chatbox.features.credentials;

import android.view.View;
import android.widget.TextView;

import firebase.chatbox.R;
import firebase.chatbox.base.BaseFragmentActivity;
import firebase.chatbox.features.credentials.signin.SignInFragment;
import firebase.chatbox.features.credentials.signup.SignUpFragment;
import firebase.chatbox.presenter.CredentialPresenter;
import firebase.chatbox.view.CredentialView;

public class CredentialSetup extends BaseFragmentActivity<CredentialPresenter> implements CredentialView {

    private TextView changeCredentials;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_credential_setup;
    }

    @Override
    protected void initView() {
        changeCredentials = findViewById(R.id.changeCredentials);
        getPresenter().defaultView();

    }

  /*  protected void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.credential_container, fragment, fragment.getClass().getSimpleName());
        transaction.commit();

    }*/

    @Override
    protected void viewListeners() {


        changeCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onViewChangeClick(v.getTag().toString());

            }
        });


    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected CredentialPresenter getPresenter() {
        return new CredentialPresenter(this);
    }

    @Override
    protected int getFragmentContainerView() {
        return R.id.credential_container;
    }



    @Override
    public void previewSignIn() {
        loadFragment(new SignInFragment());
        changeCredentials.setText(getString(R.string.newUSer));
        changeCredentials.setTag("SignIn");
    }

    @Override
    public void previewSignUp() {
        loadFragment(new SignUpFragment());
        changeCredentials.setText(getString(R.string.existingUser));
        changeCredentials.setTag("SignUp");

    }


}

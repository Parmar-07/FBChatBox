package firebase.chatbox;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CredentialSetup extends BaseFragmentActivity {

    private TextView changeCredentials;

    @Override
    int getLayoutView() {
        return R.layout.activity_credential_setup;
    }

    @Override
    void initView() {
        Fragment fragment = new SignInFragment();
        loadFragment(fragment);
        changeCredentials = findViewById(R.id.changeCredentials);
        changeCredentials.setTag("SignIn");
        changeCredentials.setText(getString(R.string.newUSer));

    }

  /*  protected void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.credential_container, fragment, fragment.getClass().getSimpleName());
        transaction.commit();

    }*/

    @Override
    void viewListeners() {


        changeCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getTag().equals("SignIn")) {
                    loadFragment(new SignUpFragment());
                    changeCredentials.setText(getString(R.string.existingUser));
                    changeCredentials.setTag("SignUp");
                } else {
                    loadFragment(new SignInFragment());
                    changeCredentials.setText(getString(R.string.newUSer));
                    changeCredentials.setTag("SignIn");
                }

            }
        });


    }

    @Override
    void initData() {
        super.initData();

    }

    @Override
    int getFragmentContainerView() {
        return R.id.credential_container;
    }

}

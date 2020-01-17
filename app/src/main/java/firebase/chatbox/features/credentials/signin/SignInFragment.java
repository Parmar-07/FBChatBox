package firebase.chatbox.features.credentials.signin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import firebase.chatbox.R;
import firebase.chatbox.base.BaseFragment;
import firebase.chatbox.presenter.SignInPresenter;
import firebase.chatbox.view.SignInView;

public class SignInFragment extends BaseFragment<SignInPresenter> implements SignInView {


    private EditText email;
    private EditText password;
    private Button btnSignIn;

    @Override
    protected int getLayoutView() {
        return R.layout.frag_signin;
    }

    @Override
    protected void initView(View view) {
        email = view.findViewById(R.id.userEmail);
        password = view.findViewById(R.id.userPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);

    }

    @Override
    protected void viewListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().doSignIn(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected SignInPresenter getPresenter() {
        return new SignInPresenter(this);
    }

    @Override
    public void onSuccessSignIn() {
        popError("DO FIRE-BASE CALL");
    }
}

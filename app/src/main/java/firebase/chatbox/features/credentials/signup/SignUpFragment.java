package firebase.chatbox.features.credentials.signup;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import firebase.chatbox.R;
import firebase.chatbox.base.BaseFragment;
import firebase.chatbox.presenter.SignUpPresenter;
import firebase.chatbox.view.SignUpView;

public class SignUpFragment extends BaseFragment<SignUpPresenter> implements SignUpView {
    private EditText username;
    private EditText email;
    private EditText password;
    private Button btnSignUp;

    @Override
    protected int getLayoutView() {
        return R.layout.frag_signup;
    }

    @Override
    protected void initView(View view) {
        username = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.userEmail);
        password = view.findViewById(R.id.userPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);
    }

    @Override
    protected void viewListeners() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().doSignUp(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected SignUpPresenter getPresenter() {
        return new SignUpPresenter(this);
    }

    @Override
    public void onSuccessSignUp() {
        popMessage("DO FIRE-BASE CALL");
    }
}
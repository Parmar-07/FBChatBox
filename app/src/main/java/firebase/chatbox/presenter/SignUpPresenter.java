package firebase.chatbox.presenter;

import firebase.chatbox.base.BasePresenter;
import firebase.chatbox.interactor.SignUpInteractor;
import firebase.chatbox.view.SignUpView;

public class SignUpPresenter extends BasePresenter implements SignUpInteractor {

    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        super(view);
        this.view = view;
    }

    @Override
    public boolean validate(String username, String email, String password) {
        view.dismissKeyboard();
        if (username.isEmpty()) {
            view.popMessage("Please Enter Username");
            return false;
        } else if (email.isEmpty()) {
            view.popMessage("Please Enter Email");
            return false;
        } else if (password.isEmpty()) {
            view.popMessage("Please Enter Password");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void doSignUp(String username, String email, String password) {
        if (validate(username, email, password))
            view.onSuccessSignUp();
    }
}

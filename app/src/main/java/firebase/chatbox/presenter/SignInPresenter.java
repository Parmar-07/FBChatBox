package firebase.chatbox.presenter;

import firebase.chatbox.base.BasePresenter;
import firebase.chatbox.interactor.SignInInteractor;
import firebase.chatbox.view.SignInView;

public class SignInPresenter extends BasePresenter implements SignInInteractor {

    private SignInView view;

    public SignInPresenter(SignInView view) {
        super(view);
        this.view = view;
    }


    @Override
    public boolean validate(String email, String password) {

        view.dismissKeyboard();

        if (email.isEmpty()) {
            view.popError("Please Enter Email");
            return false;
        } else if (password.isEmpty()) {
            view.popError("Please Enter Password");
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void doSignIn(String email, String password) {
        if (validate(email, password))
            view.onSuccessSignIn();
    }
}

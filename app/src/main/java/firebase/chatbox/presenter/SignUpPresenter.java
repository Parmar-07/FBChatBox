package firebase.chatbox.presenter;

import firebase.chatbox.base.BasePresenter;
import firebase.chatbox.data.ResponseListener;
import firebase.chatbox.data.repo.CredentialDataRepo;
import firebase.chatbox.interactor.SignUpInteractor;
import firebase.chatbox.view.SignUpView;

public class SignUpPresenter extends BasePresenter implements SignUpInteractor {

    private SignUpView view;
    private CredentialDataRepo credentialRepo;
    public SignUpPresenter(SignUpView view) {
        super(view);
        this.view = view;
        this.credentialRepo = new CredentialDataRepo();
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
        {
            view.onPreNetworkCall();
            credentialRepo.login(email, password, new ResponseListener<Boolean>() {
                @Override
                public void onSuccess(Boolean response) {
                    if (response)
                        view.onSuccessSignUp();
                    else
                        view.popMessage("Some thing went wrong");

                    view.onPostNetworkCall();
                }

                @Override
                public void onFailure(String error) {
                    view.popMessage(error);
                    view.onPostNetworkCall();
                }
            });
        }
    }
}

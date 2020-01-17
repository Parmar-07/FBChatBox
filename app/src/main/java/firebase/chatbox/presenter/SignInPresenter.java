package firebase.chatbox.presenter;

import firebase.chatbox.base.BasePresenter;
import firebase.chatbox.base.BaseRepo;
import firebase.chatbox.data.DataRepo;
import firebase.chatbox.data.ResponseListener;
import firebase.chatbox.data.repo.CredentialDataRepo;
import firebase.chatbox.interactor.SignInInteractor;
import firebase.chatbox.view.SignInView;

public class SignInPresenter extends BasePresenter implements SignInInteractor {

    private SignInView view;
    private CredentialDataRepo credentialRepo;


    public SignInPresenter(SignInView view) {
        super(view);
        this.view = view;
        this.credentialRepo = new CredentialDataRepo();
    }


    @Override
    public boolean validate(String email, String password) {

        view.dismissKeyboard();

        if (email.isEmpty()) {
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
    public void doSignIn(String email, String password) {
        if (validate(email, password))
        {
            view.onPreNetworkCall();
            credentialRepo.login(email, password, new ResponseListener<Boolean>() {
                @Override
                public void onSuccess(Boolean response) {
                    if (response)
                        view.onSuccessSignIn();
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

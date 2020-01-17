package firebase.chatbox.interactor;

import firebase.chatbox.base.BaseInteractor;

public interface SignInInteractor extends BaseInteractor {

    boolean validate(String email, String password);

    void doSignIn(String email, String password);

}

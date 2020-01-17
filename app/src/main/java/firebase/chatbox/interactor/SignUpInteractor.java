package firebase.chatbox.interactor;

import firebase.chatbox.base.BaseInteractor;

public interface SignUpInteractor extends BaseInteractor {

    boolean validate(String username,String email, String password);
    void doSignUp(String username,String email, String password);

}

package firebase.chatbox.presenter;

import firebase.chatbox.base.BasePresenter;
import firebase.chatbox.interactor.CredentailInteractor;
import firebase.chatbox.view.CredentialView;

public class CredentialPresenter extends BasePresenter implements CredentailInteractor {

    private CredentialView view;

    public CredentialPresenter(CredentialView view) {
        super(view);
        this.view = view;
    }



    @Override
    public void defaultView() {
        view.previewSignIn();
    }

    @Override
    public void onViewChangeClick(String tag) {
        if (tag.equals("SignIn")) {
           view.previewSignUp();
        } else {
            view.previewSignIn();
        }

//        if (v.getTag().equals("SignIn")) {
//            loadFragment(new SignUpFragment());
//            changeCredentials.setText(getString(R.string.existingUser));
//            changeCredentials.setTag("SignUp");
//        } else {
//            loadFragment(new SignInFragment());
//            changeCredentials.setText(getString(R.string.newUSer));
//            changeCredentials.setTag("SignIn");
//        }


    }
}

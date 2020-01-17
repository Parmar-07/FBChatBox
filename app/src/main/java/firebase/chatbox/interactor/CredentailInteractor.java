package firebase.chatbox.interactor;

import firebase.chatbox.base.BaseInteractor;

public interface CredentailInteractor extends BaseInteractor {

    void defaultView();
    void onViewChangeClick(String tag);

}

package firebase.chatbox.base;

import firebase.chatbox.FBChatBox;
import firebase.chatbox.data.local.AppCache;
import firebase.chatbox.data.network.FireBaseConnector;

public abstract class BaseRepo {
    private AppCache appCache;
    private FireBaseConnector fireBase;


    public BaseRepo() {
        this.appCache = FBChatBox.getAppInstance().getAppCache();
        this.fireBase = FBChatBox.getAppInstance().getFireBaseConnector();
    }


    protected AppCache getAppCache() {
        return appCache;
    }

    protected FireBaseConnector getFireBase() {
        return fireBase;
    }
}

package firebase.chatbox;

import androidx.multidex.MultiDexApplication;

import firebase.chatbox.data.local.AppCache;
import firebase.chatbox.data.network.FireBaseConnector;

public class FBChatBox extends MultiDexApplication {

    private static FBChatBox appInstance = null;
    private AppCache appCache;
    private FireBaseConnector fireBaseConnector;

    public static FBChatBox getAppInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;
        appCache = AppCache.getAppCache(appInstance);
        fireBaseConnector = FireBaseConnector.getFireBaseConnector();

    }


    public AppCache getAppCache() {
        return appCache;
    }
    public FireBaseConnector getFireBaseConnector() {
        return fireBaseConnector;
    }
}

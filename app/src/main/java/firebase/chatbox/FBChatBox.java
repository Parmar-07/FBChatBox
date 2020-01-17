package firebase.chatbox;

import androidx.multidex.MultiDexApplication;

import firebase.chatbox.data.local.AppCache;

public class FBChatBox extends MultiDexApplication {

    private static FBChatBox appInstance=null;

    public static FBChatBox getAppInstance(){
     return appInstance;
    }

    private AppCache appCache;

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;
        appCache = AppCache.getAppCache(appInstance);


    }


    public AppCache getAppCache() {
        return appCache;
    }
}

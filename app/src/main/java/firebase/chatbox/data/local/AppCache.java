package firebase.chatbox.data.local;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppCache implements IAppCache {



    private static AppCache appCache = null;
    private SharedPreferences prefs;

    private AppCache(Application app) {
        prefs = app.getApplicationContext().getSharedPreferences("AppCache", Context.MODE_PRIVATE);
    }

    public static AppCache getAppCache(Application app) {
        if (appCache == null) {
            appCache = new AppCache(app);
        }
        return appCache;
    }


    @Override
    public void cacheString(String cacheKey, String cacheValue) {
        prefs.edit().putString(cacheKey, cacheValue).apply();
    }

    @Override
    public String getStringCache(String cacheKey) {
        return prefs.getString(cacheKey, "");
    }

    @Override
    public void clearCache(String cacheKey) {
        prefs.edit().remove(cacheKey).apply();

    }

    @Override
    public void clearCache() {
        prefs.edit().clear().apply();
    }
}

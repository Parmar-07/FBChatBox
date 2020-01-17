package firebase.chatbox.data.local;

public interface IAppCache {

    void cacheString(String cacheKey,String cacheValue);
    String getStringCache(String cacheKey);

    void clearCache(String cacheKey);
    void clearCache();

}

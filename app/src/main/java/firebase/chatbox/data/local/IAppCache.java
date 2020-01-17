package firebase.chatbox.data.local;

public interface IAppCache {

    String USER_NAME = "user_name";
    String USER_ID = "user_id";
    String USER_EMAIL = "user_email";
    String USER_PROVIDER_ID = "user_provider_id";
    String USER_PHOTO_URI = "user_photo_uri";


    void cacheString(String cacheKey,String cacheValue);
    String getStringCache(String cacheKey);

    void clearCache(String cacheKey);
    void clearCache();

}

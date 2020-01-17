package firebase.chatbox.data;

public interface DataRepo {

    interface CredentialRepo{
        void login(String email,String password,ResponseListener<Boolean> responseListener);
        void register(String username,String email,String password,ResponseListener<Boolean> responseListener);
    }


}

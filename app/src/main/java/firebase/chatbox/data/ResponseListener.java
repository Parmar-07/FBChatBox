package firebase.chatbox.data;

public interface ResponseListener<T> {

    void onSuccess(T response);
    void onFailure(String error);



}

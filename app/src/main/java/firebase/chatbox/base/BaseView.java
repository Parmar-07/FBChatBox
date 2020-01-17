package firebase.chatbox.base;

public interface BaseView {

    void popError(String error);
    void dismissKeyboard();
    void onPreNetworkCall();
    void onPostNetworkCall();


}

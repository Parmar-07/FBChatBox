package firebase.chatbox.base;

public interface BaseView {

    void popMessage(String error);
    void dismissKeyboard();
    void onPreNetworkCall();
    void onPostNetworkCall();


}

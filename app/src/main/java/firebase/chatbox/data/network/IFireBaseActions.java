package firebase.chatbox.data.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface IFireBaseActions {

    String TBL_CHAT_BOX_USER = "tbl_chat_box_user";

    Task<AuthResult> login(String email, String password);
    Task<AuthResult> register(String email, String password);

}

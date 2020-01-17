package firebase.chatbox.data.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseConnector implements IFireBaseActions {

    private static FireBaseConnector fireBaseConnector = null;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference fireDatabase;


    private FireBaseConnector() {
        firebaseAuth = FirebaseAuth.getInstance();
        fireDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public static FireBaseConnector getFireBaseConnector(){
        if (fireBaseConnector == null){
            fireBaseConnector = new FireBaseConnector();
        }
        return fireBaseConnector;
    }

    @Override
    public Task<AuthResult> login(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> register(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public DatabaseReference getDatabase() {
        return fireDatabase;
    }
}

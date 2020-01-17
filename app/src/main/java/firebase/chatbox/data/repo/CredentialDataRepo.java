package firebase.chatbox.data.repo;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import firebase.chatbox.base.BaseRepo;
import firebase.chatbox.data.DataRepo;
import firebase.chatbox.data.ResponseListener;
import firebase.chatbox.data.local.AppCache;
import firebase.chatbox.data.network.FireBaseConnector;

public class CredentialDataRepo extends BaseRepo implements DataRepo.CredentialRepo {


    private void cacheUser(FirebaseUser user) {
        if (user != null) {
            getAppCache().cacheString(AppCache.USER_NAME, user.getDisplayName());
            getAppCache().cacheString(AppCache.USER_EMAIL, user.getEmail());
            getAppCache().cacheString(AppCache.USER_ID, user.getUid());
            getAppCache().cacheString(AppCache.USER_PROVIDER_ID, user.getProviderId());
            Uri photoUri = user.getPhotoUrl();
            if (photoUri != null) {
                getAppCache().cacheString(AppCache.USER_PHOTO_URI, photoUri.toString());
            }
        }
    }

    @Override
    public void login(String email, String password, final ResponseListener<Boolean> responseListener) {

        getFireBase().login(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        boolean isSuccess = task.isSuccessful();
                        if (isSuccess) {
                            AuthResult result = task.getResult();
                            if (result != null) {
                                cacheUser(result.getUser());
                            }
                        }
                        responseListener.onSuccess(isSuccess);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        responseListener.onFailure(e.getMessage());
                    }
                });

    }

    @Override
    public void register(String username, String email, String password, final ResponseListener<Boolean> responseListener) {
        getFireBase().register(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        boolean isSuccess = task.isSuccessful();
                        if (isSuccess) {
                            AuthResult result = task.getResult();

                            if (result != null) {
                                final FirebaseUser user = result.getUser();
                                if (user != null) {

                                    HashMap<String, String> userValues = new HashMap<>();
                                    userValues.put(AppCache.USER_NAME, user.getDisplayName());
                                    userValues.put(AppCache.USER_EMAIL, user.getEmail());
                                    userValues.put(AppCache.USER_ID, user.getUid());
                                    Uri photoUri = user.getPhotoUrl();
                                    if (photoUri != null) {
                                        userValues.put(AppCache.USER_PHOTO_URI, photoUri.toString());
                                    }

                                    getFireBase().getDatabase().child(FireBaseConnector.TBL_CHAT_BOX_USER)
                                            .child(user.getUid())
                                            .setValue(userValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            boolean isSuccess = task.isSuccessful();
                                            if (isSuccess) {
                                                cacheUser(user);
                                            }
                                            responseListener.onSuccess(isSuccess);
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    responseListener.onFailure(e.getMessage());
                                                }
                                            });

                                }
                            }


                        }
                        responseListener.onSuccess(isSuccess);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        responseListener.onFailure(e.getMessage());
                    }
                });
    }
}

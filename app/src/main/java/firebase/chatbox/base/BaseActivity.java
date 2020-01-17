package firebase.chatbox.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import firebase.chatbox.FBChatBox;

public abstract class BaseActivity<presenter extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected abstract int getLayoutView();
    protected abstract void initView();
    protected abstract void viewListeners();
    protected abstract void initData();
    protected abstract presenter getPresenter();
    private ProgressDialog progressDialog=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());

        initData();
        initView();
        viewListeners();

        getPresenter().loadPresenter();

    }


    @Override
    protected void onDestroy() {
        getPresenter().unloadPresenter();
        super.onDestroy();
    }


    @Override
    public void popError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreNetworkCall() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
    }

    @Override
    public void onPostNetworkCall() {
        progressDialog.dismiss();
    }

    @Override
    public void dismissKeyboard() {
        Context context = FBChatBox.getAppInstance().getApplicationContext();
        View view = this.getCurrentFocus();
        if (view!=null){
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}

package firebase.chatbox.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import firebase.chatbox.FBChatBox;

public abstract class BaseFragment<presenter extends BasePresenter> extends Fragment implements BaseView {

    private ProgressDialog progressDialog = null;

    protected abstract int getLayoutView();

    protected abstract void initView(View view);

    protected abstract void viewListeners();

    protected abstract void initData();

    protected abstract presenter getPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutView(), container, false);
        if (view != null) {
            initView(view);
            viewListeners();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        if (getPresenter() != null)
            getPresenter().loadPresenter();
    }

    @Override
    public void onDestroy() {
        if (getPresenter() != null)
            getPresenter().unloadPresenter();
        super.onDestroy();
    }

    @Override
    public void popMessage(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreNetworkCall() {
        progressDialog = new ProgressDialog(getActivity());
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
        View view = this.getView();
        if (view!=null){
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }


    }
}

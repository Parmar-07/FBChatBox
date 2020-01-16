package firebase.chatbox;

import android.util.Log;
import android.view.View;

public class SignInFragment extends BaseFragment {
    public SignInFragment(){
        Log.d("BaseFragment",this.getClass().getSimpleName());
    }

    @Override
    int getLayoutView() {
        return R.layout.frag_signin;
    }

    @Override
    void initView(View view) {

    }

    @Override
    void viewListeners() {

    }

    @Override
    void initData() {

    }
}

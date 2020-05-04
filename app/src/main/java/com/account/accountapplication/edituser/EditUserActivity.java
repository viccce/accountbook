package com.account.accountapplication.edituser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.my.MyFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity implements EditUserContract.EditUserView {

    private EditUserContract.EditUserPresenter editUserPresenter;

    EditText username;
    EditText mobile;
    EditText email;
    EditText remark;
    Button saveButton;

    User user;

    private static EditUserActivity editUserActivity;

    public EditUserActivity(){
        editUserActivity = this;
    }

    public static EditUserActivity getEditUserActivity(){
        return editUserActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editUserPresenter = new EditUserPresenterImpl(this);

        username = (EditText) findViewById(R.id.edit_user_nickname);
        mobile = (EditText) findViewById(R.id.edit_user_mobile);
        email = (EditText) findViewById(R.id.edit_user_email);
        remark = (EditText) findViewById(R.id.edit_user_remark);

        saveButton = (Button) findViewById(R.id.edit_user_save_button);

        editUserPresenter.getUserInfo();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameText = username.getText().toString();
                final String mobileText = mobile.getText().toString();
                final String emailText = email.getText().toString();
                final String remarkText = remark.getText().toString();
                editUserPresenter.saveRemoteUserInfo(user.getId(), usernameText, mobileText, emailText, remarkText);
            }
        });
    }

    @Override
    public void textEmpty(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void setUserInfo(User user) {
        this.user = user;
    }

    @Override
    public void updateSuccess(User user) {
        Log.e("WangJ", "123");
        MyFragment myFragment = MyFragment.getInstance();
        if(myFragment != null){
            myFragment.displayUserInfo(user);
        }
        finish();
    }
}

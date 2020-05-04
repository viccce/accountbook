package com.account.accountapplication.edituser;

import com.account.accountapplication.data.edituser.EditUserModel;
import com.account.accountapplication.data.edituser.EditUserModelImpl;
import com.account.accountapplication.data.my.User;

public class EditUserPresenterImpl implements EditUserContract.EditUserPresenter, EditUserModel.FinishListener {

    private EditUserContract.EditUserView editUserView;
    private EditUserModel editUserModel;

    public EditUserPresenterImpl(EditUserContract.EditUserView editUserView) {
        this.editUserView = editUserView;
        this.editUserModel = new EditUserModelImpl();
    }

    @Override
    public void saveRemoteUserInfo(Long id, String nickname, String mobile, String email, String remark) {
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setRemark(remark);

        editUserModel.saveRemoteUserInfo(user, this);
    }

    @Override
    public void getUserInfo() {
        editUserModel.getUserInfo(this);
    }

    @Override
    public void onError(String message) {
        editUserView.textEmpty(message);
    }

    @Override
    public void remoteSaveSuccess(User user) {
        editUserModel.updateUserInfo(user, this);
    }

    @Override
    public void getUserInfoSuccess(User user) {
        editUserView.setUserInfo(user);
    }

    @Override
    public void updateUserInfoSuccess(User user) {
        editUserView.updateSuccess(user);
    }
}

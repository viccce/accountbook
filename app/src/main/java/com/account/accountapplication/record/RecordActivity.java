package com.account.accountapplication.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.account.accountapplication.R;
import com.account.accountapplication.chat.chatFragment;
import com.account.accountapplication.edituser.EditUserActivity;
import com.account.accountapplication.login.LoginActivity;
import com.account.accountapplication.message.messageFragment;
import com.account.accountapplication.my.MyFragment;
import com.account.accountapplication.record.addRecord.addRecordFragment;
import com.next.easynavigation.view.EasyNavigationBar;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    FragmentManager fragmentManager;

    private String[] tabText = {"聊天", "账目", "", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = { R.mipmap.message,R.mipmap.me,R.mipmap.message,R.mipmap.message,R.mipmap.message};
    //选中时icon
    private int[] selectIcon = { R.mipmap.message,R.mipmap.me,R.mipmap.message,R.mipmap.message,R.mipmap.me};

    private List<Fragment> fragments = new ArrayList<>();
    private Handler mHandler = new Handler();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new chatFragment());
        fragments.add(new RecordFragment());
        fragments.add(new addRecordFragment());
        fragments.add(new messageFragment());
        fragments.add(new MyFragment());
        //      .fragmentList(fragments)

//         fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(new chatFragment(),"chat");
//        transaction.add(new RecordFragment(),"record");
//        transaction.add(new addRecordFragment(),"addRecord");
//        transaction.add(new messageFragment(),"message");
//        transaction.add(new myFragment(),"my");
//        transaction.commit();


        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .anim(null)
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .addLayoutBottom(0)
                .addAlignBottom(true)
                .addAsFragment(true)
                .fragmentManager(getSupportFragmentManager())
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        Log.e("onTabClickEvent", position + "");
                        if (position == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //＋ 旋转动画
                                    if (flag) {
                                        navigationBar.getAddImage().animate().rotation(45).setDuration(400);
                                    } else {
                                        navigationBar.getAddImage().animate().rotation(0).setDuration(400);
                                    }
                                    flag = !flag;
                                }
                            });
                        }
                        return false;
                    }
                })
                .canScroll(true)
                .mode(EasyNavigationBar.MODE_ADD)
                .build();

    }

    public void logout(){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        this.startActivity(intent);
        finish();
    }

    public void editUserInfo() {
        Intent intent = new Intent();
        intent.setClass(this, EditUserActivity.class);
        this.startActivity(intent);
    }
}

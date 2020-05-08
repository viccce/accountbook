package com.account.accountapplication.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.addaccount.AddAccountActivity;
import com.account.accountapplication.data.record.Account;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordFragment extends Fragment implements RecordContract.RecordView {

    private RecordContract.RecordPresenter recordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        Context context = view.getContext();

        ImageButton addButton = view.findViewById(R.id.account_add);

        recordPresenter = new RecordPresenterImpl(this);
        this.getAccountInfoList();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddAccountActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    //获取账表信息并渲染页面
    void getAccountInfoList(){
        recordPresenter.getAccountInfoList();
    }

    @Override
    public void showAccountInfoList(List<Account> list) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.account_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AccountAdapter adapter = new AccountAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    public void showAccountOptions(final Account account){

        new MaterialDialog.Builder(getContext())
                .title(R.string.tip_options)
                .items(R.array.menu_values)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                        if(position == 0){//详情
                            Intent intent = new Intent(getActivity(), AccountActivity.class);
                            intent.putExtra("accountId", account.getAccountId());
                            startActivity(intent);
                        }
                        if(position == 1){//删除
                            new MaterialDialog.Builder(getContext())
                                    .content(R.string.confirm_delete)
                                    .positiveText(R.string.yes)
                                    .negativeText(R.string.no)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            recordPresenter.deleteAccount(account);
                                        }
                                    })
                                    .show();
                        }
                    }
                })
                .show();

    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
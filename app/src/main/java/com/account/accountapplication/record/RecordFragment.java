package com.account.accountapplication.record;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.data.record.Account;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordFragment extends Fragment implements RecordContract.RecordView {

    private RecordContract.RecordPresenter recordPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        Context context = view.getContext();

        recordPresenter = new RecordPresenterImpl(this);
        this.getAccountInfoList();

        return view;
    }


    //获取账表信息并渲染页面
    public void getAccountInfoList(){
        recordPresenter.getAccountInfoList();
    }

    @Override
    public void showAccountInfoList(List<Account> list) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.account_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AccountAdapter adapter = new AccountAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
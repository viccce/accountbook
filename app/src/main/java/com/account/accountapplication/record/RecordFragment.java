package com.account.accountapplication.record;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.account.accountapplication.R;
import com.account.accountapplication.data.record.dao.InnerJoinResult;
import com.account.accountapplication.data.record.model.RecordModel;
import com.account.accountapplication.data.record.record;
import com.account.accountapplication.databinding.FragmentRecordBinding;

import java.util.List;

public class RecordFragment extends Fragment {


    private RecordModel model;
    private recordAdapter myAdapteritem, myAdapternormal;
    //DatabindingBinding 由框架编译时生成，负责通知界面同步更新(命名方式：xml文件名 + Binding)；
    private FragmentRecordBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        Context context = view.getContext();
        //set recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //DataBindingUtil 将布局文件与Activity关联,生成DatabindingBinding实例binding；
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_record,container,false);

        //获取ViewModel实例
        model = new ViewModelProvider(this, new SavedStateViewModelFactory(this.getActivity().getApplication(), this)).get(RecordModel.class);

//为布局文件设置源数据
        binding.setData(model);
        myAdapternormal = new recordAdapter(false);
        myAdapteritem = new recordAdapter(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recyclerview.setAdapter(myAdapternormal);

//        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    binding.recyclerview.setAdapter(myAdapteritem);
//                } else {
//                    binding.recyclerview.setAdapter(myAdapternormal);
//                }
//            }
//        });

//为实现LiveData的数据设置观察者，以便当数据改变时通知UI更新数据
        model.getAllRecordsLive().observe(this.getViewLifecycleOwner(), new Observer<List<record>>() {
            @Override
            public void onChanged(List<record> records) {
                myAdapteritem.setAllRecords(records);
                myAdapteritem.notifyDataSetChanged();
                myAdapternormal.setAllRecords(records);
                myAdapternormal.notifyDataSetChanged();
            }
        });

        model.findRecType().observe(this.getViewLifecycleOwner(), new Observer<List<InnerJoinResult>>() {
            @Override
            public void onChanged(List<InnerJoinResult> recs) {
                myAdapteritem.serAllTypes(recs);
                myAdapteritem.notifyDataSetChanged();
                myAdapternormal.serAllTypes(recs);
                myAdapternormal.notifyDataSetChanged();
            }
        });



        return view;
    }



}


////拿到数据库操作对象
//        dao = recordDatabase.getInstance(view.getContext()).getRecordDao();
//
//                recyclerView=findViewById(R.id.recycler_view);
//
//                // 创建一个LinearLayoutManager对象，并把它设置到RecyclerView当中
//                // LayoutManager用于指定RecyclerView的布局方式，这里是线性布局的意思
//                LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
//                recyclerView.setLayoutManager(layoutManager);
//
//                // 创建FruitAdapter的实例，并将水果数据传入到FruitAdapter的构造函数中
//                recordAdapter adapter=new recordAdapter(recordsList);
//                recyclerView.setAdapter(adapter);
//                return view;
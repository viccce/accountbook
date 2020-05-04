package com.account.accountapplication.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.account.accountapplication.R;
import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.dao.InnerJoinResult;
import com.account.accountapplication.data.record.record;
import com.account.accountapplication.databinding.CellNormalBinding;
import com.account.accountapplication.databinding.RecordItemBinding;

import java.util.ArrayList;
import java.util.List;

public class recordAdapter extends RecyclerView.Adapter<recordAdapter.MyViewHolder>{
    private List<record> mRecordList= new ArrayList<>();
    private List<account> mAccountList= new ArrayList<>();
    private List<InnerJoinResult> mRecTypeNameList= new ArrayList<>();
    private LiveData<List<record>> allRecords;

    private boolean useCardView;
    private CellNormalBinding normalBinding;
    private RecordItemBinding itemBinding;

    //返回所有的数据
    void setAllRecords(List<record> Records) {
        this.mRecordList = Records;

    }

    void serAllTypes(List<InnerJoinResult> mRecTypeNames){
        this.mRecTypeNameList=mRecTypeNames;
    }

    //初始化布局标志位
    public recordAdapter(boolean useCardView) {
        this.useCardView = useCardView;
    }

    //当适配器创建的时候调用
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //RecyclerView中调用Databinding，绑定布局，获取binding实例
        //根据标识位初始化获取不同的binding
        if (useCardView) {
            itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.record_item, parent, false);
            //把binding作为参数，返回一个自定义的MyViewHolder
            return new MyViewHolder(itemBinding);
        } else {
            normalBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cell_normal, parent, false);
            return new MyViewHolder(normalBinding);
        }
    }


    //当调用ViewHolder时响应
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        //获取当前位置的一行数据
        record rec = mRecordList.get(position);
        String recType=mRecTypeNameList.get(position).getRecType_name();
        //设置数据
        holder.money.setText(String.valueOf(position + 1)); //让页面显示从1开始，而不用显示word.getId数据库中实际的位置
        holder.typeName.setText(rec.getRecordName());
        holder.typeInfo.setText(recType+"  "+rec.getRecordCreateDate());
        holder.typeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textEnglish.getText());
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(uri);
//                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //返回一个数据的容量
        return mRecordList.size();
    }

    //把onCreateViewHolder方法中的binding存起来，可以下次再用。这样做的好处就是不必每次都到布局文件中去拿到你的View，提高了效率。
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private CellNormalBinding normalBinding;
        private RecordItemBinding itemBinding;
        private TextView money, typeName, typeInfo;
        //两种参数不同的构造函数初始化不同的布局控件
        MyViewHolder(CellNormalBinding binding) {
            super(binding.getRoot());
            normalBinding = binding;
            money = normalBinding.money;
            typeName = normalBinding.typename;
            typeInfo = normalBinding.typeinfo;
        }

        MyViewHolder(RecordItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
            money = itemBinding.money;
            typeName = itemBinding.typename;
            typeInfo = itemBinding.typeinfo;
        }

    }

}

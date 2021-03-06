package com.account.accountapplication.account;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.account.accountapplication.R;
import com.account.accountapplication.data.record.AccountLine;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountLineAdapter extends RecyclerView.Adapter<AccountLineAdapter.ViewHolder> {

    private List<AccountLine> accountLineList;
    private AccountActivity accountActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView accountType;
        TextView changeMoney;
        TextView changeTime;
        TextView changeUserName;
        TextView payType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountType = itemView.findViewById(R.id.account_type_name);
            changeMoney = itemView.findViewById(R.id.change_money);
            changeTime = itemView.findViewById(R.id.change_time);
            changeUserName = itemView.findViewById(R.id.change_user_name);
            payType = itemView.findViewById(R.id.pay_type);
        }
    }

    public AccountLineAdapter(List<AccountLine> accountLineList, AccountActivity accountActivity) {
        this.accountLineList = accountLineList;
        this.accountActivity = accountActivity;
    }

    @NonNull
    @Override
    public AccountLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_line_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountLineAdapter.ViewHolder holder, int position) {
        AccountLine line = accountLineList.get(position);
        holder.accountType.setText(line.getAccountType());
        String money = new BigDecimal(line.getChangeMoney())
                .divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_DOWN).toString() + "元";
        holder.changeMoney.setText(money);
        holder.changeUserName.setText(line.getChangeUserName());
        holder.payType.setText(line.getPayType());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        String date = sdf.format(line.getChangeTime());
        holder.changeTime.setText(date);
    }

    @Override
    public int getItemCount() {
        return accountLineList.size();
    }
}

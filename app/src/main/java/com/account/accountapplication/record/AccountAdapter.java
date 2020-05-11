package com.account.accountapplication.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.account.accountapplication.R;
import com.account.accountapplication.data.record.Account;

import java.math.BigDecimal;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private List<Account> accountList;

    private RecordFragment recordFragment;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView accountName;
        TextView accountBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.account_name);
            accountBalance = itemView.findViewById(R.id.account_balance);
        }
    }

    public AccountAdapter(List<Account> list, RecordFragment recordFragment){
        this.accountList = list;
        this.recordFragment = recordFragment;
    }

    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Account account = accountList.get(position);
                recordFragment.showAccountOptions(account);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.accountName.setText(account.getAccountName());
        BigDecimal balance = new BigDecimal(account.getBalance());
        balance = balance.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_DOWN);
        String balanceText = "余额:" + balance.toString() + "元";
        holder.accountBalance.setText(balanceText);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}

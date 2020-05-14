package com.account.accountapplication.accountuser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.account.accountapplication.R;
import com.account.accountapplication.data.my.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountUserAdapter extends RecyclerView.Adapter<AccountUserAdapter.ViewHolder> {

    AccountUserActivity accountUserActivity;
    List<User> accountUserList;

    public AccountUserAdapter(List<User> accountUserList, AccountUserActivity accountUserActivity) {
        this.accountUserList = accountUserList;
        this.accountUserActivity = accountUserActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_user_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                User user = accountUserList.get(position);
                accountUserActivity.deleteAccountUser(user);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = accountUserList.get(position);
        holder.accountUserName.setText(user.getNickname());
    }

    @Override
    public int getItemCount() {
        return accountUserList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView accountUserName;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountUserName = itemView.findViewById(R.id.account_user_name);
            deleteButton = itemView.findViewById(R.id.account_user_delete);
        }
    }
}

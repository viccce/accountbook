package com.account.accountapplication.data.record;

import java.util.Date;

public class AccountLine {

    private Long accountLineId;
    private Long accountId;
    private String accountType;
    private long changeMoney;
    private Date changeTime;
    private Long changeUserId;
    private String payType;

    public Long getAccountLineId() {
        return accountLineId;
    }

    public void setAccountLineId(Long accountLineId) {
        this.accountLineId = accountLineId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(long changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Long getChangeUserId() {
        return changeUserId;
    }

    public void setChangeUserId(Long changeUserId) {
        this.changeUserId = changeUserId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}

package com.account.accountapplication.data.record.dataBase;

import androidx.room.TypeConverter;


import com.account.accountapplication.data.record.account;
import com.account.accountapplication.data.record.recType;

import java.util.Date;


public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

//    @TypeConverter
//    public static Integer accountToId(account account) {
//        return account==null ? null : account.getAccountId();
//    }
//
//    @TypeConverter
//    public static account IdToAccount(Integer accountId) {
//        return accountId==null ? null:aDataSource.getAccount(accountId);
//    }
//
//    @TypeConverter
//    public static Integer recTypeToId(recType recType) {
//        return recType==null ? null : recType.getRecTypeId();
//    }
//
//    @TypeConverter
//    public static recType IdToRecTypr(Integer recTypeId) {
//        return recTypeId==null ? null:rDataSource.getRecType(recTypeId);
//    }




}

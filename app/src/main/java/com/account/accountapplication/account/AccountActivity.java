package com.account.accountapplication.account;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.account.accountapplication.R;
import com.account.accountapplication.addaccountline.AddAccountLineActivity;
import com.account.accountapplication.data.record.AccountLine;
import com.account.accountapplication.record.RecordActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AccountActivity extends AppCompatActivity implements AccountContract.AccountView {

    public static AccountActivity accountActivity;

    public AccountActivity() {
        accountActivity = this;
    }

    public static AccountActivity getInstance() {
        return accountActivity;
    }

    private AccountContract.AccountPresenter accountPresenter;

    private RecyclerView recyclerView;
    private RelativeLayout accountSelect;
    private PieChart accountChart;
    private List<String> accountTypeList;
    private List<List<String>> accountTypeDetailList;

    @Override
    protected void onDestroy() {
        RecordActivity.getInstance().refreshRecord();
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        accountPresenter = new AccountPresenterImpl(this);

        Intent i = getIntent();
        Long accountId = null;
        accountId = i.getLongExtra("accountId", -1L);
        final String accountName = i.getStringExtra("accountName");

        if (accountId == -1L) {
            finish();
        } else {
            recyclerView = findViewById(R.id.account_line_recycler_view);
            accountSelect = findViewById(R.id.account_select);
            accountChart = findViewById(R.id.account_line_chart);
            String[] pickTypeList = getResources().getStringArray(R.array.account_type_values);
            accountTypeList = new ArrayList<>();
            accountTypeList.add("全部");
            List<String> all = new ArrayList<>();
            all.add("全部");
            accountTypeDetailList = new ArrayList<>();
            accountTypeDetailList.add(all);
            for (int index = 0; index < pickTypeList.length; index++) {
                accountTypeList.add(pickTypeList[index]);
                if ("收入".equals(pickTypeList[index])) {
                    List<String> detail = new ArrayList<>();
                    detail.add("全部");
                    detail.addAll(Arrays.asList(getResources().getStringArray(R.array.account_type_in_values)));
                    accountTypeDetailList.add(detail);
                } else if ("支出".equals(pickTypeList[index])) {
                    List<String> detail = new ArrayList<>();
                    detail.add("全部");
                    detail.addAll(Arrays.asList(getResources().getStringArray(R.array.account_type_out_values)));
                    accountTypeDetailList.add(detail);
                }
            }
            final TextView accountType = findViewById(R.id.account_in_detail);
            final Long finalAccountId = accountId;
            accountSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OptionsPickerView pvOptions = new OptionsPickerBuilder(v.getContext(), new OnOptionsSelectListener() {
                        @Override
                        public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                            String text = accountTypeList.get(options1) + "-" + accountTypeDetailList.get(options1).get(options2);
                            accountType.setText(text);
                            if (options1 == 0) {
                                getAccountLine(finalAccountId, "");
                            } else {
                                String accountType = text.replace("全部", "");
                                getAccountLine(finalAccountId, accountType);
                            }
                            return false;
                        }
                    })
                            .setTitleText(getString(R.string.account_type))
//                        .setSelectOptions(sexSelectOption)
                            .build();
                    pvOptions.setPicker(accountTypeList, accountTypeDetailList);
                    pvOptions.show();
                }
            });
            TitleBar titleBar = findViewById(R.id.account_line_title);
            titleBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            FloatingActionButton addButton = findViewById(R.id.add_account_line);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("accountId", finalAccountId);
                    intent.putExtra("accountName", accountName);
                    intent.setClass(AccountActivity.this, AddAccountLineActivity.class);
                    startActivity(intent);
                }
            });
            this.getAccountLine(accountId, null);
        }
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    void charInit() {
        //使用百分百显示
        accountChart.setUsePercentValues(true);
        accountChart.getDescription().setEnabled(false);
        accountChart.setExtraOffsets(5, 10, 5, 5);

        //设置拖拽的阻尼，0为立即停止
        accountChart.setDragDecelerationFrictionCoef(0.95f);

        //设置图标中心文字
        accountChart.setCenterText(generateCenterSpannableText());
        accountChart.setDrawCenterText(true);
        //设置图标中心空白，空心
        accountChart.setDrawHoleEnabled(true);
        //设置空心圆的弧度百分比，最大100
        accountChart.setHoleRadius(58f);
        accountChart.setHoleColor(Color.WHITE);
        //设置透明弧的样式
        accountChart.setTransparentCircleColor(Color.WHITE);
        accountChart.setTransparentCircleAlpha(110);
        accountChart.setTransparentCircleRadius(61f);

        //设置可以旋转
        accountChart.setRotationAngle(0);
        accountChart.setRotationEnabled(true);
        accountChart.setHighlightPerTapEnabled(true);

        Legend l = accountChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        accountChart.setEntryLabelColor(Color.BLACK);
        accountChart.setEntryLabelTextSize(12f);
        accountChart.animateY(1400, Easing.EaseInOutQuad);
    }

    public void getAccountLine(Long accountId, String type) {
        accountPresenter.getAccountLine(accountId, type);
    }

    @Override
    public void getAccountLineSuccess(List<AccountLine> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        AccountLineAdapter adapter = new AccountLineAdapter(list, this);
        recyclerView.setAdapter(adapter);

        Map<String, Float> total = new HashMap<>();
        for (AccountLine line : list) {
            if (total.containsKey(line.getAccountType())) {
                Float money = total.get(line.getAccountType());
                money += line.getChangeMoney() / 100;
                total.replace(line.getAccountType(), money);
            } else {
                total.put(line.getAccountType(), new Float(line.getChangeMoney()/100));
            }
        }
        List<PieEntry> entries = new ArrayList<>();
        Iterator<Map.Entry<String, Float>> iterator = total.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Float> next = iterator.next();
            entries.add(new PieEntry(Math.abs(next.getValue()), next.getKey()));
        }
//        for (int i = 0; i < total.size(); i++) {
//            //设置数据源
//            entries.add(new PieEntry((float) ((Math.random() * 10) + 10 / 5), "aaa"));
//        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        List<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(accountChart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        accountChart.setData(data);
        // undo all highlights
        accountChart.highlightValues(null);
        accountChart.invalidate();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

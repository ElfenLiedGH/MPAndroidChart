package com.xxmassdeveloper.mpchartexample.custom;

import com.github.ElfenLiedGH.charting.data.Entry;
import com.github.ElfenLiedGH.charting.formatter.IValueFormatter;
import com.github.ElfenLiedGH.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter implements IValueFormatter
{

    private final DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + " $";
    }
}

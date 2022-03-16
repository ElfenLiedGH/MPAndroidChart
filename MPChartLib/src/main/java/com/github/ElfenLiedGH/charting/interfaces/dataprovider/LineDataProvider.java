package com.github.ElfenLiedGH.charting.interfaces.dataprovider;

import com.github.ElfenLiedGH.charting.components.YAxis;
import com.github.ElfenLiedGH.charting.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}

package com.github.ElfenLiedGH.charting.interfaces.dataprovider;

import com.github.ElfenLiedGH.charting.components.YAxis.AxisDependency;
import com.github.ElfenLiedGH.charting.data.BarLineScatterCandleBubbleData;
import com.github.ElfenLiedGH.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}

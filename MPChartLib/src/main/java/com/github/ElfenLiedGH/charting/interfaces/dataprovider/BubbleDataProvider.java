package com.github.ElfenLiedGH.charting.interfaces.dataprovider;

import com.github.ElfenLiedGH.charting.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}

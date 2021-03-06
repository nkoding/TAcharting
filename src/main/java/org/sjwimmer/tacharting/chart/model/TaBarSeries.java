package org.sjwimmer.tacharting.chart.model;

import org.sjwimmer.tacharting.chart.model.types.GeneralTimePeriod;
import org.sjwimmer.tacharting.implementation.model.api.key.SQLKey;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.BarSeries;

import java.util.Currency;
import java.util.List;

public class TaBarSeries extends BaseBarSeries implements ChartBarSeries {

	private static final long serialVersionUID = 8968804460398253480L;
	
	private final Currency currency;
    private final GeneralTimePeriod periodType;

    public TaBarSeries(String name, List<Bar> BarList, Currency currency, GeneralTimePeriod periodType){
        super(name,BarList);
        this.currency = currency;
        this.periodType = periodType;
    }

    public TaBarSeries(BarSeries series, Currency currency, GeneralTimePeriod periodType){
        this(series.getName(), series.getBarData(),currency,periodType);
    }

    public Currency getCurrency() {
        return currency;
    }

    public GeneralTimePeriod getTimeFormatType() {
        return periodType;
    }

    public SQLKey getKey(){
        return new SQLKey(this.getName(),periodType,currency);
    }

    /**
     * Two TaBarSeries are equal if symbol, timePeriod and currency are the same
     * This overwriting is needed to reach correct behaviour of HashMaps and Sets
     * @param o object
     * @return false if <tt>o</tt> is not the 'same' or a TaBarSeries
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof TaBarSeries)){
            return false;
        }
        TaBarSeries other = ((TaBarSeries)o);
        return other.getCurrency().equals(this.currency) &&
                other.getTimeFormatType().equals(this.periodType) &&
                other.getName().equals(this.getName());
    }
}

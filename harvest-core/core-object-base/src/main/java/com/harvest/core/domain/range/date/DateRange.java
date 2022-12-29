package com.harvest.core.domain.range.date;

import com.harvest.core.domain.range.AbstractRange;
import com.harvest.core.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "日期区间", description = "不含时分秒")
public class DateRange extends AbstractRange<Date> {

    private static final long serialVersionUID = 5214008475981002020L;

    @Override
    public void setMin(Date min) {
        super.setMin(DateUtils.removeTime(min));
    }

    @Override
    public void setMax(Date max) {
        super.setMax(DateUtils.removeTime(max));
    }
}

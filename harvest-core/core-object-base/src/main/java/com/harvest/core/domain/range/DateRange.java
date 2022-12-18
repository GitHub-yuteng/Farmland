/*
 *
 * @(#)DateRange.java   2021-6-7
 *
 * Copyright (c) 2011-2021 杭州湖畔网络技术有限公司
 * 保留所有权利
 * 本软件为杭州湖畔网络技术有限公司所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2011-2021 HUPUN Network Technology CO.,LTD.
 * All rights reserved.
 * This software is the confidential and proprietary information of HUPUN
 * Network Technology CO.,LTD("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with HUPUN.
 * Website：http://www.hupun.com
 *
 */

package com.harvest.core.domain.range;

import com.harvest.core.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 日期区间（不含时分秒）
 *
 * @author: Will Wang 2021-06-07
 */
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

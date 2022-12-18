package com.harvest.core.domain.range;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 10:04 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class IntegerRange extends AbstractRange<Date> implements Serializable {

    private static final long serialVersionUID = -6348328505906617029L;

}

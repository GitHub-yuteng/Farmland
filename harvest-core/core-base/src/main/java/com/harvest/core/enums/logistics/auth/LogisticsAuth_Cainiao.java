package com.harvest.core.enums.logistics.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Alodi
 * @Date: 2023/2/25 10:52 PM
 * @Description: TODO
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsAuth_Cainiao extends LogisticsAuth {

    private static final long serialVersionUID = 2901732365755008404L;

    private String cainiao;
}

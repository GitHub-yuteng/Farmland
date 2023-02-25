package com.harvest.oms.domain.order.audit;

import lombok.Data;

/**
 * @Author: Alodi
 * @Date: 2023/2/9 3:40 PM
 * @Description: TODO
 **/
@Data
public class OrderAuditTransferDTO {

    private Boolean $continue;

    public Boolean getContinue() {
        return $continue;
    }

    public void setContinue(Boolean $continue) {
        this.$continue = $continue;
    }
}

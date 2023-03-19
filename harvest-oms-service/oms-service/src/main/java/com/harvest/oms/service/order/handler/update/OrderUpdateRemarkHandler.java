package com.harvest.oms.service.order.handler.update;

import com.harvest.core.annotation.BizLog;
import com.harvest.core.exception.ExceptionCodes;
import com.harvest.core.exception.StandardRuntimeException;
import com.harvest.core.log.RecordLog;
import com.harvest.core.service.utils.BizLogUtils;
import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.repository.domain.order.base.OrderOperationLog;
import com.harvest.oms.repository.domain.order.base.OrderRemark;
import com.harvest.oms.repository.domain.order.update.OrderSubmitUpdateField;
import com.harvest.oms.repository.domain.order.update.remark.OrderUpdateRemark;
import com.harvest.oms.service.order.handler.AbstractBizOrderHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Alodi
 * @Date: 2023/3/17 10:54 AM
 * @Description: TODO
 **/
@Component
public class OrderUpdateRemarkHandler extends AbstractBizOrderHandler implements OrderUpdateHandler {

    @Override
    protected String update() {
        return "更新订单备注";
    }

    @Override
    public boolean match(Long companyId, OrderSubmitUpdateField.UpdateEnum updateEnum) {
        return OrderSubmitUpdateField.UpdateEnum.REMARK.equals(updateEnum);
    }

    @BizLog
    @Override
    public void handle(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderUpdateRemark updateRemark = field.getRemark();
        // 追加备注
        if (Objects.nonNull(updateRemark.getAppend()) && updateRemark.getAppend()) {
            OrderRemark orderRemark = order.getOrderRemark();
            OrderRemark.RemarkEnum remarkEnum = updateRemark.getRemarkEnum();
            switch (remarkEnum) {
                case SELLER:
                    updateRemark.setSellerRemark(
                            StringUtils.isNotEmpty(orderRemark.getSellerRemark()) ?
                                    orderRemark.getSellerRemark() + LINE_FEED + updateRemark.getSellerRemark() :
                                    updateRemark.getSellerRemark()
                    );
                    break;
                case BUYER:
                    updateRemark.setBuyerRemark(
                            StringUtils.isNotEmpty(orderRemark.getBuyerRemark()) ?
                                    orderRemark.getBuyerRemark() + LINE_FEED + updateRemark.getSellerRemark() :
                                    updateRemark.getSellerRemark()
                    );
                    break;
                case SYSTEM:
                    updateRemark.setSystemRemark(
                            StringUtils.isNotEmpty(orderRemark.getSystemRemark()) ?
                                    orderRemark.getSystemRemark() + LINE_FEED + updateRemark.getSellerRemark() :
                                    updateRemark.getSellerRemark()
                    );
                    break;
                case PRINT:
                    updateRemark.setPrintRemark(
                            StringUtils.isNotEmpty(orderRemark.getPrintRemark()) ?
                                    orderRemark.getPrintRemark() + LINE_FEED + updateRemark.getSellerRemark() :
                                    updateRemark.getSellerRemark()
                    );
                    break;
                default:
                    throw new StandardRuntimeException(ExceptionCodes.OMS_MODULE_ERROR, "订单备注类型错误！");
            }
        }
        orderWriteRepositoryClient.updateRemark(companyId, order.getOrderId(), updateRemark);

        this.log(companyId, field, order);
    }

    /**
     * 记录处理日志
     *
     * @param companyId
     * @param field
     * @param order
     */
    @Override
    public void log(Long companyId, OrderSubmitUpdateField field, OrderInfoDO order) {
        OrderRemark orderRemark = order.getOrderRemark();
        OrderUpdateRemark updateRemark = field.getRemark();

        OrderOperationLog operationLog = OrderOperationLog.init();
        operationLog.setBusinessId(order.getOrderId());
        operationLog.setOperationType(RecordLog.OperationType.MODIFY);

        StringBuilder builder = new StringBuilder();
        builder.append(Log.ORIGINAL);

        OrderRemark.RemarkEnum remarkEnum = updateRemark.getRemarkEnum();
        switch (remarkEnum) {
            case SELLER:
                if (!StringUtils.equals(orderRemark.getSellerRemark(), updateRemark.getSellerRemark())) {
                    operationLog.setPrefix(this.update() + " | 卖家备注");
                    builder.append(orderRemark.getSellerRemark()).append(Log.CHANGE).append(updateRemark.getSellerRemark());
                    operationLog.setContent(builder.toString());
                    BizLogUtils.log(operationLog);
                }
                break;
            case BUYER:
                if (!StringUtils.equals(orderRemark.getBuyerRemark(), updateRemark.getBuyerRemark())) {
                    operationLog.setPrefix(this.update() + " | 买家备注");
                    builder.append(orderRemark.getBuyerRemark()).append(Log.CHANGE).append(updateRemark.getBuyerRemark());
                    operationLog.setContent(builder.toString());
                    BizLogUtils.log(operationLog);
                }
                break;
            case SYSTEM:
                if (!StringUtils.equals(orderRemark.getSystemRemark(), updateRemark.getSystemRemark())) {
                    operationLog.setPrefix(this.update() + " | 系统备注");
                    builder.append(orderRemark.getSystemRemark()).append(Log.CHANGE).append(updateRemark.getSystemRemark());
                    operationLog.setContent(builder.toString());
                    BizLogUtils.log(operationLog);
                }
                break;
            case PRINT:
                if (!StringUtils.equals(orderRemark.getPrintRemark(), updateRemark.getPrintRemark())) {
                    operationLog.setPrefix(this.update() + " | 打印备注");
                    builder.append(orderRemark.getPrintRemark()).append(Log.CHANGE).append(updateRemark.getPrintRemark());
                    operationLog.setContent(builder.toString());
                    BizLogUtils.log(operationLog);
                }
                break;
            default:
                break;
        }
    }

}

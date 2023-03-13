package com.harvest.oms.service.order.listener;

import com.harvest.oms.domain.order.OrderInfoDO;
import com.harvest.oms.enums.OrderEventEnum;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 9:11 PM
 * @Description: 订单事件监听处理器
 **/
public interface OrderEventListener {

    /**
     * 订单事件类型
     *
     * @return
     */
    OrderEventEnum type();

    /**
     * 订单下单
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void created(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单付款
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void paid(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单审核
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void audit(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单取消审核
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void returnAudit(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单申报
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void declare(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单发货
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void delivery(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单完成
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void finish(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单退款、关闭
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void close(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单删除
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void delete(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单恢复
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void restore(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单修改仓库
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void updateWarehouse(Long companyId, OrderInfoDO order) {
    }

    /**
     * 商品信息变更
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void goodsChange(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单标签修改
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void tagModify(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单备注修改
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void remarkModify(Long companyId, OrderInfoDO order) {
    }

    /**
     * 订单合并
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void merged(Long companyId, OrderInfoDO order) {
    }

}

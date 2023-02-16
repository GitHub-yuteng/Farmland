package com.harvest.oms.service.order.listener;

import com.harvest.oms.domain.order.OrderInfoDO;

/**
 * @Author: Alodi
 * @Date: 2022/12/12 9:11 PM
 * @Description: 订单事件监听处理器
 **/
public interface OrderEventListener {

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
    default void paid(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单审核
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void audit(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单取消审核
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void returnAudit(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单申报
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void declare(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单发货
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void delivery(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单退款、关闭
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void close(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单删除
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void delete(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单恢复
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void restore(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单修改仓库
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void updateWarehouse(long companyId, OrderInfoDO order) {
    }

    /**
     * 商品信息变更
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void goodsChange(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单标签修改
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void tagModify(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单备注修改
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void remarkModify(long companyId, OrderInfoDO order) {
    }

    /**
     * 订单合并
     *
     * @param companyId 公司id
     * @param order     相关订单
     */
    default void merged(long companyId, OrderInfoDO order) {
    }

}

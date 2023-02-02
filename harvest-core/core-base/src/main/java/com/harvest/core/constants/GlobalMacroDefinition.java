package com.harvest.core.constants;

/**
 * @Author: Alodi
 * @Date: 2022/12/11 7:17 PM
 * @Description: 全局宏定义 GlobalMacroDefinition
 **/
public interface GlobalMacroDefinition {

    String COMPANY_ID = "companyId";

    interface Switch {
        int ON = 1;
        int OFF = 0;
    }

    interface Automation {
        /**
         * 自动化执行时记录的用户ID
         */
        String USER_ID = "0";
        /**
         * 自动化执行时记录的用户名
         */
        String USER_NAME = "系统自动化";
    }

    interface Shop {
        String SHOP_ID = "shopId";
        String SHOP_IDS = "shopIds";
        String SHOP_SOURCE = "shopSource";
    }

    interface OMS {
        String ORDER_ID = "orderId";
        String ORDER_IDS = "orderIds";
        String ORDER_ITEM_ID = "orderItemId";
        String ORDER_ITEM_IDS = "orderItemIds";
        String ORDER_SOURCE = "orderSource";

        String SPU_ID = GOODS.SPU_ID;
        String SKU_ID = GOODS.SKU_ID;

        String OUTBOUND_BILL_ID = "outboundBillId";
        String OUTBOUND_BILL_IDS = "outboundBillIds";
        String INBOUND_BILL_ID = "inboundBillId";
        String INBOUND_BILL_IDS = "inboundBillIds";

        String TAG_ID = "tagId";
        String TAGS = "tagIds";
    }

    interface GOODS {
        String SPU_ID = "spuId";
        String SKU_ID = "skuId";
        String CATEGORY_ID = "categoryId";
        String BRAND_ID = "brandId";
        String UNIT_ID = "unitId";
    }

    interface FINANCE {
        String ACCOUNT_ID = "financeAccountId";
        String CURRENCY = "currency";
        String DOMESTIC_CURRENCY = "domesticCurrency";
        String FOREIGN_CURRENCY = "foreignCurrency";
        String EXCHANGE_RATE = "exchangeRate";
    }

    interface WMS {
        String WAREHOUSE_ID = "warehouseId";
        String WAREHOUSE_IDS = "warehouseIds";
        String WAREHOUSE_OWNER = "warehouseOwner";
    }

    interface TIME_BASE {
        String CREATE_TIME = "createTime";
        String MODIFY_TIME = "modifyTime";
        String BUSINESS_TIME = "businessTime";
        String DB_CREATE_TIME = "rcTime";
        String DB_MODIFY_TIME = "rmTime";
    }

    interface ParameterNames {

        String ID = "id";
        String IDS = "ids";
        String NAME = "name";

        String ACCOUNT_ID = "accountId";
        String USER_ID = "userId";
        String APPLICATION_TYPE = "applicationType";

        String KEYWORD = "keyword";
        String STATUS = "status";
        String IS_DISABLED = "isDisabled";
        String IS_DELETED = "isDeleted";
        String REMARK = "remark";
        String FAIL_REASON = "failReason";

        String BILL_ID = "billId";
        String BILL_IDS = "billIds";

        String BUYER_ID = "buyerId";
        String BUYER_IDS = "buyerIds";

        String LOGISTICS_ID = "logisticsId";
        String LOGISTICS_IDS = "logisticsIds";

    }

    /**
     * 常用属性参数 无特殊意义定义
     */
    int DEFAULT_10 = 10;

    int DEFAULT_INITIAL_CAPACITY = 64;

}

# Farmland Harvest. 供应链

## 前言
关于农业供应链项目，目的在于归类总结供应链中领域模型及其模版优化业务流程。

- 单据的生成
- 信息的存储
- 状态的流转
- 领域对象DDD构建

## 技术选型

- SpringCloud
- SpringBoot
- Caffeine Cache
- Redis
- MyBatis
- MyBatis-Plus
- Druid
- Lombok
- Hutool
- Guava
- Swagger3

## DB Schema
- farmland_biz 
- farmland_core
- farmland_finance
- farmland_goods
- farmland_inventory
- farmland_oms
  - farmland_oms_after_sale_bill
  - farmland_oms_after_sale_bill_item
  - farmland_oms_after_sale_bill_log
  - farmland_oms_inbound_bill
  - farmland_oms_inbound_bill_item
  - farmland_oms_logistics
  - farmland_oms_logistics_channel
  - farmland_oms_logistics_channel_address
  - farmland_oms_logistics_channel_setting
  - farmland_oms_order
  - farmland_oms_order_address
  - farmland_oms_order_declaration
  - farmland_oms_order_declaration_item
  - farmland_oms_order_item
  - farmland_oms_order_operation_log
  - farmland_oms_order_remark
  - farmland_oms_order_sensitive_data
  - farmland_oms_order_tag
  - farmland_oms_order_tag_custom_definition
  - farmland_oms_order_value_map
  - farmland_oms_outbound_bill
  - farmland_oms_outbound_bill_item
  - farmland_oms_simple_setting
  - farmland_oms_snapshot
  - farmland_oms_template_order_export
  - farmland_oms_template_order_import
- farmland_purchase
- farmland_rule
- farmland_task
- farmland_wms

## 开发环境

- JDK: 1.8
- Maven: 3.8.7
- Mysql: 8
- Redis: latest

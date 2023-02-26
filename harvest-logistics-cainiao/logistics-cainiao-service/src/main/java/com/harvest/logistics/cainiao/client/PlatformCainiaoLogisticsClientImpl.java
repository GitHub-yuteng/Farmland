package com.harvest.logistics.cainiao.client;

import com.harvest.basic.domain.logistics.DeclarationDataFile;
import com.harvest.basic.domain.logistics.DeclarationResponse;
import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.domain.file.OuterDataFormat;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.core.enums.logistics.auth.LogisticsAuth_Cainiao;
import com.harvest.core.utils.DateUtils;
import com.harvest.logistics.cainiao.HarvestCainiaoLogisticsApplications;
import com.harvest.core.enums.logistics.feature.LogisticsFeature_Cainiao;
import com.harvest.oms.request.order.declare.SubmitDeclarationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author: Alodi
 * @Date: 2023/2/4 2:27 PM
 * @Description: 菜鸟物流平台
 **/
@HarvestService(path = HarvestCainiaoLogisticsApplications.SERVICE_PATH)
public class PlatformCainiaoLogisticsClientImpl implements PlatformCainiaoLogisticsClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformCainiaoLogisticsClientImpl.class);

    @Override
    public void getToken(Long companyId) {
        System.out.println("获取授权");
    }

    @Override
    public void refreshToken(Long companyId) {
        System.out.println("刷新授权");
    }

    @Override
    public void validAccount(Long companyId) {
        System.out.println("验证账户");
    }

    @Override
    public DeclarationResponse submitDeclaration(Long companyId, SubmitDeclarationRequest request) {

        LogisticsEnum logisticsEnum = request.getLogisticsEnum();

        Class<?> clazz = LogisticsEnum.getAuthByType(logisticsEnum.getType());

        assert clazz != null;
        String simpleName = clazz.getSimpleName();
        Object authorization = request.getAuthorization();


        LOGGER.info(LogisticsEnum.CAINIAO.getName() + "申报");
        DeclarationResponse declarationResponse = new DeclarationResponse();
        declarationResponse.setSuccess(true);
        declarationResponse.setDeliveryNo("CAINIAO123");
        declarationResponse.setMessage("");
        declarationResponse.setDeclareTime(DateUtils.FORMAT.format(new Date()));
        declarationResponse.setFeature("");
        return declarationResponse;
    }

    @Override
    public void getDeliveryNo(Long companyId, SubmitDeclarationRequest request) {
        System.out.println("获取运单号");
    }

    @Override
    public DeclarationDataFile print(Long companyId, SubmitDeclarationRequest request) {
        DeclarationDataFile file = new DeclarationDataFile();
        file.setFormat(OuterDataFormat.PICTURE_DATA);
        file.setData("数据");
        return file;
    }

    @Override
    public void cancelDeclaration(Long companyId, SubmitDeclarationRequest request) {
        System.out.println("取消申报");
    }

    @Override
    public void queryPickTime(Long companyId) {
        System.out.println("查询揽收时间");

    }

}

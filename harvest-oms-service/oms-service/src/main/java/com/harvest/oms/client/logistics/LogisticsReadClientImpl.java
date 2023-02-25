package com.harvest.oms.client.logistics;

import com.harvest.core.annotation.feign.HarvestService;
import com.harvest.core.enums.logistics.LogisticsEnum;
import com.harvest.oms.client.constants.HarvestOmsApplications;
import com.harvest.oms.domain.logistics.LogisticsChannelAddressDO;
import com.harvest.oms.domain.logistics.LogisticsChannelDO;
import com.harvest.oms.repository.client.logistics.LogisticsReadRepositoryClient;
import com.harvest.oms.repository.domain.logistics.OrderLogisticsKey;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsChannelSimplePO;
import com.harvest.oms.repository.domain.logistics.simple.LogisticsSimplePO;
import com.harvest.oms.repository.entity.FarmlandOmsLogisticsChannelAddressEntity;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Alodi
 * @Date: 2022/12/9 10:24 PM
 * @Description: 物流读服务
 **/
@HarvestService(path = HarvestOmsApplications.LogisticsPath.LOGISTICS_READ)
public class LogisticsReadClientImpl implements LogisticsReadClient {

    @Autowired
    private LogisticsReadRepositoryClient logisticsReadRepositoryClient;

    @Override
    public Collection<LogisticsChannelDO> getChannelByLogisticsKeys(Long companyId, List<OrderLogisticsKey> logisticsKeys) {
        Collection<LogisticsChannelSimplePO> channelSimpleList = logisticsReadRepositoryClient.getChannelByLogisticsKeys(companyId, logisticsKeys);
        return channelSimpleList.stream().map(simple -> {
            LogisticsChannelDO logisticsChannel = new LogisticsChannelDO();
            BeanUtils.copyProperties(simple, logisticsChannel);
            return logisticsChannel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LogisticsChannelAddressDO> getChannelAddress(Long companyId, Long channelId) {
        List<FarmlandOmsLogisticsChannelAddressEntity> channelAddress = logisticsReadRepositoryClient.getChannelAddress(companyId, channelId);
        return channelAddress.stream().map(address -> {
            LogisticsChannelAddressDO logisticsChannelAddressDO = new LogisticsChannelAddressDO();
            logisticsChannelAddressDO.setShopId(address.getShopId());
            logisticsChannelAddressDO.setAddressType(address.getAddressType());
            logisticsChannelAddressDO.setCountryCode(address.getCountryCode());
            logisticsChannelAddressDO.setCountryCn(address.getCountryCn());
            logisticsChannelAddressDO.setPostalCode(address.getPostalCode());
            logisticsChannelAddressDO.setRegion1(address.getRegion1());
            logisticsChannelAddressDO.setRegion2(address.getRegion2());
            logisticsChannelAddressDO.setRegion3(address.getRegion3());
            logisticsChannelAddressDO.setRegion4(address.getRegion4());
            logisticsChannelAddressDO.setDetail(address.getDetail());
            return logisticsChannelAddressDO;
        }).collect(Collectors.toList());
    }

    @Override
    public String getAuthorization(Long companyId, LogisticsEnum logisticsType) {
        LogisticsSimplePO logisticsSimple = logisticsReadRepositoryClient.getLogistics(companyId, logisticsType);
        if (Objects.isNull(logisticsSimple)) {
            return Strings.EMPTY;
        }
        return logisticsSimple.getAuthorization();
    }

}

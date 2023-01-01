package com.harvest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralRequest;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class TestCloud {
    /**
     * 代码示例：请求词性标注算法
     */
    public static void main(String[] args) throws ClientException {
        //下方第二项和第三项需要替换为您的accessKeyId和accessKeySecret，获取或创建方式详见文档《获取AccessKey》
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "LTAI4FjLMmdz1UoVQeg3GHHg",
                "uCLsM6Jx72Ctz4bIE31dyoYBVNOKvG");
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetSaChGeneral是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求
        GetSaChGeneralRequest request = new GetSaChGeneralRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText("面料真好");
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetSaChGeneral也需要替换
        GetSaChGeneralResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis()- start));
    }
}
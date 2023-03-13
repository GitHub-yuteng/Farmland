package com.harvest.core.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Semaphore;

/**
 * @Author: Alodi
 * @Date: 2023/2/10 5:40 PM
 * @Description: TODO
 **/
public class PDFUtils {

    /**
     * 最多允许50个线程同时执行，防止并发量高时导致 OOM
     */
    private static final Semaphore SEMAPHORE = new Semaphore(50);

    /**
     * 将远程文件下载转为图片链接
     *
     * @param url
     * @return
     */
    public static String toImageOnOss(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        try {
            SEMAPHORE.acquire();
            return doConvert(url);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            SEMAPHORE.release();
        }
    }

    private static String doConvert(String url) {
        // 下载 pdf 文件 转 图片 上传 oss 获取链接返回
        return "images_url";
    }

}

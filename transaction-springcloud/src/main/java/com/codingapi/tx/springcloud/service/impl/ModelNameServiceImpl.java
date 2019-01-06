package com.codingapi.tx.springcloud.service.impl;

import com.codingapi.tx.listener.service.ModelNameService;
import com.codingapi.tx.netty.utils.IpAddressUtils;
import com.codingapi.tx.springcloud.listener.ServerListener;
import com.lorne.core.framework.utils.encode.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by lorne on 2017/7/12.
 */
@Service
@Configuration
public class ModelNameServiceImpl implements ModelNameService {

    @Value("${spring.application.name}")
    private String modelName;

    @Autowired
    private ServerListener serverListener;

    Logger logger = LoggerFactory.getLogger(getClass());

    private String host = null;

    @Override
    public String getModelName() {
        return modelName;
    }

    /**
     * 获取ip地址，先获取hostname，如果获取的是正常的ip且不为127.0.0.1则返回，否则遍历网卡，获取有效ip
     * @return
     */
    private String getIp() {
        if (StringUtils.isBlank(host)) {
            try {
                host = InetAddress.getLocalHost().getHostAddress();
                if (IpAddressUtils.isIpAddress(host) && !"127.0.0.1".equals(host)) {
                    logger.info("getIp1={}", host);
                    return host;
                } else {
                    // 读网卡的ip地址
                    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
                    for (; n.hasMoreElements(); ) {
                        NetworkInterface e = n.nextElement();
                        logger.info("Interface:{}", e.getName());
                        Enumeration<InetAddress> a = e.getInetAddresses();
                        for (; a.hasMoreElements(); ) {
                            InetAddress addr = a.nextElement();
                            if (IpAddressUtils.isIpAddress(addr.getHostAddress()) && !"127.0.0.1".equals(addr.getHostAddress())) {
                                logger.info("addr.getHostAddress={}", addr.getHostAddress());
                                return addr.getHostAddress();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("getIp.e={}", e);
                e.printStackTrace();
            }
        }
        logger.info("getIp2={}", host);
        return host;
    }

    private int getPort() {
        int port = serverListener.getServerPort();
        int count = 0;
        while (port == 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            port = serverListener.getServerPort();
            count++;

            if (count == 2000) {
                throw new RuntimeException("get server port error.");
            }
        }

        return port;
    }

    @Override
    public String getUniqueKey() {
        String address = getIp() + getPort();
        return MD5Util.md5(address.getBytes());
    }


    @Override
    public String getIpAddress() {
        String address = getIp() + ":" + getPort();
        return address;
    }
}

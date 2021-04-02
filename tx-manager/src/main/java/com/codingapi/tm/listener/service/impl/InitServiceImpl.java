package com.codingapi.tm.listener.service.impl;

import com.codingapi.tm.Constants;
import com.codingapi.tm.config.ConfigReader;
import com.codingapi.tm.listener.service.InitService;
import com.codingapi.tm.netty.service.NettyServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lorne on 2017/7/4.
 */
@Service
public class InitServiceImpl implements InitService {

    private final static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Autowired
    private NettyServerService nettyServerService;

    @Autowired
    private ConfigReader configReader;


    @Override
    public void start() {
        /**加载本地服务信息**/

        Constants.socketPort = configReader.getSocketPort();
        Constants.maxConnection = configReader.getSocketMaxConnection();
        nettyServerService.start();

        welcome();
    }


    private void welcome() {
        logger.info("");
        logger.info("");
        logger.info("\t\t**  \t\t ****\t\t**  **");
        logger.info("\t\t**  \t\t**   \t\t*** **");
        logger.info("\t\t**  \t\t**   \t\t** ***");
        logger.info("\t\t*****\t\t ****\t\t**  **");
        logger.info("");
        logger.info("\t\tLCN-TxManager  version:4.1.0-xcloud");
        logger.info("");
    }


    @Override
    public void close() {
        nettyServerService.close();
    }
}

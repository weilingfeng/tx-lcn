package com.codingapi.tx.spi.rpc;


import lombok.extern.slf4j.Slf4j;

/**
 * @author lorne
 * @date 2018/12/2
 * @description
 */
@Slf4j
public enum LCNCmdType {

    /**
     * 事务提交
     */
    notifyUnit("notify-unit", MessageConstants.ACTION_NOTIFY_UNIT, "事务提交"),

    /**
     * 创建事务组
     * <p>
     * 简写 cg
     */
    createGroup("create-group", MessageConstants.ACTION_CREATE_GROUP, "创建事务组"),

    /**
     * 加入事务组
     * <p>
     * 简写 cg
     */
    joinGroup("join-group", MessageConstants.ACTION_JOIN_GROUP, "加入事务组"),

    /**
     * 通知事务组
     * 简写 clg
     */
    notifyGroup("notify-group", MessageConstants.ACTION_NOTIFY_GROUP, "通知事务组"),

    /**
     * 响应事务状态
     * 间写 ats
     */
    askTransactionState("ask-transaction-state", MessageConstants.ACTION_ASK_TRANSACTION_STATE, "响应事务状态"),

    /**
     * 记录补偿
     * 简写 wc
     */
    writeCompensation("write-compensation", MessageConstants.ACTION_WRITE_COMPENSATION, "记录补偿"),


    /**
     * TxManager请求连接
     * 简写 nc
     */
    notifyConnect("notify-connect", MessageConstants.ACTION_NOTIFY_CONNECT, "TxManager请求连接"),


    /**
     * 初始化客户端
     * 简写 ic
     */
    initClient("init-client", MessageConstants.ACTION_INIT_CLIENT, "初始化客户端"),

    /**
     * 获取切面日志
     * 简写 gal
     */
    getAspectLog("get-aspect-log", MessageConstants.ACTION_GET_ASPECT_LOG, "获取切面日志");

    private String code;

    private String name;

    private String desc;

    LCNCmdType(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static LCNCmdType parserCmd(String cmd) {
        LCNCmdType lcnCmdType;
        switch (cmd) {
            case MessageConstants.ACTION_CREATE_GROUP:
                lcnCmdType = createGroup;
                break;
            case MessageConstants.ACTION_NOTIFY_GROUP:
                lcnCmdType = notifyGroup;
                break;
            case MessageConstants.ACTION_NOTIFY_UNIT:
                lcnCmdType = notifyUnit;
                break;
            case MessageConstants.ACTION_JOIN_GROUP:
                lcnCmdType = joinGroup;
                break;
            case MessageConstants.ACTION_ASK_TRANSACTION_STATE:
                lcnCmdType = askTransactionState;
                break;
            case MessageConstants.ACTION_WRITE_COMPENSATION:
                lcnCmdType = writeCompensation;
                break;
            case MessageConstants.ACTION_NOTIFY_CONNECT:
                lcnCmdType = notifyConnect;
                break;
            case MessageConstants.ACTION_GET_ASPECT_LOG:
                lcnCmdType = getAspectLog;
                break;
            case MessageConstants.ACTION_INIT_CLIENT:
                lcnCmdType = initClient;
                break;
            default:
                throw new IllegalStateException("unsupported cmd.");
        }
        log.debug("parsed cmd: {}  ->  {}", cmd, lcnCmdType.desc);
        return lcnCmdType;
    }
}

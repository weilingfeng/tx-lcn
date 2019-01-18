/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.txlcn.client.support;

import com.codingapi.txlcn.client.bean.TxTransactionInfo;
import com.codingapi.txlcn.commons.exception.TransactionException;

/**
 * Description: 事务分离器
 * Date: 2018/12/5
 *
 * @author ujued
 */
public interface TXLCNTransactionSeparator {

    /**
     * 判断事务状态
     *
     * @param txTransactionInfo txTransactionInfo
     * @return TXLCNTransactionState
     */
    TXLCNTransactionState loadTransactionState(TxTransactionInfo txTransactionInfo) throws TransactionException;
}

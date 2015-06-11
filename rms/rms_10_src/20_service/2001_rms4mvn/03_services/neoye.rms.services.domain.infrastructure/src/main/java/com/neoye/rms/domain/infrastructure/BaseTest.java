package com.neoye.rms.domain.infrastructure;

import java.util.HashMap;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import rms.cmpnt.libs.commons.CodeConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/**/applicationContext*.xml" })
// 如果是true不会改变数据库,如果是false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class BaseTest {
    /**
     * 用于解开proxy层返回给Flex的MAP，取得其中的data add by liuxy 20141204
     */
    public <T> T getResultMapData(HashMap<String, T> retMap) {
        if (retMap != null)
            return retMap.get(CodeConstant.MESSAGE_DATA);
        else
            return null;
    }
}

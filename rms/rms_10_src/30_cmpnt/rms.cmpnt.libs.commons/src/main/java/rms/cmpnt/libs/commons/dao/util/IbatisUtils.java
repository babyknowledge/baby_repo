package rms.cmpnt.libs.commons.dao.util;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 
* 项目名称: 广播电视节目监测自动编目与检索系统<br>
* 模块名称: 工具类<br>
* 功能描述: 获取IBatis的SqlMapClient类<br>
* 创建日期: Feb 18, 2013 <br>
* 版权信息: Copyright (c) 2013<br>
* 公司信息: 沈阳东软系统集成工程有限公司<br> 
* @author <a href="mailto: zh_yi@neusoft.com">张义</a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    Feb 18, 2013        张义       创建
* </pre>
 */
public class IbatisUtils {

    private static SqlMapClient sqlMapper;

    public static synchronized SqlMapClient getSqlmap() {
        if (sqlMapper == null) {
            try {
                Reader reader = Resources
                        .getResourceAsReader("SqlMapConfig.xml");
                sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlMapper;
    }
}

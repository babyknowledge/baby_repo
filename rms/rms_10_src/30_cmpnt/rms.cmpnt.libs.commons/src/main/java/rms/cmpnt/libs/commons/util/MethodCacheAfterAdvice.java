package rms.cmpnt.libs.commons.util;

import java.lang.reflect.Method;
import net.sf.ehcache.Cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: ehcache<br>
 * 功能描述: 缓存对象清除功能<br>
 * 在bo层对某个查询方法的返回结果进行缓存规则 1 命名： 方法名必须以findCache开头，例如findCacheSysConfig，以使拦截器进行拦截
 * 同时该bo类内部必须定义方法以removeCache开头
 * ，与findCache对应，例如removeCacheSysConfig，同时如果方法带参数也必须与findCache方法所带参数类型一致
 * 对于该bo类内部其它增删改的方法内部必须调用removeCache开头的方法
 * ，以清空缓存，调用不支持方法内部调用，必须将本身bo注入进来，以bo.removeCache()方式调用拦截器才起作用 2 使用方式
 * 首次查询后将结果存入缓存，以后的查询都会从缓存中读取，发生增删改的时候，支持缓存数据的变更 支持数据库部分数据缓存同时，只变更该部分数据的缓存
 * 例如List liall = bo.findCacheSysConfig("1")
 * 可取得数据库中类型为1的一系列记录数据的缓存，在增删改的方法中调用removeCache
 * ("1")的调用即实现了数据库中类型为1的一系列记录数据的缓存的变更
 * 
 * 从缓存中筛选数据的方式须在该方法中首先执行findCacheSysConfig方法取出所有数据，再从返回的list中根据条件筛选(需根据具体情况编码实现)
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: qinshch@neusoft.com">秦绍岑</a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3      秦绍岑       创建
 * </pre>
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice,
		InitializingBean {
	// 缓存对象
	private Cache cache;

	// 日志对象
	private static final Log log = LogFactory
			.getLog(MethodCacheAfterAdvice.class);

	/**
	 * setCache
	 * 
	 * @param cache
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * 构造方法
	 * 
	 * @coustructor
	 */
	public MethodCacheAfterAdvice() {
		super();
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Throwable
	 */
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		log.debug("into MethodCacheAfterAdvice");
		String targetName = arg3.getClass().getName();
		String methodNameRmove = arg1.getName();
		String methodName = "findCache"
				+ methodNameRmove.substring(11, methodNameRmove.length());
		String cacheKey = getCacheKey(targetName, methodName, arg2);
		log.debug("afterReturning cacheKey=" + cacheKey);
		cache.remove(cacheKey);
		log.debug("remove cache " + cacheKey);
	}

	/**
	 * 得到缓存对象的key
	 * 
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 * @return
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * afterPropertiesSet
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,
				"Need a cache. Please use setCache(Cache) create it.");
	}

}

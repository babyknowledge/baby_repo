package rms.cmpnt.libs.commons.util;

import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: ehcache<br>
 * 功能描述: <br>
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
 *    1    2009-8-3      秦绍岑      创建
 * </pre>
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {
	// 缓存对象
	private Cache cache;

	// 日志对象
	private static final Log log = LogFactory
			.getLog(MethodCacheInterceptor.class);

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
	public MethodCacheInterceptor() {
		super();
	}

	/**
	 * 拦截Service/DAO的方法，并查找该结果是否存在，如果存在就返回cache中的值， 否则，返回数据库查询结果，并将查询结果放入cache
	 * 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("into MethodCacheInterceptor");
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result;

		log.debug("Find object from cache is " + cache.getName());

		String cacheKey = getCacheKey(targetName, methodName, arguments);
		log.debug("invoke cacheKey=" + cacheKey);
		Element element = cache.get(cacheKey);

		if (element == null) {
			log.debug("Hold up method , Get method result and create cache........!");
			result = invocation.proceed();
			element = new Element(cacheKey, (Serializable) result);
			cache.put(element);
		}
		return element.getValue();
	}

	/**
	 * 获得cache key的方法，cache key是Cache中一个Element的唯一标识 cache key包括
	 * 包名+类名+方法名，如com.co.cache.service.UserServiceImpl.getAllUser
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
	 * implement InitializingBean，检查cache是否为空
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,
				"Need a cache. Please use setCache(Cache) create it.");
	}

}

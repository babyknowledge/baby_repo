package com.neoye.rms.domain.infrastructure.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.log.Log;
import rms.cmpnt.libs.commons.proxy.BaseProxy;

@Component
@Aspect
public class DebugPerformance2LogAop extends BaseProxy {

	private Log log = Log.getClassLogger(DebugPerformance2LogAop.class);

	
//	public static final String EDPRep = "(execution(public * com.neusoft.gbw..intf.*Proxy.*(..))) or (execution(public * com.neusoft.gbw..enti.*Entity.*(..)))";
	public static final String EDPRep = "execution(public * com.neusoft.gbw..intf.*Proxy.*(..))";
    // 方法执行前后调用 
    @Around(EDPRep)
    public Object aroundAop(ProceedingJoinPoint joinPoint) throws Throwable {
//        log = Log.getClassLogger(joinPoint.getTarget().getClass());
        String stackTraceStr = null;
        Object object  = null;
        try {
            long bt = System.currentTimeMillis();
            log.debug("=============="+joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"()开始时间："+bt);
        
            object = joinPoint.proceed();
            
            long et = System.currentTimeMillis();
            log.debug("=============="+joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"()结束时间："+et+"=======用时："+(et-bt)+"毫秒！"+(et-bt)/1000+"秒！");
            
        } catch (ServiceException e) {            
        } catch (Exception e) {
        }
        return object;
    }

	private String makeStackTraceStr(Exception e){
	    StackTraceElement[] ele = e.getStackTrace();
	    return ele[0].toString();
	}
	
	private String makeErrorLog(ProceedingJoinPoint joinPoint){
		StringBuilder  retSb = new StringBuilder("");
		Object[] args = joinPoint.getArgs();
		//方法名
		retSb.append(joinPoint.getSignature().getName()).append(":");
		//参数
		int index = 1;
		for (Object info:args){
			if (index==1)
			retSb.append(info==null?"":info.toString());
			else
				retSb.append(",").append(info==null?"":info.toString());
			index++;
		}
		return retSb.toString();
	}
	
	private Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		return joinPoint.proceed(args);
	}
}

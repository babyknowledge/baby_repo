package com.neoye.rms.domain.infrastructure.aop;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import rms.cmpnt.libs.commons.CodeConstant;
import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.log.Log;
import rms.cmpnt.libs.commons.proxy.BaseProxy;

@Component
@Aspect
public class ProxyReturnAop extends BaseProxy {

	private Log log = Log.getClassLogger(ProxyReturnAop.class);

	public static final String EDP = "execution(* com.neusoft.gbw..intf.*Proxy.*(..))";
	// 方法执行前后调用
	@Around(EDP)
	public Object aroundAop(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, Object> msg = getRtnMsg();
		String stackTraceStr = null;
		try {
		    long bt = System.currentTimeMillis();
//		    log.debug("=========="+joinPoint.getSignature().getName()+"()开始时间："+bt);
	    
			Object object = joinPoint.proceed();
			// FIXME zh_yi 临时解决方案
			if(object instanceof Map) {
				Map m = (Map) object;
				if(m.containsKey(CodeConstant.MESSAGE_STATUS))
					msg.put(CodeConstant.MESSAGE_STATUS, m.get(CodeConstant.MESSAGE_STATUS));
				if(m.containsKey(CodeConstant.MESSAGE_MSG))
					msg.put(CodeConstant.MESSAGE_MSG, m.get(CodeConstant.MESSAGE_MSG));
				if(m.containsKey(CodeConstant.MESSAGE_DATA))
					msg.put(CodeConstant.MESSAGE_DATA, m.get(CodeConstant.MESSAGE_DATA));
				else 
					msg.put(CodeConstant.MESSAGE_DATA, object);
			}
			else {
				msg.put(CodeConstant.MESSAGE_DATA, object);
			}
			
			long et = System.currentTimeMillis();
//			log.debug("=========="+joinPoint.getSignature().getName()+"()结束时间："+et+"=======用时："+(et-bt)+"毫秒！"+(et-bt)/1000+"秒！");
			
			
		} catch (ServiceException e) {
			log = Log.getClassLogger(joinPoint.getTarget().getClass());
			log.error(makeErrorLog(joinPoint));
			log.error(e.getMessage());
			stackTraceStr = makeStackTraceStr(e) ;
            log.debug(stackTraceStr);
            e.printStackTrace();
			msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
			msg.put(CodeConstant.MESSAGE_MSG, e.getMessage()+"\n==========StackTrace=========\n"+stackTraceStr);
		} catch (Exception e) {
			log = Log.getClassLogger(joinPoint.getTarget().getClass());
            log.error(makeErrorLog(joinPoint));
			log.error(e.getMessage());
			stackTraceStr =makeStackTraceStr(e) ;
			log.debug(stackTraceStr);
			e.printStackTrace();
			msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
			msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_ERROR +"\n==========StackTrace=========\n"+stackTraceStr);
		}
		return msg;
	}

	private String makeStackTraceStr(Exception e){
	    StackTraceElement[] ele = e.getStackTrace();
	    return ele[0].toString();
//	    StringWriter sw = new StringWriter();
//	    PrintWriter pw = new PrintWriter(sw);
//	    try{
//	        e.printStackTrace(pw);
//	        return sw.toString();	        
//	    }
//	    finally{
//	        pw.close();
//	    }
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

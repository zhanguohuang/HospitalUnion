package org.hu.logservice;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hu.annocation.ServiceLog;
import org.hu.annocation.SystemControllerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * 定义日志的切面
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	//Service层切点
	@Pointcut("@annotation(org.hu.annocation.ServiceLog)")
	public void serviceAspect(){}
	
	//Controller层切点
	@Pointcut("@annotation(org.hu.annocation.SystemControllerLog)")
	public void controllerAspect(){}
	
	/*
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * @param joinPoint切点
	 */
	@Before("controllerAspect()")
	public void deBefore(JoinPoint joinPoint){
		System.out.println("====前置通知开始====");
		logger.info("hello");
	
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		String ip = request.getRemoteAddr();
		try{
			System.out.println("====前置通知开始====");
			System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
			System.out.println("请求IP:" + ip);
			System.out.println("====前置通知结束====");
		}catch(Exception e){
			logger.error("==前置通知异常==");
			logger.error("异常信息:",e.getMessage());
		}
	
	}

	@After("controllerAspect()")
	public void deAfter(JoinPoint joinPoint){
		System.out.println("====后置通知====");
	}
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception{
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for(Method method : methods){
			if(method.getName().equals(methodName)){
				Class[] clazzs = method.getParameterTypes();
				if(clazzs.length == arguments.length){
					description = method.getAnnotation(SystemControllerLog.class).description();
				}
			}
		}
		return description;
	}
}

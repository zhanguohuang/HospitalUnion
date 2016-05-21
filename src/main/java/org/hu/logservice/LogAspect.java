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
 * ������־������
 */
@Aspect
@Component
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	//Service���е�
	@Pointcut("@annotation(org.hu.annocation.ServiceLog)")
	public void serviceAspect(){}
	
	//Controller���е�
	@Pointcut("@annotation(org.hu.annocation.SystemControllerLog)")
	public void controllerAspect(){}
	
	/*
	 * ǰ��֪ͨ ��������Controller���¼�û��Ĳ���
	 * @param joinPoint�е�
	 */
	@Before("controllerAspect()")
	public void deBefore(JoinPoint joinPoint){
		System.out.println("====ǰ��֪ͨ��ʼ====");
		logger.info("hello");
	
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		String ip = request.getRemoteAddr();
		try{
			System.out.println("====ǰ��֪ͨ��ʼ====");
			System.out.println("���󷽷�:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("��������:" + getControllerMethodDescription(joinPoint));
			System.out.println("����IP:" + ip);
			System.out.println("====ǰ��֪ͨ����====");
		}catch(Exception e){
			logger.error("==ǰ��֪ͨ�쳣==");
			logger.error("�쳣��Ϣ:",e.getMessage());
		}
	
	}

	@After("controllerAspect()")
	public void deAfter(JoinPoint joinPoint){
		System.out.println("====����֪ͨ====");
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

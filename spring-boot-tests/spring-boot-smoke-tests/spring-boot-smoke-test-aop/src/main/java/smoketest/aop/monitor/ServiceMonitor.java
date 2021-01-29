/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smoketest.aop.monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ServiceMonitor {

	@AfterReturning("execution(* smoketest..*Service.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
	}


	@Around("execution(* smoketest..*Service.*(..))")
	public void tigerAop(ProceedingJoinPoint pjp) {
		log.info("tigerAop touched!");

		try {
			log.info("before tigerAop");
			Object[] args = pjp.getArgs();
			System.out.println("Params :" + Arrays.stream(args).peek(System.out::print));
			Class<?> aClass = pjp.getTarget().getClass();
			String methodName = pjp.getSignature().getName();
			Method method = null;
			Arrays.stream(aClass.getDeclaredMethods()).peek(System.out::print);
			if(args.length == 0){
				method = aClass.getMethod(methodName);
			}else {
				List<Class<?>> paramsClass = new ArrayList<>();
				for (Object arg : args) {
					paramsClass.add(arg.getClass());
				}
				;
				method = aClass.getMethod(methodName,paramsClass.toArray(new Class<?>[]{}));
			}
			System.out.println(method.getName());
			pjp.proceed();
			log.info("after tigerAop");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

}

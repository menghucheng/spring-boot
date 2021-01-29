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

package smoketest.aop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smoketest.aop.inter.ActionInter;

@Component
public class HelloWorldService implements ActionInter {

	@Value("${name:World}")
	private String name;

	public String getHelloMessage() {
		return "Hello " + this.name;
	}

	/**
	 * say hello to ${name}
	 * @param name
	 */
	public void sayHello(String name){

		System.out.println(String.format("Hello !! %s!!!",name));
	}

//	@Override
//	public void action() {
//		System.out.println("shadow hands");
//	}
}

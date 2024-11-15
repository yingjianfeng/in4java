package com.in4java.proxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author: yingjf
 * @date: 2024/8/29 10:29
 * @description:
 */
public class SpringProxy {
    public static void main(String[] args) {
        IUserService userService = new UserService();
// spring 将cglib和jdk动态代理合二为一了，如果有接口，就会走jdk代理，如果只有类，就会走cglib代理
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Class[] { IUserService.class });
// 可以设置多个Advice，会形成代理链

        proxyFactory.addAdvisor(new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return new Pointcut() {
                    @Override
                    public ClassFilter getClassFilter() {
                        return new ClassFilter() {
                            @Override
                            public boolean matches(Class<?> clazz) {
                                // 类匹配器
                                return false;
                            }
                        };
                    }

                    @Override
                    public MethodMatcher getMethodMatcher() {
                        // 方法匹配器
                        return new MethodMatcher() {
                            @Override
                            public boolean matches(Method method, Class<?> targetClass) {
                                return false;
                            }

                            @Override
                            public boolean isRuntime() {
                                return false; // 如果为true时，下面的参数matches就会生效
                            }

                            @Override
                            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                                return false;
                            }
                        };
                    }
                };
            }

            @Override
            public Advice getAdvice() {
                return new MyAfterAdvice();
            }

            // 没用
            @Override
            public boolean isPerInstance() {
                return true;
            }
        });

        IUserService proxy = (IUserService) proxyFactory.getProxy();

        proxy.test();
        // com.in4java.proxy.UserService@2f9f7dcf  无接口

    }
}

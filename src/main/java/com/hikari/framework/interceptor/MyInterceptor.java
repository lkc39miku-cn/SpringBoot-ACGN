package com.hikari.framework.interceptor;

import com.hikari.commons.util.id.IdStrategy;
import com.hikari.commons.util.id.MyIdStrategy;
import com.hikari.framework.annotation.AutoGenerateId;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author
 * @discription
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class MyInterceptor implements Interceptor {

    private final IdStrategy idStrategy = new MyIdStrategy();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {


        Object[] args = invocation.getArgs();
        if (args.length == 2 && args[1] != null) {

            Object paramObject = args[1];
            Class<?> aClass = paramObject.getClass();
            AutoGenerateId annotation = aClass.getAnnotation(AutoGenerateId.class);
            if (annotation != null) {

                String name = annotation.name();
                Field declaredField = aClass.getDeclaredField(name);

                declaredField.setAccessible(true);
                declaredField.set(paramObject, idStrategy.product());

                args[1] = paramObject;

            }

        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        properties.forEach((key, value) -> System.out.println(key + ":" + value));
    }
}
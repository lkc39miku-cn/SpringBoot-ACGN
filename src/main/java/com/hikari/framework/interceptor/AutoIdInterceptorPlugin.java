package com.hikari.framework.interceptor;

import com.hikari.commons.util.id.SnowflakeIdWorker;
import com.hikari.framework.annotation.IdAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})})
@Slf4j
public class AutoIdInterceptorPlugin implements Interceptor {
   @Override
   public Object intercept(Invocation invocation) throws Throwable {
       Object[] args = invocation.getArgs();
       MappedStatement arg = (MappedStatement) args[0];
       if (SqlCommandType.INSERT.name().equals(arg.getSqlCommandType().name())) {
           ParameterMap parameterMap = arg.getParameterMap();
           Class<?> type = parameterMap.getType();
           Field[] fields = type.getDeclaredFields();
           Object arg1 = args[1];
           for (Field field : fields) {
               IdAnnotation annotation = field.getAnnotation(IdAnnotation.class);
               if (annotation != null) {
                   setField(arg1, field.getName(), String.valueOf(SnowflakeIdWorker.getId()));
               }
           }

       }
       return invocation.proceed();
   }
   @Override
   public Object plugin(Object o) {
       return Plugin.wrap(o,this);
   }

   @Override
   public void setProperties(Properties properties) {
       log.warn(properties.toString());
   }

   public static void setField(Object o,String args,Object attributeValue){
       Class<?> cls = o.getClass();
       String fieldName = "set"+args.substring(0,1).toUpperCase()+(args.length()>1?args.substring(1):"");
       Method method;
       try {
           method = cls.getMethod(fieldName,attributeValue.getClass());
           method.invoke(o,attributeValue);
       } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
           e.printStackTrace();
       }
   }
}


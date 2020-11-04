package com.zdb.demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.zdb.demo.util.DateUtilJava8;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;


/**
 * className MethodLogAspect
 * description MethodLogAspect
 *
 * @author Toby
 * @date 2018-8-25
 */
@Aspect
@Component()
@Slf4j
public class MethodExecutionLogAspect {

    @Pointcut(value = "execution(public * com.zdb.demo.controller..*Controller.* (..)))")
    private void controller() {
    }

    @Around("controller()")
   // @ResponseBody
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes));
        HttpServletRequest request = servletRequestAttributes.getRequest();
        try {
            // 获取参数名
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            String[] parameterNames = signature.getParameterNames();
            String beginTime = DateUtilJava8.format(new Date());
            log.info("============== 接口: {} 调用 开始 ===============================", request.getRequestURI());
            log.info("ACCESS URL: {}, BEGIN TIME: {}, METHOD: {}, PARAMS_KEY: {}, PARAMS_VALUE: {}",
                    request.getRequestURI(), beginTime, request.getMethod(), parameterNames, objectToString(joinPoint.getArgs()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        Object result = joinPoint.proceed();

        try {
            String endTime = DateUtilJava8.format(new Date());
            log.info("OUT OF URL: {},   END TIME: {}, RESULT: {}", request.getRequestURI(), endTime, result);
            log.info("============== 接口: {} 调用 结束 ===============================", request.getRequestURI());

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return result;
    }


    private String objectToString(Object[] objs) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder("[");
        int initLength = stringBuilder.length();
        for (Object obj : objs) {
            try {
                String s = objectMapper.writeValueAsString(obj);
                stringBuilder.append(s).append(",");
            } catch (JsonProcessingException e) {
                stringBuilder.append(obj.toString()).append(",");
                //e.printStackTrace();
            }
        }
        int length = stringBuilder.length();
        if (length > initLength) {
            return stringBuilder.deleteCharAt(length - 1).append("]").toString();
        }
        return "empty";
    }
}

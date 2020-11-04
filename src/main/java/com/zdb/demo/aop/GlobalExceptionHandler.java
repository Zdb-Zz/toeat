package com.zdb.demo.aop;

import com.zdb.demo.util.ResultUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @Transactional
    public Map<String,Object>businessExceptionHandler(Exception e){

        if(e instanceof NullPointerException){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动事务回滚
            StringBuffer stringBuffer = new StringBuffer();
            for(StackTraceElement s:  e.getStackTrace())
            {
             stringBuffer.append(s);
             stringBuffer.append(";");
            }
            e.printStackTrace();
            return ResultUtil.resultFail("数据有关键字段为空"+"\n"+stringBuffer,0,null);
        }else {
            e.printStackTrace();
            return ResultUtil.resultFail(e.getMessage(),0,null);
        }

    }
}

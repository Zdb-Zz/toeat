package com.zdb.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class MyControllerAdvice {


   /* @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> myExceptionHandler(HttpServletResponse response, Exception e) throws Exception {
        if (e != null) {
            e.printStackTrace();
        }
        if (e instanceof FileNotFoundException) {
            response.setHeader("Content-Type", "application/json");
            Map<String, Object> map = new HashMap<String, Object>();
            //发生异常进行日志记录
            log.info("===============文件url错误，请重新选择文件=========================");
            return ResultUtil.resultFail("文件url出错", null, null);
        }else if(e instanceof RuntimeException){
            return ResultUtil.resultFail(e.getMessage(), null, null);
        }

        return ResultUtil.resultFail("全局异常", null, null);
    }*/

}

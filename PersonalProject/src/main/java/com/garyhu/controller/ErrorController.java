package com.garyhu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : garyhu
 * @Since : 2018/1/28
 */
@Controller
public class ErrorController extends AbstractErrorController {

    Log log = LogFactory.getLog(ErrorController.class);

    public ErrorController() {
        super(new DefaultErrorAttributes());
    }

    /**
     * 出现错误时直接跳转到指定的错误页面
     * @return
     */
//    @Override
//    @RequestMapping("/error")
//    public String getErrorPath() {
//        System.out.println(111);
//        return "error/error";
//    }

    @RequestMapping("/myerror")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> model =
                Collections.unmodifiableMap(getErrorAttributes(request,false));
        //获取异常，有可能为空
        Throwable cause = getCause(request);
        int status  = (Integer) model.get("status");
        // 错误信息
        String message = (String) model.get("message");
        // 友好提示
        String errorMessage = getErrorMessage(cause);
        log.info(status+","+message,cause);
        response.setStatus(status);
        System.out.println(!isJsonRequest(request));
        if(!isJsonRequest(request)){
            ModelAndView mav = new ModelAndView("error/error");
            mav.addAllObjects(model);
            mav.addObject("errorMessage",errorMessage);
            mav.addObject("cause",cause);
            mav.addObject("status",status);
            return mav;
        }else {
            Map error = new HashMap();
            error.put("success",false);
            error.put("errorMessage",errorMessage);
            error.put("message",message);
            writeJson(response,error);
            return null;
        }
    }

    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "redirect:/myerror";
    }

    protected Throwable getCause(HttpServletRequest request){
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if(error != null){
            // MVC有可能会封装异常成ServletException,需要调用getCause获取真正的异常
            while (error instanceof ServletException && error.getCause() != null){
                error = error.getCause();
            }
        }

        return error;
    }

    protected String getErrorMessage(Throwable ex){
        // ex instanceof Exception根据不同异常类型返回不同的错误提示
        return "服务器异常，请检查你的服务器";
    }

    protected boolean isJsonRequest(HttpServletRequest request){
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if(requestUri != null && requestUri.endsWith(".json")){
            return true;
        }else {
            if(request.getHeader("Accept").contains("application/json")){
                return true;
            }else {
                return false;
            }
        }
    }

    protected void writeJson(HttpServletResponse response,Map map){
        try {
            response.getWriter().print(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

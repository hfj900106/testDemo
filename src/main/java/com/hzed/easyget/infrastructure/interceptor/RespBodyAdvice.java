package com.hzed.easyget.infrastructure.interceptor;

import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;

/**
 * 拦截返回并封装成 com.hzed.easyget.infrastructure.model.Response
 * @author guichang
 * @date 2018/6/18
 */
@ControllerAdvice
public class RespBodyAdvice implements ResponseBodyAdvice<Object> {

	@PostConstruct
	public void init() throws Exception {
	}

	/**
	 * 判断是否支持转换
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
		ModuleFunc moduleFunc = methodParameter.getMethodAnnotation(ModuleFunc.class);
		if(moduleFunc != null && moduleFunc.isCommonResponse()) {
			return true;
		}

		return true;
	}

	/**
	 * 返回结果转换
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			org.springframework.http.MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		System.out.println(body);
		return body;
	}

}

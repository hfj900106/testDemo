package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import java.io.UnsupportedEncodingException;

/**
 * rabbitmq 消息json转换工具类
 * <p>Title: FastJsonMessageConverter</p>
 * @author  madaijun
 * @date    2018年6月14日 上午9:03:14
 */
public class FastJsonMessageConverter extends AbstractJsonMessageConverter {

	private static Log log = LogFactory.getLog(FastJsonMessageConverter.class);

    public static final String DEFAULT_CHARSET = "UTF-8";

	private volatile String defaultCharset = DEFAULT_CHARSET;
	
	public FastJsonMessageConverter() {
	}

	@Override
	public void setDefaultCharset(String defaultCharset) {
		this.defaultCharset = (defaultCharset != null) ? defaultCharset : DEFAULT_CHARSET;
	}

	@Override
	protected Message createMessage(Object object, MessageProperties messageProperties) {
		byte[] bytes = null;
		try {
			String jsonString = JSONObject.toJSONString(object);
			bytes = jsonString.getBytes(this.defaultCharset);
		} catch (UnsupportedEncodingException e) {
			throw new MessageConversionException("Failed to convert Message content", e);
		} 
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(this.defaultCharset);
		if (bytes != null) {
			messageProperties.setContentLength(bytes.length);
		}
		return new Message(bytes, messageProperties);
	}
	
	@Override
	public Object fromMessage(Message message) throws MessageConversionException {
		return message;
	}
	
	public <T> T fromMessage(Message message, Class<T> t) throws MessageConversionException {
		String json = "";
		try {
			json = new String(message.getBody(), defaultCharset);
			log.info("MQ消息：" + json);
			return JSONObject.parseObject(json, t);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	

}
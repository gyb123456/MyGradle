package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc	//启用Spring MVC组件
@ComponentScan("com.sys.ctrl")	//启用组件扫描
public class WebConfig extends WebMvcConfigurerAdapter{
	
	//配置JSP视图解析器
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/JSP/");
		resolver.setSuffix(".jsp");
		//可以在JSP页面中通过${}访问beans
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	//配置静态资源的处理
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();//把针对静态资源的请求转交给servlert容器的default servlet处理
	}

	/**
	 * Jackson配置
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//		MediaType mediaType1 = new MediaType("application\\json");
//		MediaType mediaType2 = new MediaType("charset=UTF-8");
//		supportedMediaTypes.add(mediaType1);
//		supportedMediaTypes.add(mediaType2);
		
		supportedMediaTypes = MediaType.parseMediaTypes("application/json; charset=utf-8");
		
		converter.setSupportedMediaTypes(supportedMediaTypes );
        return converter;
    }
	/*//如何在Spring MVC中统一对返回的Json进行加密？http://www.tuicool.com/articles/E3I3qyi
	 * @Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    return new MappingJackson2HttpMessageConverter() {
	        //重写writeInternal方法，在返回内容前首先进行加密
	        @Override
	        protected void writeInternal(Object object,
	                                     HttpOutputMessage outputMessage) throws IOException,
	                HttpMessageNotWritableException {
	            //使用Jackson的ObjectMapper将Java对象转换成Json String
	            ObjectMapper mapper = new ObjectMapper();
	            String json = mapper.writeValueAsString(object);
	            LOGGER.error(json);
	            //加密
	            String result = json + "加密了！";
	            LOGGER.error(result);
	            //输出
	            outputMessage.getBody().write(result.getBytes());
	        }
	    };
	}*/
	/**
	 * Jackson配置
	 * 添加自定义转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
}

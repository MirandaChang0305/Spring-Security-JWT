package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${swagger.enable}")
	private boolean enable;
	
	@Bean
	public Docket customDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(enable)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder()
						.description("用戶於登入系統請求成功後，Server 產生 JWT token 回傳給客戶端，且之後的CRUD Request 都需帶入 Token 取得 Response Data")
						.title("demo1022")
						.build())
				.globalOperationParameters(this.getGlobalParamList());
				
	}
	
	private List<Parameter> getGlobalParamList(){
		ParameterBuilder token = new ParameterBuilder();
		token.name("Authorization")
		.modelRef(new ModelRef("string"))
		.parameterType("Header");
		List<Parameter> globalParamList = new ArrayList<Parameter>();
		globalParamList.add(token.build());
		return globalParamList;
	}

}

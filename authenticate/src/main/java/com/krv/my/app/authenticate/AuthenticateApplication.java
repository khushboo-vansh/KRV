package com.krv.my.app.authenticate;

import java.io.PrintStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class AuthenticateApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AuthenticateApplication.class);
		app.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.print("\n" + "MY APP is Starting" + "".toUpperCase());
			}
		});
		app.run(args);
	}

	/**
	 * Overidding the configure of SpringBootServletInitializer class
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AuthenticateApplication.class);
	}

	/**
	 * To allow the multipart upload for PUT and POST methods
	 *
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver() {
			@Override
			public boolean isMultipart(HttpServletRequest request) {
				String method = request.getMethod().toLowerCase();
				if (!Arrays.asList("put", "post").contains(method)) {
					return false;
				}
				String contentType = request.getContentType();
				return ((contentType != null) && contentType.toLowerCase().startsWith("multipart/"));
			}
		};
	}

}

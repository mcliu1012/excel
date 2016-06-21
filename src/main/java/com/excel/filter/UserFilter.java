package com.excel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserFilter implements Filter {
	
	private FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String maxCount = config.getInitParameter("maxCount");
		System.out.println("maxCount:" + maxCount);
		ServletContext context = config.getServletContext();
		System.out.println("context.getContextPath():" + context.getContextPath());
		chain.doFilter(request, response);
		System.out.println("执行结束了！！！");
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}

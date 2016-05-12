package org.hu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	public void destroy() {	
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.setCharacterEncoding("GBK");  //±‡¬Î–¥À¿Œ™utf-8	
		response.setContentType("text/html;charset=GBK");
		response.setCharacterEncoding("GBK");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {	
	}
	
}

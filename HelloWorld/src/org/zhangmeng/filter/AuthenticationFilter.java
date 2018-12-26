package org.zhangmeng.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.DispatcherType;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(
		filterName = "/AuthenticationFilter",
		urlPatterns = {"/newpost.jsp", "/editpost.jsp", "/userprofile.jsp"},  // 涓嶈兘缂哄皯/瀛楃
		servletNames = {
			"org.zhangmeng.servlet.EditPostServlet", 
			"org.zhangmeng.servlet.NewPostServlet", 
			"org.zhangmeng.servlet.NewCommentServlet", 
			"org.zhangmeng.servlet.RemoveCommentServlet",
			"org.zhangmeng.servlet.RemovePostServlet", 
			"org.zhangmeng.servlet.UpdateBasicInfoServlet", 
			"org.zhangmeng.servlet.UpdateAvatarServlet", 
			"org.zhangmeng.servlet.LogoutServlet"},
		dispatcherTypes = {DispatcherType.REQUEST}
		)
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String queryString = req.getQueryString();
		System.out.println(req.getRequestURL() + (null!=queryString ? "?" + queryString : ""));
		if (req.getSession(true).getAttribute("User") == null) {
			// 鏈櫥褰�
			//res.sendRedirect("signin.jsp");
			req.setAttribute("tip", "You have not signed in!");
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else {	
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

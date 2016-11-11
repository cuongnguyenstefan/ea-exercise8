package cs544.exercise8_2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet Filter implementation class OpenSessionInViewFilter
 */
public class OpenSessionInViewFilter implements Filter {
	
	private SessionFactory sf;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO implement actual session in view filter code
		Transaction tx = null;
		try {
//			tx = sf.getCurrentSession().beginTransaction();
			// pass the request along the filter chain
			System.out.println("receiving request");
			chain.doFilter(request, response);
			System.out.println("sending response");
//			tx.commit();
		} catch (RuntimeException e) {
			try {
				e.printStackTrace();
				tx.rollback();
			} catch (RuntimeException re) {
				System.out.println("can not rollback");
			}
			throw e;
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
//		ServletContext context = arg0.getServletContext();
//		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//		sf = applicationContext.getBean("sf", SessionFactory.class);
	}
}

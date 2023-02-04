package filter;

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

@WebFilter("/*")
public class I18nFilter implements Filter {
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		String lang = req.getParameter("lang");
		System.out.println("Filter an chua?" + lang);
		HttpServletRequest r = (HttpServletRequest) req;

		if (lang != null) {
			r.getSession().setAttribute("lang", lang);
		}
		chain.doFilter(req, resp);
		
	}
}
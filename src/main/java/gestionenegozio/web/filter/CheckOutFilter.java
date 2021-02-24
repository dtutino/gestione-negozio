package gestionenegozio.web.filter;

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

import gestionenegozio.model.Utente;

@WebFilter(filterName = "CheckOutFilter", urlPatterns = { "/*" })
public class CheckOutFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/"};
	private static final String[] PROTECTED_URLS = {"/ExecuteListUtenteServlet","/PrepareInsertUtenteServlet"};
	
    public CheckOutFilter() {
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String pathAttuale = httpRequest.getServletPath();
		
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente)httpRequest.getSession().getAttribute("userInfo");
			
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
			
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("errorMessage", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}

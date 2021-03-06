/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Filters;

import Domain.UserRole;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * A filter to be used by servlets that only allow warehouse people in
 */
@WebFilter(urlPatterns = "/warehouse")
public class WarehouseUserOnlyFilter extends BaseFilter
{
    private ServletContext context;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        // If the user is not a warehouse person, redirect to home page
        if (!this.currentUser.getRole().equals(UserRole.WarehousePerson))
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/");
        }
        
        // Pass the request to other filters or the servlet
        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
        super.destroy();
    }
}
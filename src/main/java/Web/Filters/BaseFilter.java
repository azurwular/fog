/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.Filters;

import Web.DTO.SessionKeys;
import Web.DTO.UserSessionDto;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author azurwular
 */
public class BaseFilter implements Filter
{
    public UserSessionDto currentUser;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        // Get session from request
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        // Get the current user from the session
        this.currentUser = (UserSessionDto) session.getAttribute(SessionKeys.user);
        
        if (this.currentUser == null)
        {
            this.currentUser = new UserSessionDto();
        }
    }

    @Override
    public void destroy()
    {
    }
}

package br.com.respawntimer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.respawntimer.model.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();
	
		if(uri.endsWith("/respawn") && request.getSession().getAttribute("usuario") != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			response.sendRedirect("respawn/" + usuario.getNick());
			
			return true;
		}
		
		if(uri.endsWith("/respawn/") && request.getSession().getAttribute("usuario") != null){
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			response.sendRedirect(usuario.getNick());
			
			return true;
		}
		
		if(uri.endsWith("/registraRespawn") && request.getSession().getAttribute("usuario") == null){
			response.setStatus(401);
			
			return false;
		}
		
		response.setStatus(200);
		
		return true;
	}
}
package br.com.respawntimer.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import br.com.respawntimer.dao.UsuarioDAO;
import br.com.respawntimer.model.Usuario;

@Service
public class LoginService {
	
	@Autowired
	HttpSession session;

	public boolean conectar(Usuario usuario) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/spring-persistence.xml");
		
		UsuarioDAO dao = (UsuarioDAO) context.getBean("usuarioDAO");
		
		Usuario user = dao.existeUsuario(usuario.getEmail());
		
		if(user != null) {
							
			if(usuario.getSenha().equals(user.getSenha())) {
								
				session.setAttribute("usuario", user);
				
				return true;
				
			}
						
		}
		
		return false;
	}
	
	public void desconectar() {

		session.invalidate();
		
	}
}
package br.com.respawntimer.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.respawntimer.dao.UsuarioDAO;
import br.com.respawntimer.model.Usuario;
import br.com.respawntimer.services.CadastroValidator;
import br.com.respawntimer.services.LoginService;
import br.com.respawntimer.services.SenhaBuilder;

@Controller
public class UsuarioController  {
	
	@Autowired
	LoginService login;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("/config/spring-persistence.xml");
	
	UsuarioDAO daoUsuario = (UsuarioDAO) context.getBean("usuarioDAO");

	@RequestMapping(value="/registraUsuario", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> registra(Usuario usuario, HttpSession session, HttpServletResponse response, BindingResult result, CadastroValidator usuarioValidator) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		usuarioValidator.validate(usuario, result);
		
		if(result.hasErrors()) {
			List<String> list = new ArrayList<String>();
			
			for(DefaultMessageSourceResolvable errors: result.getAllErrors()) {
				
				list.add(errors.getDefaultMessage());
				
			}
			
			response.setStatus(200);
			
			map.put("errors", list);
				
			return map;
		}
		
		try {
			
			SenhaBuilder sb = new SenhaBuilder(usuario.getSenha()); 
						
			usuario.setSenha(sb.criptografar());
			
			if(daoUsuario.adiciona(usuario)) {
				
				if(login.conectar(usuario)) {
					
					response.setStatus(200);
					
					map.put("nick", ((Usuario) session.getAttribute("usuario")).getNick());
					
					return map;
					
				}
				
			}
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
				
		return null;
		
	}
}
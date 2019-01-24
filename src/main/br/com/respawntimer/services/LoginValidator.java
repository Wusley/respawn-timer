package br.com.respawntimer.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.respawntimer.dao.UsuarioDAO;
import br.com.respawntimer.model.Usuario;

@Service
public class LoginValidator implements Validator {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"/config/spring-persistence.xml");

	UsuarioDAO daoUsuario = (UsuarioDAO) context.getBean("usuarioDAO");

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {

		return clazz.equals(Usuario.class);

	}

	@Override
	public void validate(Object obj, Errors errors) {

		Usuario usuario = (Usuario) obj;

		validateEmail(usuario.getEmail(), usuario.getEmailRepeat(), errors);
		validateSenha(usuario.getSenha(), usuario.getSenhaRepeat(), errors);
	}

	private void validateEmail(String email, String emailRepeat, Errors errors) {

		Pattern modelo = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");

		Matcher result = modelo.matcher(email);

		if (email.length() == 0) {

			errors.rejectValue("email", "EMAIL_BRANCO", "E-mail: Em branco.");

		} else if (!result.find()) {

			errors.rejectValue("email", "EMAIL_INVALIDO",
					"E-mail: Fora do padr&atilde;o de e-mail.");

		}

	}

	private void validateSenha(String senha, String senhaRepeat, Errors errors) {

		if (senha.length() == 0) {

			errors.rejectValue("senha", "SENHA_BRANCO", "Senha: Em branco.");

		} else if (senha.length() < 6) {

			errors.rejectValue("senha", "SENHA_PEQUENA",
					"Senha: Menor que seis caracteres.");

		}

	}

}
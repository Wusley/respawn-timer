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
public class CadastroValidator implements Validator {

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

		validateNick(usuario.getNick(), errors);
		validateEmail(usuario.getEmail(), usuario.getEmailRepeat(), errors);
		validateSenha(usuario.getSenha(), usuario.getSenhaRepeat(), errors);
	}

	private void validateNick(String nick, Errors errors) {

		Pattern modelo = Pattern
				.compile("^[a-zA-Z_0-9]+([\\s]?[a-zA-Z_0-9])*$");

		Matcher result = modelo.matcher(nick);

		if (nick.length() == 0) {

			errors.rejectValue("nick", "NICK_BRANCO", "Nick: Em branco.");

		} else if (!result.find()) {

			errors.rejectValue("nick", "NICK_INVALIDO",
					"Nick: Caracteres inv&aacute;lidos.");

		} else if (nick.length() < 3) {

			errors.rejectValue("nick", "NICK_PEQUENO",
					"Nick: Menor que tr&ecirc;s caracteres.");

		} else if (daoUsuario.existeNick(nick)) {

			errors.rejectValue("nick", "NICK_EXISTENTE",
					"Nick: J&aacute; existente.");

		}

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

		} else if (!email.equals(emailRepeat)) {

			errors.rejectValue("emailRepeat", "EMAIL_REPEAT_DIFERENTE",
					"E-mail: N&atilde;o repetido corretamente.");

		} else if (daoUsuario.existeEmail(email)) {

			errors.rejectValue("email", "EMAIL_EXISTENTE",
					"E-mail: J&aacute; existente.");

		}

	}

	private void validateSenha(String senha, String senhaRepeat, Errors errors) {

		if (senha.length() == 0) {

			errors.rejectValue("senha", "SENHA_BRANCO", "Senha: Em branco.");

		} else if (senha.length() < 6) {

			errors.rejectValue("senha", "SENHA_PEQUENA",
					"Senha: Menor que seis caracteres.");

		} else if (!senha.equals(senhaRepeat)) {

			errors.rejectValue("senhaRepeat", "SENHA_REPEAT_DIFERENTE",
					"Senha: N&atilde;o repetida corretamente.");

		}

	}

}
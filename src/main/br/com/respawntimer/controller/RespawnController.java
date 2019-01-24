package br.com.respawntimer.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.respawntimer.dao.RespawnDAO;
import br.com.respawntimer.model.Respawn;
import br.com.respawntimer.model.Usuario;
import br.com.respawntimer.services.RespawnService;
import br.com.respawntimer.services.RespawnValidator;

@Controller
@RequestMapping("/respawn")
public class RespawnController {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"/config/spring-persistence.xml");

	RespawnDAO daoRespawn = (RespawnDAO) context.getBean("respawnDAO");

	@RequestMapping(method = RequestMethod.GET)
	public String pageAnonima(HttpServletResponse response, Model model) {
		model.addAttribute("link", "respawn");

		return "respawn";
	}

	@RequestMapping(value = "/{nick}", method = RequestMethod.GET)
	public String pageIdentificada(@PathVariable String nick, Model model) {
		model.addAttribute("link", "respawn");

		return "respawn";
	}

	@RequestMapping(value = "/{nick}", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, List<Respawn>> lista(@PathVariable String nick,
			HttpSession session, HttpServletResponse response, Model model) {
				
		if (session.getAttribute("usuario") != null) {
			response.setStatus(200);
			
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			if (nick.equals(usuario.getNick())) {
				
				RespawnService respawnService = new RespawnService();
				
				Map<String, List<Respawn>> map = new HashMap<String, List<Respawn>>();
								
				List<Respawn> listRespawn = daoRespawn.listaRespawnTo(usuario);
								
				List<Respawn> list = respawnService.lista(listRespawn);
				
				map.put("list", list);
				
				return map;
			}

			return null;
		}
		
		response.setStatus(401);

		return null;
	}

	@RequestMapping(value = "/registraRespawn", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> registra(Respawn respawn, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			BindingResult result, RespawnValidator respawnValidator)
			throws ParseException, NumberFormatException {

		Map<String, Object> map = new HashMap<String, Object>();

		respawn.setNome(request.getParameter("nome"));
		respawn.setDataMorte(Long.parseLong(request.getParameter("dataMorte")));
		respawn.setDias(Long.parseLong(request.getParameter("dias")));
		respawn.setTempoNascimento(Long.parseLong(request
				.getParameter("tempoNascimento")));
		respawn.setUsuario((Usuario) session.getAttribute("usuario"));

		respawnValidator.validate(respawn, result);

		if (result.hasErrors()) {
			List<String> list = new ArrayList<String>();

			for (DefaultMessageSourceResolvable errors : result.getAllErrors()) {

				list.add(errors.getDefaultMessage());

			}

			response.setStatus(200);

			map.put("errors", list);

			return map;
		}

		RespawnService respawnService = new RespawnService();

		respawn = respawnService.tempoNascimentoBuilder(respawn);

		if (daoRespawn.adiciona(respawn)) {

			response.setStatus(200);

		} else {

			response.setStatus(304);

		}

		respawn.setUsuario(null);

		map.put("respawn", respawn);

		return map;
	}

	@RequestMapping(value = "/finalizaRespawn", method = RequestMethod.POST)
	public @ResponseBody
	Respawn finaliza(HttpServletRequest request, HttpServletResponse response) {

		Long id = Long.parseLong(request.getParameter("id"));

		Respawn respawn = daoRespawn.busca(id);

		RespawnService respawnService = new RespawnService();

		respawn = respawnService
				.finaliza(respawn, request.getParameter("acao"));

		daoRespawn.atualiza(respawn);

		respawn.setUsuario(null);

		response.setStatus(200);

		return respawn;
	}
}
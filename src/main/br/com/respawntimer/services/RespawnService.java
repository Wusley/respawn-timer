package br.com.respawntimer.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.respawntimer.model.Respawn;

@Service
public class RespawnService {

	public RespawnService() {
	}

	public Respawn tempoNascimentoBuilder(Respawn respawn) {
		Long tempoNascimento = respawn.getDias() + respawn.getTempoNascimento();

		Calendar calendar = Calendar.getInstance();

		if ((tempoNascimento + respawn.getDataMorte()) <= calendar
				.getTimeInMillis()) {

			throw new IllegalArgumentException(
					"Data de nascimento nao pode ser menor que a data atual.");

		}

		respawn.setTempoNascimento(tempoNascimento);

		return respawn;
	}

	public Respawn finaliza(Respawn respawn, String acao) {
		Calendar now = Calendar.getInstance();

		if (respawn != null) {

			Long nascimento = respawn.getDataMorte()
					+ respawn.getTempoNascimento();

			if (acao.equals("sucesso")) {

				if (now.getTimeInMillis() >= nascimento) {

					respawn.setSucesso(1);
					respawn.setStatus(false);

				}

			} else if (acao.equals("falha")) {

				if (now.getTimeInMillis() >= nascimento) {

					respawn.setSucesso(0);
					respawn.setStatus(false);

				}

			} else if (acao.equals("delete")) {

				if (now.getTimeInMillis() < nascimento) {

					respawn.setStatus(false);

				}

			}

		}

		return respawn;
	}

	public List<Respawn> lista(List<Respawn> list) {

		List<Respawn> newList = new ArrayList<Respawn>();

		for (Respawn respawn : list) {

			if (respawn != null) {

				respawn.setUsuario(null);
				
				newList.add(respawn);
				
			}
		}

		if (newList.size() > 0) {
			
			return newList;

		}

		return null;
	}
}
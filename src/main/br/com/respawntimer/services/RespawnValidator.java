package br.com.respawntimer.services;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.respawntimer.model.Respawn;

@Service
public class RespawnValidator implements Validator {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {

		return clazz.equals(Respawn.class);

	}

	@Override
	public void validate(Object obj, Errors errors) {

		Respawn respawn = (Respawn) obj;

		validateNome(respawn.getNome(), errors);
		validateDataMorte(respawn.getDataMorte(),
				respawn.getDias() + respawn.getTempoNascimento(), errors);
		validateDias(respawn.getDias(), errors);
		validateTempoNascimento(respawn.getTempoNascimento(),
				respawn.getDias(), errors);

	}

	private void validateNome(String nome, Errors errors) {
		Pattern modelo = Pattern
				.compile("^[a-zA-Z_0-9]+([\\s]?[a-zA-Z_0-9])*$");

		Matcher result = modelo.matcher(nome);

		if (!result.find()) {

			errors.rejectValue("nome", "NOME_INVALIDO",
					"Nome: Caracteres inv&aacute;lidos.");

		} else if (nome.length() < 3) {

			errors.rejectValue("nome", "NOME_PEQUENO",
					"Nome: Menor que tr&ecirc;s caracteres.");

		}
	}

	private void validateDataMorte(Long dataMorte, Long tempoNascimento,
			Errors errors) {
		Calendar calendar = Calendar.getInstance();

		if (dataMorte < 0) {

			errors.rejectValue("dataMorte", "DATA_MORTE_INVALIDO",
					"Quando morreu: Inv&aacute;lido.");

		} else if (dataMorte > calendar.getTimeInMillis()) {

			errors.rejectValue("dataMorte", "DATA_MORTE_ERROR",
					"Quando morreu: N&atilde;o pode ter acontecido depois da data atual.");

		} else if ((tempoNascimento + dataMorte) <= calendar.getTimeInMillis()) {

			errors.rejectValue(
					"dataMorte",
					"DATA_MORTE_ERROR",
					"Quando morreu: Data da morte n&atilde;o pode ter ocorrido depois do nascimento.");

		}
	}

	private void validateDias(Long dias, Errors errors) {
		Long maxDias = dias * 86400000;
		
		if (dias < 0 || dias > maxDias) {

			errors.rejectValue("dias", "DIAS_INVALIDO",
					"Intervalo de nascimento: Dias inv&aacute;lidos.");

		}

	}

	private void validateTempoNascimento(Long tempoNascimento, Long dias,
			Errors errors) {

		if (tempoNascimento < 0 || tempoNascimento > 86400000) {

			errors.rejectValue(
					"tempoNascimento",
					"TEMPO_NASCIMENTO_INVALIDO",
					"Intervalo de nascimento: Hora n&atilde;o pode estar abaixo de 00:00 ou acima de 23:59.");

		} else if (tempoNascimento == 0 && dias == 0) {

			errors.rejectValue("tempoNascimento", "TEMPO_NASCIMENTO_INVALIDO",
					"Intervalo de nascimento: Nulo ou zero.");

		}

	}
}
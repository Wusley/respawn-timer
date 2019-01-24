var respawns = {};
var ping;

var polimorfRespawnFactory = function(data, call) {
	clearTimeout(ping);
	
	var count = 0;
	for(id in respawns) {
		count++;
	}
	
	if(count <= 0) {		
		$(".lista-respawn ul").html("");
	}
	
	var modelo = $(".box-lista-respawn .modelo").html();
	
	switch(call) {
		case 'one': new respawnFactory(data, modelo);
		break;
		
		case 'many': new listRespawnFactory(data, modelo);
		break;
	}
	
	// efeito regressivo
	ping = window.setInterval('regressivo()',1000);
};

var respawnFactory = function(data, modelo) {
	var nascimento = new Nascimento();
	
	$(".lista-respawn ul").append(modelo);
	
	if(data !== null && data !== undefined && data !== "") {
		
		$(".lista-respawn #respawn").attr("id",data.id).find("input[name='id']").val(data.id);
		$("#"+data.id).find(".nome").text(data.nome);
		$("#"+data.id).find(".tempoNascimento").text(formataTempoNascimento(nascimento.tempoNascimento(data.tempoNascimento)));
		$("#"+data.id).find(".nascimento").text(formataDataNascimento(nascimento.dataNascimento(data.dataMorte, data.tempoNascimento)));
		$("#"+data.id).find("input[name='delete']").attr('disabled',false);
				
		respawns[data.id] = { 
			'id'				: data.id,
			'dataNascimento'	: nascimento.dataNascimento(data.dataMorte, data.tempoNascimento)
		};
	}
};

var listRespawnFactory = function(data, modelo) {
	$(".lista-respawn ul").html("");
	
	for(id in data) {		
		new respawnFactory(data[id], modelo);
	}
};

var regressivo = function() {
	var agora = new Date(); //now

	var count = 0;
	for(id in respawns) {
		count++;
		
		if(!breakRespawn(respawns[id])) {
			var segundo	= parseInt(((respawns[id].dataNascimento - agora) + 60000) / 1000); //total de segundos que faltam
			var minuto	= parseInt(segundo / 60); //total de minutos que faltam
			var hora	= parseInt(minuto / 60); //total de horas que faltam
			var dia		= parseInt(hora / 24); //total de dias que faltam
			 
			//segundo	= segundo - (minuto * 60); //quantidade de segundos
			minuto	= minuto - (hora * 60); //quantidade de minutos
			hora	= hora - (dia * 24); //quantidade de horas
			 
			//formata para tela
			if(hora <= 9) {
				hora = '0' + hora;
			}
			
			if(minuto <= 9) {
				minuto = '0' + minuto;
			}
			
			var formato = '';
			formato += (dia && dia > 1) ? dia+' dias, ' : (dia === 1 ? '1 dia, ' : '');
			formato += (toString(hora).length) ? hora+':' : '';
			formato += (toString(minuto).length) ? minuto : '';
			 
			$("#"+respawns[id].id).find(".regressiva").text(formato);
		}
	}
	
	if(count <= 0) {
		clearTimeout(ping);
		
		respawnDefault();
	}
	
};

var respawnDefault = function () {
		var count = 0;
		for(id in respawns) {
			count++;
		}
		
		if(count <= 0) {
			var modelo = $(".box-lista-respawn .modelo").html();
			
			$(".lista-respawn ul").html(modelo);
		}
		
	};
	
var finalizaRespawn = function(data, that) {
		switch(data.status) {
			case true:
				
				switch(that.attr("name")) {
					case "sucesso":
						new insertError("N&atilde;o foi poss&iacute;vel declarar sucesso no respawn, pois ainda n&atilde;o ocorreu o respawn.");
					break;
					
					case "falha":
						new insertError("N&atilde;o foi poss&iacute;vel declarar falha no respawn, pois ainda n&atilde;o ocorreu o respawn.");
					break;
					
					case "delete":
						new insertError("N&atilde;o foi poss&iacute;vel cancelar o respawn, pois o tempo de nascimento j&iacute; ocorreu e deve ser declarado sucesso ou falha.");
					break;
				}
			
				new efeitoTransicao(".erro");
				
			break;
			
			case false: 
				that.parents("li").remove();
				delete respawns[data.id];
				new respawnDefault();
				
				switch(data.sucesso) {
					case 0:
						new insertSuccess("Respawn perdido.");
					break;
					
					case 1:
						new insertSuccess("Respawn conclu&iacute;do.");
					break;
					
					case 2:
						new insertSuccess("Respawn cancelado.");
					break;
				}
				
				new efeitoTransicao(".sucesso");
				
			break;
		}
	};
	
var breakRespawn = function(data) {
		var agora = new Date().getTime(); //now
		
		if(agora >= data.dataNascimento.getTime()) {
			$("#"+data.id).parents("li").addClass("active");
			$("#"+data.id).find(".regressiva").text("00:00");
			$("#"+data.id).find("input[type='button']").attr('disabled',false);
			$("#"+data.id).find("input[name='delete']").attr('disabled',true);
			
			return true;
		}
		
		return false;
	};

var formataTempoNascimento = function(tempoNascimento) {
		var segundo	= parseInt(tempoNascimento / 1000); //total de segundos que faltam
		var minuto	= parseInt(segundo / 60); //total de minutos que faltam
		var hora	= parseInt(minuto / 60); //total de horas que faltam
		var dia		= parseInt(hora / 24); //total de dias que faltam
		 
		//segundo	= segundo - (minuto * 60); //quantidade de segundos
		minuto	= minuto - (hora * 60); //quantidade de minutos
		hora	= hora - (dia * 24); //quantidade de horas
		
		//formata para tela
		if(hora <= 9) {
			hora = '0' + hora;
		}
		
		if(minuto <= 9) {
			minuto = '0' + minuto;
		}
		
		var formato = '';
		formato += (dia && dia > 1) ? dia+' dias, ' : (dia==1 ? '1 dia, ' : '');
		formato += (toString(hora).length) ? hora+':' : '';
		formato += (toString(minuto).length) ? minuto : '';
	 
		return formato;
	};
	
var formataDataNascimento = function(dataNascimento) {
		var ano = dataNascimento.getFullYear();
		var mes = dataNascimento.getMonth()+1;
		var dia = dataNascimento.getDate();
		var hora = dataNascimento.getHours();
		var minuto = dataNascimento.getMinutes();
		
		//formata para tela
		if(dia <= 9) {
			dia = '0' + dia;
		}
		
		if(mes <= 9) {
			mes = '0' + mes;
		}
		
		if(hora <= 9) {
			hora = '0' + hora;
		}
		
		if(minuto <= 9) {
			minuto = '0' + minuto;
		}
		
		var formato = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto;
		
		return formato;
	};

var Nascimento = function() {
	
		return {
			tempoNascimento: function(tempoNascimento) {
				return new Date(tempoNascimento);
			},
		
			dataNascimento: function(dataMorte, tempoNascimento) {
				return new Date(dataMorte + tempoNascimento);
			}
		};
		
	};

// METODO PARA CRIAR CALENDARIO
var calendario = function(data) {
		$(data).datetimepicker({
			//maxDate: 0
		});
	},
	
// METODO PARA CRIAR RELOGIO
	relogio = function(data) {
		$(data).timepicker();
	};
	
var fixedRespawn = function() {
		var forms = $(".formularios-respawn ul").find("li").size();
		
		if(forms <= 0) {
			new addFormRespawn();
		}
	};
	
var addFormRespawn = function() {
		var formRespawn = $(".box-formularios-respawn .modelo").html();
		
		$(".formularios-respawn ul").append(formRespawn);
		
		$(".formularios-respawn ul").find("li:last").find("input[name='dataMorte']").trigger("click", function() {
			$(this).attr('id','calendario-on');
			
			new calendario("#calendario-on");
		});
		
		$(".formularios-respawn ul").find("li:last").find("input[name='tempoNascimento']").trigger("click", function() {
			$(this).attr('id','relogio-on');
			
			new relogio("#relogio-on");
		});
	};
	
	//VALIDACAO
var validaDate = function(data) {
	
		if(new Date(data).getTime()) {
			
			return data;
			
		} else {
			
			return -1;
			
		}
		
	};
	
var validaNumero = function(data) {
	
		var er = /\D/g;
	
		if(!er.test(data) && data.length > 0) {
			
			return data;
			
		} else if(data.length === 0) {
			
			return 0;
			
		} else {
			
			return -1;
			
		}
		
	};
	
var insertSuccess = function(data) {
		$(".sucesso h3").html(data);
	};	
	
var insertError = function(data) {
		$(".erro h3").html(data);
	};

var insertListaErrors = function(data) {
	$(".falha .falhas").html("");
	
	for(id in data) {
		var falha = $('<p />').html(data[id]);
		
		$(".falha .falhas").append(falha);
	}
};

var pingTransicao;
var efeitoTransicao = function(path) {
	
		clearTimeout(pingTransicao);
		$(".alertas section article").css({
			"display":"none",
			"height":"auto"
		});
	
		var altura = $(path).height();
		
		$(path).css({
			"display":"block",
			"height":"0px"
		});
		
		$(path).animate({height: altura}, 300);
		
		pingTransicao = window.setTimeout("transicao('" + path + "')",5000);
	};

	
var transicao = function(path) {
	
		$(path).animate({height: "0px"}, 300, function() {
			$(path).css({
				"display":"none",
				"height":"auto"
			});
		});
		
	};
	
var efeitoTransicaoON = function() {
		var path = ".loading";
	
		$(".alertas section article").css({
			"display":"none",
			"height":"auto"
		});

		var altura = $(path).height();
		
		$(path).css({
			"display":"block",
			"height":"0px"
		});
		
		//The following code starts the animation
		new startAnimation();
		
		$(path).animate({height: altura}, 300);
	};

var efeitoTransicaoOFF = function() {
		var path = ".loading";
	
		$(path).animate({height: "0px"}, 300, function() {
			$(path).css({
				"display":"none",
				"height":"auto"
			});
			
			clearTimeout(animation);
		});
		
	};
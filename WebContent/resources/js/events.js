$(".background img").hisrc({
	minwidth : 1280
}); // responsividade nas image

window.onload = function() {
	//background do parallax
	new background(document.body.clientWidth);
	
	// CRIA FORM RESPAWN
	new addFormRespawn();
	
	// PEGA A LISTA DE RESPAWNS
	new getListRespawn();
	
	// posiciona box conexao
	$("section.conexao").css("margin-top", document.body.clientHeight / 2 - 140);
};

$(window).resize(function() {
	// atualizando background parallax
	new background(document.body.clientWidth);
	
	// posiciona box conexao
	$("section.conexao").css("margin-top", document.body.clientHeight / 2 - 140);
});
	
$(window).mousemove(function(e) {
	new parallax(e);
});

// REVELA FORM PARA CONEXAO E REGISTRO
$('.conectar').click(function() {
	
	$('.container-conexao').css({'display':'table'});
	
	$("form[name='form-conexao']").find("input[name='email']").focus();
});

$('section.conexao img.fechar').click(function() {
	$('.container-conexao').css({'display':'none'});
});

// REGISTRAR USUARIO
$("form[name='form-registro']").find("input[type='button']").click(function() {
	new efeitoTransicaoON();
	
	var form = $(this).parents('form'),
		data = form.serialize();
	
	$.ajax({
        type: "POST",
        url: form.attr("action"),
        dataType: "json",
        data: data,
        cache: false,
		statusCode: {
			200: function(data) {
				new efeitoTransicaoOFF();
				
				if(!data.errors) {
					
					$('.container-conexao').css({'display':'none'});
					var url = window.location.toString();
					
					if(url.indexOf("respawn/") > 0) {
					
						window.history.pushState(null, null, data.nick);
					
					} else if(url.indexOf("respawn") > 0) {
					
						window.history.pushState(null, null, "respawn/" + data.nick);
					
					}
					
					$("li.conectar").remove();
					$("li.conectar-hidden").css({"display":"inline"}).find(".nick-conectado").text(data.nick);
					
					new insertSuccess("Sucesso ao realizar o cadastro.");
					
					new efeitoTransicao(".sucesso");
				
				} else {
					
					new insertListaErrors(data.errors);
					
					new efeitoTransicao(".falha");
				}
			
			}
		}
    });	
});

// CONECTAR USUARIO
$("form[name='form-conexao']").find("input[type='button']").click(function() {
	new efeitoTransicaoON();
	
	var form = $(this).parents('form'),
		data = form.serialize();
	
	$.ajax({
        type: "POST",
        url: form.attr("action"),
        dataType: "json",
        data: data,
        cache: false,
		statusCode: {
			401: function() {
				$('.container-conexao').css({'display':'block'});
			},
			200: function(data) {
				new efeitoTransicaoOFF();
				
				if(data === null || data === undefined || data === "") {
					
					new insertError("Senha incorreta.");
					
					new efeitoTransicao(".erro");
					
				} else if(!data.errors) {
					
					$('.container-conexao').css({'display':'none'});
					var url = window.location.toString();
					
					if(url.indexOf("respawn/") > 0) {
						window.history.pushState(null, null, data.nick);
					} else if(url.indexOf("respawn") > 0) {
						window.history.pushState(null, null, "respawn/" + data.nick);
					}
					
					$("li.conectar").remove();
					$("li.conectar-hidden").css({"display":"inline"}).find(".nick-conectado").text(data.nick);
					
					// PEGA A LISTA DE RESPAWNS
					new getListRespawn();
					
					new insertSuccess("Conectado com sucesso.");
					
					new efeitoTransicao(".sucesso");
				
				} else if(data.errors) {
					
					new insertListaErrors(data.errors);
					
					new efeitoTransicao(".falha");
						
				}
				
			}
		}
    });
});

// REMOVER FORM-RESPAWN
$("form[name='form-respawn']").find("input[name='remover']").die();
$("form[name='form-respawn']").find("input[name='remover']").live("click", function() {
	
	$(this).parents("li").remove();
	
	new fixedRespawn();
	
});

// ADICIONAR NOVO FORM-RESPAWN
$(".novo-formulario-respawn").click(function() {	
	new addFormRespawn();
});

// REGISTRAR RESPAWN
$("form[name='form-respawn']").find("input[name='iniciar']").die();
$("form[name='form-respawn']").find("input[name='iniciar']").live("click",function() {
	new efeitoTransicaoON();
	
	var that = $(this);
	var form = that.parents('form');
	
	var dataMorte = validaDate(form.find("input[name='dataMorte']").val());
	var tempoNascimento = validaDate("1970/1/1 " + form.find("input[name='tempoNascimento']").val() + ":0");
	var dias = validaNumero(form.find("input[name='dias']").val());
	
	var dataMorte = Date.parse(new Date(dataMorte));
	
	var GMTNascimento = new Date(tempoNascimento).getTimezoneOffset() * 60000;
	var tempoNascimento = Date.parse(new Date(tempoNascimento)) - GMTNascimento;
	
	$.ajax({
        type: "POST",
        url: form.attr("action"),
        dataType: "json",
	    data: {
	    	'nome'				: form.find("input[name='nome']").val(),
	    	'dataMorte'			: dataMorte,
	    	'dias'				: dias,
	    	'tempoNascimento'	: tempoNascimento
	    },
        cache: false,
		statusCode: {
			401: function() {
				new efeitoTransicaoOFF();
				
				$('.container-conexao').css({'display':'block'});
				
				$("form[name='form-conexao']").find("input[name='email']").focus();
			},
			200: function(data) {
				new efeitoTransicaoOFF();
				
				if(!data.errors) {
				
					that.parents("li").remove();
					
					new fixedRespawn();
					
					// fabricao dos respawns
					new polimorfRespawnFactory(data.respawn, 'one');
					
					new insertSuccess("Sucesso ao registrar o respawn.");
					
					new efeitoTransicao(".sucesso");
				
				} else {
					
					new insertListaErrors(data.errors);
					
					new efeitoTransicao(".falha");
				}
			}
		}
    });
});

//EVENTO CALENDARIO ON
$("input[name='dataMorte']").die();
$("input[name='dataMorte']").live('click', function() {

	$("form[name='form-respawn']").each(function() {
		$(this).find("input[name='dataMorte']").attr('id','calendario-off');
	});

	$(this).attr('id','calendario-on');

	new calendario("#calendario-on");
	
});

//EVENTO RELOGIO ON
$("input[name='tempoNascimento']").die();
$("input[name='tempoNascimento']").live('click', function() {

	$("form[name='form-respawn']").each(function() {
		$(this).find("input[name='tempoNascimento']").attr('id','relogio-off');
	});

	$(this).attr('id','relogio-on');

	new relogio("#relogio-on");
	
});


var getListRespawn = function() {
		var url = window.location.toString().split("/");
		var posicao = url.indexOf("respawn") + 1;
				
		if(posicao > 1 && posicao < url.length && url[posicao] != "" && url.indexOf("respawn") > 0) {

			$.ajax({
			    type: "POST",
			    url: url[posicao],
			    cache: false,
				statusCode: {
					200: function(data) {
						
						if(data !== null && data !== undefined && data !== "") {
							console.log(data);
							
							// fabricao dos respawns
							new polimorfRespawnFactory(data.list, 'many');
						}
					}
				}
			});
		}
	};

// FINALIZA RESPAWN
$("form[name='view-respawn'] input[type='button']").die();
$("form[name='view-respawn'] input[type='button']").live('click', function() {
	new efeitoTransicaoON();
	
	var that 	= $(this);
	var form	= that.parents('form');
	var now		= new Date();
	var GMT		= now.getTimezoneOffset() * 60000;

	$.ajax({
	    type: "POST",
	    url: form.attr("action"),
	    dataType: "json",
	    data: {
	    	'id'	: form.find("input[name='id']").val(),
	    	'acao'	: that.attr("name"),
	    	'now'	: now.getTime(),
	    	'gmt'	: GMT
	    },
	    cache: false,
		statusCode: {
			401: function() {
				new efeitoTransicaoOFF();
				
				$('.container-conexao').css({'display':'block'});
			},
			200: function(data) {
				new efeitoTransicaoOFF();
				
				new finalizaRespawn(data, that);
			}
		},
	});
});

// FECHAR ALERTA
$('.alertas section img.fechar').click(function() {
	$(".alertas section article").animate({height: "0px"}, 300, function() {
		$(".alertas section article").css({
			"display":"none",
			"height":"auto"
		});
		
		clearTimeout(animation);
	});
});

// EFEITO SOCIAL
$("footer.social a").mouseover(function() {
	$("button." + $(this).attr('class')).addClass("hover");
});

$("footer.social a").mouseout(function() {
	$("button." + $(this).attr('class')).removeClass("hover");
});
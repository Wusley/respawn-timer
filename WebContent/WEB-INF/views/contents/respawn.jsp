<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<section class="default box-formularios-respawn">
					<header class="titulo">
						<div></div>
						<h1>Adicionar Respawn</h1>
					</header>
				
					<ul class="modelo">
						<li>
							<form name="form-respawn" action="registraRespawn" method="post">
								<input type="button" name="iniciar" value="Iniciar" />
								
								<label>
									<input type="text" name="nome" />
								</label> 
								
								<label>
									<input type="text" name="dataMorte" placeholder="aaaa/mm/dd hh:mm" />
								</label>
								
								<label for="none" accesskey="none">
									<input type="text" name="dias" placeholder="dias" />
									<input type="text" name="tempoNascimento" placeholder="hh:mm" />
								</label>
								
								<input type="button" name="remover" value="Remover" />
							</form> <!-- formulario-respawn -->
						</li>
					</ul> <!-- modelo -->
				
					<article class="formularios-respawn">
						<header>
							<button class="novo-formulario-respawn">Add</button>
							<div class="nome">Nome</div>
							<div class="dead">Quando morreu?</div>
							<div class="respawn">Intervalo de nascimento?</div>
						</header> <!-- header -->
				
						<ul>
						</ul>
					</article> <!-- formularios-respawn -->
				
				</section> <!-- box-formularios-respawn -->
				
				<section class="publicidade">
					<article>
						<span class="divulgacao">Anuncie 728 X 90</span>
					</article>
				</section> <!-- publicidade -->
				
				<section class="default box-lista-respawn">
					<header class="titulo">
						<div></div>
						<h1>Respawns</h1>
					</header>
				
					<ul class="modelo">
						<li>
							<form id="respawn" name="view-respawn" action="finalizaRespawn" method="post">
								<input type="hidden" name="id" value="" />
								<input type="button" name="sucesso" value="Sucesso" disabled="disabled" /> 
								
								<label class="nome"> --- </label> 
								
								<label class="dataMorte">
									<span class="regressiva"> --- </span> <span class="separa">&frasl;</span>
									<span class="tempoNascimento"> --- </span>
								</label>
								
								<label class="nascimento"> --- </label>
								<input type="button" name="falha" value="Falha" disabled="disabled" />
								<input type="button" name="delete" value="Cancelar" disabled="disabled" />
							</form>
						</li>
					</ul> <!-- modelo -->
				
					<article class="lista-respawn">
						<header>
							<div class="nome">Nome</div>
							<div class="remaining">Tempo restante</div>
							<div class="respawn">Nascimento</div>
						</header>
				
						<ul>
							<li>
								<form id="respawn" name="view-respawn" action="finalizaRespawn" method="post">
									<input type="hidden" name="id" value="" />
									<input type="button" name="sucesso" value="Successo" disabled="disabled" />
									
									<label class="nome"> --- </label>
									<label class="dataMorte">
										<span class="regressiva"> --- </span> <span class="separa">&frasl;</span> <span class="tempoNascimento"> --- </span>
									</label>
									
									<label class="nascimento"> --- </label>
									<input type="button" name="falha" value="Falha" disabled="disabled" />
									<input type="button" name="delete" value="Cancelar" disabled="disabled" />
								</form>
							</li>
						</ul>
					</article> <!-- lista-respawn -->
				</section> <!-- box-lista-respawn -->
				
				<section class="publicidade">
					<article>
						<span class="divulgacao">Anuncie 728 X 90</span>
					</article>
				</section> <!-- publicidade -->
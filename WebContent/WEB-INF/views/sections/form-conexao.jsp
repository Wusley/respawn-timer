<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<section class="conexao">
					<article class="login">
						<h3>Conectar</h3>
				
						<form name="form-conexao" action="<%=request.getContextPath()%>/conectaUsuario" method="post">
							<fieldset>
								<legend>Conex&atilde;o</legend>
				
								<label for="email" accesskey="e">
									<input class="default" type="email" placeholder="e-mail" name="email" />
								</label> 
								
								<label for="senha" accesskey="s">
									<input class="default" type="password" placeholder="senha" name="senha" />
								</label>
								
								<label for="conectar" accesskey="c">
									<input type="button" name="conectar" value="Conectar" />
								</label>
							</fieldset>
						</form>
					</article>
				
					<article class="registro">
						<img class="fechar" src="<%=request.getContextPath()%>/resources/images/icons/16x16_xis.png" height="16" width="16" alt="" title="" />
				
						<h3>Registrar</h3>
				
						<form name="form-registro" action="<%=request.getContextPath()%>/registraUsuario" method="post">
							<fieldset>
								<legend>Registro</legend>
				
								<label for="nick" accesskey="n">
									<input class="default" type="text" placeholder="nick" name="nick" />
								</label>
								
								<label for="email" accesskey="e">
									<input class="default" type="email" placeholder="e-mail" name="email" />
									<input class="default repeat" type="email" placeholder="repita e-mail" name="emailRepeat" />
								</label>
								
								<label for="senha" accesskey="s">
									<input class="default" type="password" placeholder="senha" name="senha" />
									<input class="default repeat" type="password" placeholder="repita senha" name="senhaRepeat" />
								</label>
								
								<label for="salvar" accesskey="s">
									<input type="button" name="salvar" value="Salvar" />
								</label>
							</fieldset>
						</form>
					</article>
				</section> <!-- "conexao" -->
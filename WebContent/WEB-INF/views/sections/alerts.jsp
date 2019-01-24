<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<section>
					<img class="fechar" src="<%=request.getContextPath()%>/resources/images/icons/16x16_xis.png" height="16" width="16" alt="" title="" />
					
					<article class="loading">
						<div class="posiciona">
							<div id="loaderImage"></div>
						</div>
					</article>
					
					<article class="sucesso">
						<h3></h3>
					</article>
				
					<article class="erro">
						<h3></h3>
					</article>
				
					<article class="falha">
						<div class="box-falhas">
							<h3>Não foi possível realizar a operação.</h3>
							<h4>Causas:</h4>
				
							<div class="falhas"></div>
						</div>
					</article>
				</section>
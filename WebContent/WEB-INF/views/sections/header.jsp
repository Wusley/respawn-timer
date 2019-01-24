<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<section class="topbar">
				
					<footer class="social">
						<a class="facebook" href="http://www.facebook.com/share.php?u=http://respawntimer.com/${link}" target="_blank"></a>
	
						<a class="twitter" href="http://twitter.com/share?url=http://respawntimer.com/${link}&amp;text=RespawnTimer&amp;hashtags=RespawnTimer" target="_blank"></a>
	
						<a class="gplus" href="https://plus.google.com/share?url=http://respawntimer.com/${link}" onclick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"></a>
					</footer> <!-- social -->
					
				</section> <!-- topbar -->
				
				<section class="header">
					<ul class="menu">
				
						<c:if test="${link == 'respawn'}">
							<li><a href="<%=request.getContextPath()%>/">Sobre</a></li>
						</c:if>
				
						<c:if test="${link == 'sobre'}">
							<li><a href="<%=request.getContextPath()%>/respawn">Respawn</a></li>
						</c:if>
				
						<c:if test="${link != 'respawn' && link != 'sobre'}">
							<li><a href="<%=request.getContextPath()%>/">Sobre</a></li>
							<li><a href="<%=request.getContextPath()%>/respawn">Respawn</a></li>
						</c:if>
				
						<c:if test="${not empty usuario}">
							<li>
								<a class="nick-conectado" href="<%=request.getContextPath()%>/respawn">${usuario.nick}</a> <span>,</span><a class="desconectar" href="<%=request.getContextPath()%>/desconectaUsuario">sair</a>
							</li>
						</c:if>
				
						<c:if test="${empty usuario}">
							<li class="conectar">Login</li>
							<li class="conectar-hidden">
								<a class="nick-conectado" href="<%=request.getContextPath()%>/respawn">${usuario.nick}</a> <span>,</span><a class="desconectar" href="<%=request.getContextPath()%>/desconectaUsuario">sair</a>
							</li>
						</c:if>
						
					</ul> <!-- menu -->
				</section> <!-- header -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html itemscope="itemscope" itemtype="http://schema.org/WebPage" lang="pt-br">
	<head>
		<title>Página n&atilde;o encontrada</title>
	
		<!-- metas -->
		<meta charset='UTF-8'>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="author" content="Wesley Lima" />
		<meta name="generator" content="Eclipse Indigo" />
		<meta name="description" content="">
	
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
		<!-- favicon -->
		<link itemprop="image" rel="icon" type="image/png" href="<%=request.getContextPath()%>/resources/images/icons/apple/apple-touch-icon.png" />
	
		<!-- apple icon -->
		<link rel="apple-touch-icon" href="<%=request.getContextPath()%>/resources/images/icons/apple/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="<%=request.getContextPath()%>/resources/images/icons/apple/57x57_apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="<%=request.getContextPath()%>/resources/images/icons/apple/72x72_apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="<%=request.getContextPath()%>/resources/images/icons/apple/114x114_apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="<%=request.getContextPath()%>/resources/images/icons/apple/144x144_apple-touch-icon.png" />
	
		<!-- lib -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/normalize.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/js/lib/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css" />
	
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.min.css" />
	</head>

	<body>
		<div class="geral">
			<div class="parallax">
				<c:import url="/WEB-INF/views/sections/parallax.jsp"></c:import>
			</div>
	
			<div class="alertas">
				<c:import url="/WEB-INF/views/sections/alerts.jsp"></c:import>
			</div> <!-- alertas gerais -->
	
			<header class="principal">
				<c:import url="/WEB-INF/views/sections/header.jsp"></c:import>
			</header> <!-- principal -->
	
			<div class="container-conexao">
				<c:import url="/WEB-INF/views/sections/form-conexao.jsp"></c:import>
			</div> <!-- container-conexao -->
	
			<section class="sidebar">
				<c:import url="/WEB-INF/views/sections/sidebar.jsp"></c:import>
			</section> <!-- sidebar -->
	
			<section class="content">
				<section class="default status">
					<h1>Página não foi encontrada</h1>
				</section>
				<!-- status -->
	
				<section class="publicidade">
					<article>
						<span class="divulgacao">Anuncie 728 X 90</span>
					</article>
				</section>
				<!-- publicidade -->
			</section> <!-- content -->
	
			<footer class="auxiliar"></footer> <!-- manter footer principal na base -->
	
			<footer class="principal">
				<c:import url="/WEB-INF/views/sections/footer.jsp"></c:import>
			</footer> <!-- principal -->
		</div> <!-- geral -->
	
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lib/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lib/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lib/jquery-ui-1.9.2.custom/js/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/lib/hisrc.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/respawn.min.js"></script>
	
		<script type="text/javascript">
			/*
			var _gaq = _gaq || [];
				 _gaq.push(['_setAccount', 'UA-37141592-1']);
				_gaq.push(['_trackPageview']);
			
			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
			 */
		</script>
	</body>

</html>
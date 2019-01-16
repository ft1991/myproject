<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mytabel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		* {margin: 0 auto;padding: 0;}
		body {background-color: #f3f3f3;}
		#mytable{
			text-align:center;
			margin-top: 100px;
			width: 800px;
		}
		p{
			text-align:center;
			color:red;		
		}
		
	
	</style>
	<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	 /*查询所有  */  
	 function find(url){
		 var page = document.getElementById("page").value;
		 var every = document.getElementById("every").value;
    	$.ajax({
    		async:false,
    		type:"post",
    		url:url,
    		datatype:"json",
    		data:{
    			"page":page,
    			"every":every
    		},
    		success:function(data){
    			//取出数据转换字符串为数组
    			data = eval("("+data+")");  
    			var html="";
    			//删除表格非第一行的内容
    			$("#mytable tr:not(:first)").remove();
    		    for(var i = 0; i < data.length;i++) {
    			     html += "<tr id="+i+">"; 
    			     $.each(data[i],function(j,val){ 
    			    	 html+="<td><input type='text' value='"+val+"'/></td>";	 
  			        	 if(j == "endTime"){
  			        		 //servlet?var=num  只能通过doget接收
  			  				html+="<td><a href='javascript:void(0)' onclick=del('DeleteServlet?id="+data[i].rnumber+"')>删除</a></td>";
  			        		html+="<td><a href='javascript:void(0)' onclick=update('UpdateServlet?id="+data[i].rnumber+"')>更新</a></td>";
  			        	 }
    			     });
    			   html+="</tr>";  
    			 }
    		   $("#first").after(function(){
    			   return html;
    		   });
    		}
    	});
    }
	/* 删除 */
	function del(i){
		
		$.ajax({
			type:"post",
			async:false,
			url:i,
			datatype:"json",
			success:function(data){
				
				$("p").html(data);
			}
		});
		find("QueryAllServlet");
	} 
	</script>

  </head>
  
  <body onload="find('QueryAllServlet')">
   	<table id="mytable" name="mytable" border="1px" cellspacing="0px" cellpadding="0px">
   	<tr id="first">
	   	 <th>车次</th>
	   	 <th>出发地</th>
	   	 <th>到达地</th>
	   	 <th>出发时间</th>
	   	 <th>到达时间</th>
	   	 <th id="caozuo" colspan="2">操作</th>
   	 </tr>	
  
   	</table>
   	<input type="text" value="${page }" id="page">
   	<input type="text" value="${every }" id="every" >
   	<table>
   	 <tr>
     <td colspan="7">
       <c:if test="${page==1}">
       <a>首页</a>
       <a>上一页</a>
       </c:if>
       <c:if test="${page!=1}">
       <a href="find?page=1">首页</a>
       <a href="find?page=${requestScope.page-1}">上一页</a>
       </c:if>
       <c:forEach var ="everyPage" begin="1" end="${requestScope.pageCount}"> 
          <a href="find?page=${everyPage}">${everyPage}</a>
         </c:forEach>
         
         <c:if test="${page==pageCount}">
       <a>下一页</a>
       <a>尾页</a>
       </c:if>  
       
       <c:if test="${page !=requestScope.pageCount}">
       <a href="find?page=${requestScope.page+1}">下一页</a>
       <a href="find?page=${requestScope.pageCount}">尾页</a>
       </c:if>
     </td>
    </tr>
   	</table>
   	<p></p>
  </body>
</html>

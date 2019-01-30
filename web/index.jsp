<%
    if(request.getSession(false).getAttribute("username") == null){
        response.sendRedirect("/login");
    }else{
        response.sendRedirect("/dashboard");
    }
%>
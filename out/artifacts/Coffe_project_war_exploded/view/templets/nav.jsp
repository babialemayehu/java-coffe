<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 1/28/19
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.group2.util.auth.Auth" %>
<nav class="navbar navbar-expand navbar-dark bg-primary static-top">

    <a class="navbar-brand mr-1" href="/dashboard">Coffe</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">

        </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
        <%--<li class="nav-item dropdown no-arrow mx-1">--%>
            <%--<a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                <%--<i class="fas fa-bell fa-fw"></i>--%>
                <%--<span class="badge badge-danger">9+</span>--%>
            <%--</a>--%>
            <%--<div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">--%>
                <%--<a class="dropdown-item" href="#">Action</a>--%>
                <%--<a class="dropdown-item" href="#">Another action</a>--%>
                <%--<div class="dropdown-divider"></div>--%>
                <%--<a class="dropdown-item" href="#">Something else here</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <%--<li class="nav-item dropdown no-arrow mx-1">--%>
            <%--<a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                <%--<i class="fas fa-envelope fa-fw"></i>--%>
                <%--<span class="badge badge-danger">7</span>--%>
            <%--</a>--%>
            <%--<div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">--%>
                <%--<a class="dropdown-item" href="#">Action</a>--%>
                <%--<a class="dropdown-item" href="#">Another action</a>--%>
                <%--<div class="dropdown-divider"></div>--%>
                <%--<a class="dropdown-item" href="#">Something else here</a>--%>
            <%--</div>--%>
        <%--</li>--%>
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user-circle fa-fw"></i>
                <%= Auth.user(request).getFirst_name()%>

            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown" style="min-width: 18em; text-align: center; padding: 2em; ">
                <h5 style="text-transform: capitalize;"><%= Auth.user(request).getFirst_name()%> <%= Auth.user(request).getFather_name()%> <%= Auth.user(request).getGfather_name()%></h5>
                <h6 class="text-secondary">Worker ID: <%= Auth.user(request).getWorker_id()%></h6>
                <div class="dropdown-divider"></div>
                <form action="/logout" method="post">
                    <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#logoutModal">Logout</button>
                </form>

            </div>
        </li>
    </ul>

</nav>
<script>
    $("#sidebarToggle").on('click',function(e) {
        e.preventDefault();
        $("body").toggleClass("sidebar-toggled");
        $(".sidebar").toggleClass("toggled");
    });
</script>
<%
    try{
    if(Auth.user(request).getSetup() == 0){%>
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Change password</h5>
                </div>
                <div class="modal-body">
                    <div class="contaier">
                        <form action="/change password" method="post" id="change-password-form">
                            <div class="alert alert-danger" style="display: none">
                                <p id="match" style="display: none">Password confirmation dosen't match</p>
                                <p id="error"style="display: none">Some error occured</p>
                            </div>
                            <div class="alert alert-success" style="display: none">
                                <p>You hava successfuly changed your password</p>
                            </div>
                            <div class="from-group form">
                                <label for="password">Password</label>
                                <input type="password" autofocus name="password" id="password" class="form-control"/>
                            </div>
                            <div class="from-group form">
                                <label for="confirm-password">Confirm Password</label>
                                <input type="password" name="confirm password" id="confirm-password" class="form-control"/>
                            </div>
                            <div class="from-group form">
                                <br>
                                <button type="submit" class="btn btn-primary float-right">Save changes</button>
                            </div>
                        </form>
                        <div id="ok" style="display:none">
                            <br>
                            <button type="submit" class="btn btn-primary float-right" data-dismiss="modal">OK</button>
                        </div>
                    </div>


                </div>

            </div>
        </div>
    </div>
    <script>

        $("#exampleModalCenter").modal({
            backdrop: 'static',
            keyboard: false,
            show: true
        });
        function pass_match(){
            var password = $("#change-password-form #password").val();
            var confirmPassword = $("#change-password-form #confirm-password").val();
            return (password == confirmPassword);
        }
        $("#change-password-form").on("submit", function(e){
            e.preventDefault();
            var password = $("#change-password-form #password").val();
            $("#change-password-form .alert").hide();
            $("#change-password-form .alert-danger>p").hide();
            if(pass_match()){
                $.post("/change password?password="+password)
                    .done(function(){
                        $("#change-password-form .alert-success").show();
                        $(".form").hide();
                        $("#ok").show();
                        $("#exampleModalCenter").modal({
                            backdrop: true,
                            keyboard: true,
                            show: true
                        });
                    })
                    .fail(function(){
                        $("#change-password-form .alert-danger>#error").show();
                    });
            }else{
                $("#change-password-form .alert-danger>#match").show();
            }

            return false;
        })
    </script>
<%}
}catch(Exception e){
        out.print(e.getLocalizedMessage());
}%>

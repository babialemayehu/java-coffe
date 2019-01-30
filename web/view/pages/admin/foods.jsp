<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<main class="container">
    <section class="row">
        <table class="table table-striped table-hover">
            <thead>
            <tr style="font-weight: 500">
                <td>Name</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.get('foods')}" var="food">
                <tr>
                    <td>${food.getName()}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </section>
    <section class="row">
        <div class="card" style="margin: auto">
            <div class="card-body">
                <h5>Add meal</h5>
                <form action="/foods" method="post" style="width: 100%" class="form">
                    <div class="container">
                        <div class="row">
                            <c:if test="${sessionScope.get('update') != null}">
                                <div class="col-md-6">
                                    <input type="text" name="id" hidden value="${sessionScope.get('update').getId()}"/>
                                </div>
                            </c:if>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" name="name" value="${sessionScope.get('update').getName()}" class="form-control" id="name"/>
                                </div>
                            </div>

                            <div class="col">
                                <button class="btn-primary btn float-right" type="submit" style="margin-top: 1.1em">Add</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>
<%
    request.getSession().setAttribute("update", null);
%>
<jsp:include page="../../templets/bottom.jsp"/>
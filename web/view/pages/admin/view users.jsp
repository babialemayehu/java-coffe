<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="container">
    <section class="row">
        <table class="table table-striped table-hover">
            <thead>
                <tr style="font-weight: 500">
                    <td>Worker ID</td>
                    <td>Name</td>
                    <td>Email</td>
                    <td>Phone</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${sessionScope.get('users')}" var="user">
                        <tr onclick="window.location.href = '/user profile?user=${user.getId()}'">
                            <td>${user.getWorker_id()}</td>
                            <td>${user.getFirst_name()} ${user.getFather_name()} ${user.getGfather_name()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getPhone()}</td>
                        </tr>
                </c:forEach>
            </tbody>

        </table>
    </section>
</main>
<jsp:include page="../../templets/bottom.jsp"/>
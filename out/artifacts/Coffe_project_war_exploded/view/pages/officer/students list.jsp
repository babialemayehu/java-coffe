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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.get('students')}" var="student">
                <tr onclick="window.location.href = '/student profile?student=${student.getId()}'">
                    <td>${student.getReg_id()}</td>
                    <td>${student.getFirst_name()} ${student.getFather_name()} ${student.getGfather_name()}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </section>
</main>
<%
    request.getSession().setAttribute("students", null);
%>
<jsp:include page="../../templets/bottom.jsp"/>
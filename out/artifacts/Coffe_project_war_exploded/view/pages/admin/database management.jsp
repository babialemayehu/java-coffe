<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<main class="container">
    <section class="row">
        <button class="btn btn-primary btn-lg float-right" id="backup">Backup</button>
    </section>
    <section class="row">
        <table class="table table-hover table-striped">
            <c:forEach items='${requestScope.get("bakups")}' var="backup">
                <tr>
                    <td>${backup.getId()}</td>
                </tr>
            </c:forEach>
        </table>
    </section>
</main>

<script>
    $("#backup").on("click", function(){
        $.post("/database mamagment")
            .done(function(response){
                console.log(response);
            })
            .fail(function(response){
                console.log(response);
            })
    });
</script>
<jsp:include page="../../templets/bottom.jsp"/>
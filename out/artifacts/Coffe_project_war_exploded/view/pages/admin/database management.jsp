<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<main class="container">
    <section class="row">
        <div class="container" style="margin: 1em">
            <form class="form-inline" style="float: right;" id="backup-form">
                <label for="comment" style="margin-right: 12px">Backup current database </label>
                <div class="input-group">
                    <input type="text" name="comment" id="comment" class="form-control" placeholder="Comment"/>
                    <input type="submit" class="btn btn-primary" value="Backup"/>
                </div>
            </form>
        </div>

    </section>
    <section class="row" >
        <c:if test="${not empty param.success_msg}">
            <div class="alert alert-success" style="margin: auto; padding: 1em 4em;margin-bottom: 1em;">
                <i>${param.success_msg}</i>
            </div>
        </c:if>
        <c:if test="${not empty param.err_msg}">
            <div class="alert alert-danger" style="margin: auto; padding: 1em 4em;margin-bottom: 1em;">
                <i>${param.err_msg}</i>
            </div>
        </c:if>
        <table class="table table-hover table-striped">
            <t>
                <td><b>Comment</b></td>
                <td><b>Backup date</b></td>
                <td></td>
            </t>
            <c:forEach items='${requestScope.backups}' var="backup">
                <tr>
                    <td>
                        <c:if test="${backup.getComment()==null}">
                            <i class='text-secondary'>No comment</i>
                        </c:if>
                        ${backup.getComment()}
                    </td>
                    <td>${backup.getCreated_at().toString()}</td>
                    <td>
                        <a href="/restore?backup_id=${backup.getId()}">
                            <button class="btn btn-outline-primary">Restore</button>
                        </a>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>
</main>

<script>
    $("#backup-form").on("submit", function(e){
        e.preventDefault();
        $.post("/database mamagment?comment="+$("#backup-form #commen").val())
            .done(function(response){
                console.log(response);
            })
            .fail(function(response){
                console.log(response);
            })
        return false;
    });
</script>
<jsp:include page="../../templets/bottom.jsp"/>
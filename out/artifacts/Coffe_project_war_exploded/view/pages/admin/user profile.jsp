<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="container">
    <section class="row">
        <div class="col-6 offset-3">
            <div class="card" style="margin-top: 3em">
                <h3>${sessionScope.get("user").first_name} ${sessionScope.get("user").father_name} ${sessionScope.get("user").gfather_name}</h3>
                <h4 class="text-secondary">Worker ID: ${sessionScope.get("user").getWorker_id()}</h4>
                <p class="text-secondary"><b>${sessionScope.get("role").getName()}</b></p><br>
                <p>Email: ${sessionScope.get("user").getEmail()}</p>
                <p>Phone: ${sessionScope.get("user").getPhone()}</p>
                <div class="row">
                    <div class="col-6"></div>
                    <div class="col-3"><button onClick='window.location.href="/delete user?delete=${sessionScope.get("user").getId()}&active=${sessionScope.get("user").getActive()}"'
                                               class="btn btn-outline-danger float-right">
                        <c:choose>
                            <c:when test="${sessionScope.get('user').getActive()}">
                                Deny
                            </c:when>
                            <c:otherwise>
                                Allow
                            </c:otherwise>
                        </c:choose>
                    </button></div>
                    <div class="col-3"><button onclick='window.location.href="/create user?update=${sessionScope.get("user").getId()}"' class="btn-primary btn  float-right">Update</button></div>
                </div>
            </div>
        </div>
    </section>
</main>

<jsp:include page="../../templets/bottom.jsp"/>
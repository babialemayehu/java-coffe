<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="container">
    <section class="row">
        <div class="col-6 offset-3">
            <div class="card" style="margin-top: 3em">
                <img src="/storage/${sessionScope.get("student").getProfile_pic()}" style="width: 150px; height: 150px;border-radius: 50%; align-self: center; margin-bottom: 2em;" />
                <h3>${sessionScope.get("student").first_name} ${sessionScope.get("student").father_name} ${sessionScope.get("student").gfather_name}</h3>
                <h5 class="text-secondary">Worker ID: ${sessionScope.get("student").getReg_id()}</h5>

                <div class="row" style="margin-top: 3em">
                    <div class="col-6"></div>
                    <div class="col-3"><button onClick='window.location.href="/student access?delete=${sessionScope.get("student").getId()}&active=${sessionScope.get("student").getActive()}"'
                                               class="btn btn-outline-danger float-right">
                        <c:choose>
                            <c:when test="${sessionScope.get('student').getActive()}">
                                Deny
                            </c:when>
                            <c:otherwise>
                                Allow
                            </c:otherwise>
                        </c:choose>
                    </button></div>
                    <div class="col-3"><button onclick='window.location.href="/register student?update=${sessionScope.get("student").getId()}"' class="btn-primary btn  float-right">Update</button></div>
                </div>
            </div>
        </div>
    </section>
</main>

<jsp:include page="../../templets/bottom.jsp"/>
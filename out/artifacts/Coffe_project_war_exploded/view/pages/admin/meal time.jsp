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
                <td>from</td>
                <td>to</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.get('mealTimes')}" var="mealTime">
                <tr oncontextmenu="openContextMenu(event, ${mealTime.getId()})">
                    <td>${mealTime.getName()}</td>
                    <td>${mealTime.getFrom()} </td>
                    <td>${mealTime.getTo()}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </section>
    <section class="row">
        <div class="card" style="margin: auto">
            <div class="card-body">
                <h5>
                    <c:choose>
                        <c:when test="${sessionScope.get('update') != null}">
                            Update meal</br>
                        </c:when>
                        <c:otherwise>
                            Add meal</br>
                        </c:otherwise>
                    </c:choose>
                </h5>
                <form action="/meal time" method="post" style="width: 100%" class="form">
                    <c:if test="${sessionScope.get('update') != null}">
                        <input type="number" name="id" value="${sessionScope.get('update').getId()}" hidden />
                    </c:if>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" name="name" value="${sessionScope.get('update').getName()}" class="form-control" id="name"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="from">From</label>
                                    <input type="time" name="from" value="${sessionScope.get('update').getFrom()}" class="form-control" id="from"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="to">To</label>
                                    <input type="time" name="to" value="${sessionScope.get('update').getTo()}" class="form-control" id="to"/>
                                </div>
                            </div>
                            <div class="col">
                                <button class="btn-primary btn float-right" type="submit" style="margin-top: 1.1em">
                                    <c:choose>
                                    <c:when test="${sessionScope.get('update')!=null}">
                                        Update
                                    </c:when>
                                    <c:otherwise>
                                        Add
                                    </c:otherwise>
                                </c:choose></button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>

<div class="card" id="context-menu" style="position: absolute;display: none; padding: 1em">
    <ul class="list-group list-group-flush">
        <li class="list-group-item" onclick="onUpdate()">Update</li>
        <li class="list-group-item" onclick="onDelete()"> Delete </li>
    </ul>
</div>

<script>
    var contextMenu = document.getElementById("context-menu");
    var  selectedItem = null;
    function openContextMenu(e, id){
        e.preventDefault();
        contextMenu.style.display = "block";
        contextMenu.style.left =e.clientX+"px";
        contextMenu.style.top= e.clientY+"px";
        selectedItem = id;
    }
    function onUpdate(item){
        window.location.href = "/meal time?update="+selectedItem;
    }
    function onDelete(item){
        window.location.href = "/meal time?delete="+selectedItem;
    }
    window.addEventListener("click", function (ev) { contextMenu.style.display = "none";  })
</script>
<%
    request.getSession().setAttribute("update", null);
    request.getSession().setAttribute("mealTimes", null);
%>
<jsp:include page="../../templets/bottom.jsp"/>
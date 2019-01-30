<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-wrapper">
    <main class="container">
        <section class="row">
            <div class="form-container" style="margin: auto; width: 75%; padding: 2rem">
                <h2>Create user</h2>

                <form name="createUser" action="/register student" method="post" enctype='multipart/form-data'>
                    <c:if test="${sessionScope.get('student') != null}">
                        <input type="text" name="id" value="${sessionScope.get('student').getId()}"
                               ng-model="User.id" ng-init=User.id="${sessionScope.get('student').getId()}" hidden/>
                    </c:if>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="first-name">First name</label><br>
                                    <input type="text" name="first_name" id="first-name" class="form-control" value="${sessionScope.get('student').getFirst_name()}" required/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="father-name">Father name</label>
                                    <input type="text" name="father_name" id="father-name" class="form-control" value="${sessionScope.get('student').getFather_name()}" required>
                                 </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="gfather-name">Grand father name</label>
                                    <input type="text" name="gfather_name" id="gfather-name" class="form-control" value="${sessionScope.get('student').getGfather_name()}" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="reg-id">Registeral ID</label>
                                    <input type="text" name="reg_id" id="reg-id" class="form-control" value="${sessionScope.get('student').getReg_id()}" required/>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="department">Department</label>
                                    <select name="department_id" id="department" class="form-control" value="${sessionScope.get('student').getDepartment_id()}" required>
                                        <option value="2">Officer</option>
                                        <option value="3">Tiker</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="profile_pic">Profile picture</label>
                                    <input type="file" name="profile_pic" id="profile_pic" class="form-control" required/>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-10"></div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-primary">Rrgister</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </main>
</div>
<% request.getSession().setAttribute("student", null);%>
<jsp:include page="../../templets/bottom.jsp"/>
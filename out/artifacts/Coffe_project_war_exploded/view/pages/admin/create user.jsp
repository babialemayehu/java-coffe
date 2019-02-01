<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-wrapper">
    <main class="container">
        <section class="row">
            <div class="form-container" style="margin: auto; width: 75%; padding: 2rem">
                <h2>Create user</h2>

                <form name="createUser" action="" method="post"
                      ng-controller="CreateUserController" ng-submit="createUser.$valid && submit($event)" ng-class="{'form-loading': loading}" novalidate>
                    <c:if test="${sessionScope.get('user') != null}">
                        <input type="text" name="id" value="${sessionScope.get('user').getId()}"
                               ng-model="User.id" ng-init=User.id="${sessionScope.get('user').getId()}" hidden/>
                    </c:if>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="first-name">First name</label><br>
                                    <input type="text" name="fist_name" id="first-name" class="form-control"
                                           ng-model="User.first_name" ng-init=User.first_name="${sessionScope.get('user').getFirst_name()}" ng-required="true"/>

                                    <span ng-show="createUser.fist_name.$touched && createUser.fist_name.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="father-name">Father name</label>
                                    <input type="text" name="father_name" id="father-name" class="form-control"
                                           ng-model="User.father_name" ng-init=User.father_name="${sessionScope.get('user').getFather_name()}" ng-required="true"/>
                                    <span ng-show="createUser.father_name.$touched && createUser.father_name.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="gfather-name">Grand father name</label>
                                    <input type="text" name="grand_father_name" id="gfather-name" class="form-control"
                                           ng-model="User.gfather_name" ng-init=User.gfather_name="${sessionScope.get('user').getGfather_name()}" ng-required="true"/>
                                    <span ng-show="createUser.gfather_name.$touched && createUser.gfather_name.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="worker-id">Worker ID</label>
                                    <input type="text" name="worker_id" id="worker-id" class="form-control"
                                           ng-model="User.worker_id" ng-init=User.worker_id="${sessionScope.get('user').getWorker_id()}"  ng-required="true"/>
                                    <span ng-show="createUser.worker_id.$touched && createUser.worker_id.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="role">Role</label>
                                    <select name="role_id" id="role" class="form-control"
                                            ng-model="User.role_id" ng-init=User.role_id="${sessionScope.get('user').getRole_id()}"  ng-required="true">
                                        <option value="2">Officer</option>
                                        <option value="3">Tiker</option>
                                    </select>
                                    <span ng-show="createUser.role_id.$touched && createUser.role_id.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" name="email" id="email" class="form-control"
                                           ng-model="User.email" ng-init=User.email="${sessionScope.get('user').getEmail()}"  ng-required="true"/>
                                    <span ng-show="createUser.email.$touched && createUser.email.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="tel" name="phone" id="phone" class="form-control"
                                           ng-model="User.phone" ng-init=User.phone="${sessionScope.get('user').getPhone()}"  ng-required="true"/>
                                    <span ng-show="createUser.phone.$touched && createUser.phone.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-10"></div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-primary" ng-disabled="createUser.$invalid">Rrgister</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </main>
</div>
<%
    request.getSession().setAttribute("user", null);
%>
<jsp:include page="../../templets/bottom.jsp"/>
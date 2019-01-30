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
                      ng-controller="RegisterStudent" ng-submit="createUser.$valid && submit($event)" ng-class="{'form-loading': loading}" novalidate>
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
                                    <label for="reg-id">Registeral ID</label>
                                    <input type="text" name="reg_id" id="reg-id" class="form-control"
                                           ng-model="User.reg_id" ng-init=User.reg_id="${sessionScope.get('user').getReg_id()}"  ng-required="true"/>
                                    <span ng-show="createUser.reg_id.$touched && createUser.reg_id.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="department">Department</label>
                                    <select name="department_id" id="department" class="form-control"
                                            ng-model="User.department_id" ng-init=User.department_id="${sessionScope.get('user').getDepartment_id()}"  ng-required="true">
                                        <option value="2">Officer</option>
                                        <option value="3">Tiker</option>
                                    </select>
                                    <span ng-show="createUser.department_id.$touched && createUser.department_id.$invalid" class="form-text error">
                                        The name is required.</span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="file">Profile picture</label>
                                    <input type="file" name="file" id="file" class="form-control"
                                           ng-file  ng-required="true"/>
                                    <span ng-show="createUser.email.$touched && createUser.email.$invalid" class="form-text error">
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
<jsp:include page="../../templets/bottom.jsp"/>
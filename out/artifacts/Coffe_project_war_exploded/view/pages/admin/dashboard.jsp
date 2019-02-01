<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/admin/side%20nav.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-primary o-hidden h-100" style="padding: 1em">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-user"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("users")%> Workers are registered</div>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-warning o-hidden h-100" style="padding: 1em">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-table"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("officer")%> Officers</div>
                </div>

            </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-success o-hidden h-100" style="padding: 1em">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-pencil-alt"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("tiker")%> Tikers</div>
                </div>

            </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-danger o-hidden h-100" style="padding: 1em">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-book"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("student")%> Students</div>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="card col-10">
            <div class="card-header">
                <i class="fas fa-chart-area"></i>
                Area Chart Example</div>
            <div class="card-body">
                <canvas id="myAreaChart" width="100%" height="30"></canvas>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
    </div>
</div>


<jsp:include page="../../templets/bottom.jsp"/>
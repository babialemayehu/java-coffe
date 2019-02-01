<jsp:include page="../../templets/top.jsp"/>
<jsp:include page="../../templets/nav.jsp"/>
<jsp:include page="../../templets/officer/side%20nav.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-xl-4 col-sm-12 mb-4">
            <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-comments"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("allowed")%> Allowed meals</div>
                </div>

            </div>
        </div>
        <div class="col-xl-4 col-sm-12 mb-4">
            <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-list"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("student")%> Active students</div>
                </div>

            </div>
        </div>
        <div class="col-xl-4 col-sm-12 mb-4">
            <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-shopping-cart"></i>
                    </div>
                    <div class="mr-5"><%=request.getAttribute("meal")%> Total meal</div>
                </div>

            </div>
        </div>

    </div>
    <div class="row">
        <div class="card col-12">
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
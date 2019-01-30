<jsp:include page="../templets/top.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card" style="margin-top: 6em;">
                    <form action="/login" method="POST">
                        <h3>Login</h3>
                        <div class="form-group">
                            <label for="worker_id">Worker id</label>
                            <input type="text" name="worker_id" id="worker_id" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-round btn-block">LOGIN</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../templets/bottom.jsp"/>
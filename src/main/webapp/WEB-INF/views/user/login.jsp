<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<form id="loginForm" action="/user/loginPost" method="post">
	<div class="form-group has-feedback">
		<input type="text" name="uid" class="form-control" placeholder="USER ID"/>
		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
	</div>
	<div class="form-group has-feedback">
		<input type="password" name="upw" class="form-control" placeholder="Password"/>
		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
	</div>
	<div class="row">
		<div class="col-xd-8">
			<div class="checkbox icheck">
				<label>
					<input type="checkbox" name="useCookie">Remember Me
				</label>
			</div>
		</div>
		<div class="col-xs-4">
			<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
		</div>
	</div>
</form>


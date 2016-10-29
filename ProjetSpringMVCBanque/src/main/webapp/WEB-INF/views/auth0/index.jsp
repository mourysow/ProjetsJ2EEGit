<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<!DOCTYPE html>
<html ng-app="statelessApp">
<head lang="en">
	<title>Stateless Authentication Example</title>			
	<script src="<c:url value="/resources/js/angular.js"/>"></script>
	<script src="<c:url value="/resources/js/controllers.js"/>"></script>
</head>
<body ng-controller="AuthCtrl" ng-init="init()" style="padding: 10% 20%">
	<div ng-hide="authenticated">
		Login as user/user or admin/admin:<br>
		<label for="un">Username:</label><input id="un" type="text" ng-model="userLogin"><br>
		<label for="pw">Password:</label><input id="pw" type="password" ng-model="password"><br>
		<button ng-click="login()">Login</button>
	</div>
	<div ng-show="authenticated">
		Logged in as {{uuid}} <button ng-click="logout()">Logout</button><br><br>
		Token content: <pre>{{token | json}}</pre>
	</div>
	<div ng-repeat="result in results track by result.index">
   	  <h3>{{result.route}} :</h3><pre>{{result.result | json}}</pre>
	</div>
</body>
</html>
<html>
<body>
	<form method="post" action="servlet/SampleServlet">
		<table border="0">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName"/></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName"/></td>
			</tr>
			<tr>
				<td>Male</td>
				<td><input type="radio" name="gender" value="male"></td>
			</tr>
			<tr>
				<td>Female</td>
				<td><input type="radio" name="gender" value="female"></td>
			</tr>
			<tr>
				<td>About Me</td>
				<td colspan="2"><textarea name="aboutme"></textarea></td>
			</tr>
			<tr>
				<td><input type="Submit" value="Sign Up" /></td>
			</tr>
		</table>
	</form>
</body>
</html>

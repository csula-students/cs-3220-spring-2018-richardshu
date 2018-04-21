<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<!-- Metadata -->
		<meta charset="utf-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>Strawberry Clicker</title>

		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" crossorigin="anonymous">

		<!-- Google Fonts -->
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Bree Serif">
		
		<!-- Styling -->
		<link rel="stylesheet" href="../style.css">
	</head>
	<body>
		<h1 id="title" class="text-center">Incremental Game Framework</h1>
		<nav class="text-center">
			<ul>
				<li><a href="#"><h3>Game Information</h3></a></li>
				<li><a href="#"><h3>Generators</h3></a></li>
				<li><a href="#"><h3>Events</h3></a></li>
			</ul>
		</nav>
		<div class="text-center">
			<form method="POST">
				<input name="generator_name" class="input-box" placeholder="Generator name" required><br/>
				<input name="generator_rate" class="input-box" placeholder="Generator rate" required><br/>
				<input name="base_cost" class="input-box" placeholder="Base cost" required><br/>
				<input name="unlock_at" class="input-box" placeholder="Unlock at" required><br/>
				<textarea name="description" class="input-box" placeholder="Description" required></textarea><br/>
				<button class="btn"><h4>Add</h4></button>
			</form>
		</div>
		

		<!-- Create the table header -->
		<div class="text-center">
			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Rate</th>
						<th>Cost</th>
						<th>Unlock At</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!-- Add each event to the table -->
					<c:forEach items="${generators}" var="generator">
						<tr>
							<td>${generator.getId()}</td>
							<td>${generator.getName()}</td>
							<td>${generator.getRate()}</td>
							<td>${generator.getBaseCost()}</td>
							<td>${generator.getUnlockAt()}</td>
	
							<!-- Send the id through the URL -->
							<td>
								<a href="./generators/edit?id=${generator.getId()}">Edit</a> | 
								<a href="./generators/remove?id=${generator.getId()}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
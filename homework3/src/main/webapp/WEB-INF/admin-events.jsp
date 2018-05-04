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
		<link rel="stylesheet" href="../app.css">
	</head>
	<body>
		<h1 id="title" class="text-center">Incremental Game Framework</h1>
		<nav class="text-center">
			<ul>
				<li><a href="#"><h3>Game Information</h3></a></li>
				<li><a href="generators"><h3>Generators</h3></a></li>
				<li><a href="events"><h3>Events</h3></a></li>
			</ul>
		</nav>
		<div class="text-center">
			<form method="POST">
				<input name="event_name" class="input-box" placeholder="Event name" required><br/>
				<input name="event_description" class="input-box" placeholder="Event description" required><br/>
				<input name="trigger" class="input-box" placeholder="Trigger value" required><br/>
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
						<th>Description</th>
						<th>Trigger At</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!-- Add each event to the table -->
					<c:forEach items="${events}" var="event">
						<tr>
							<td>${event.getId()}</td>
							<td>${event.getName()}</td>
							<td>${event.getDescription()}</td>
							<td>${event.getTriggerAt()}</td>
	
							<!-- Send the id through the URL -->
							<td>
								<a href="./events/edit?id=${event.getId()}">Edit</a> | 
								<a href="./events/remove?id=${event.getId()}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- Log out button -->
			<a href="auth"><h3>Log out</h3></a>
		</div>
	</body>
</html>
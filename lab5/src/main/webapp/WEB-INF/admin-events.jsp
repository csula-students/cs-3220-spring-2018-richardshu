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
		<h1 id='title' class='text-center'>Incremental Game Framework</h1>
		<nav class='text-center'>
			<ul>
				<li><a href='#'><h3>Game Information</h3></a></li>
				<li><a href='#'><h3>Generators</h3></a></li>
				<li><a href='#'><h3>Events</h3></a></li>
			</ul>
		</nav>
		<div class='text-center'>
			<form method='POST'>
				<label for='event_name'></label>
				<input name='event_name' class='input-box' placeholder='Enter the event name:' required><br/>
				<label for='event_description'></label>
				<input name='event_description' class='input-box' placeholder='Enter the event description:' required><br/>
				<label for='trigger'></label>
				<input name='trigger' class='input-box' placeholder='Trigger at:' required><br/>
				<button class='btn'><h4>Add</h4></button>
			</form>
		</div>
		

		<!-- Create the table header -->
		<div class="text-center">
			<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Trigger At</th>
				</tr>

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
			</table>
		</div>
	</body>
</html>
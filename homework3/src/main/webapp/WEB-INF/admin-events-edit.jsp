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
		<h1 id="title" class="text-center">Edit Event</h1>
		
		<!-- Form for editing the events -->
		<form method="POST">
			<input name="event_name" class="input-box left-margin" placeholder="New event name" required><br/>
			<input name="event_description" class="input-box left-margin" placeholder="New event description" required><br/>
			<input name="trigger" class="input-box left-margin" placeholder="New trigger value" required><br/>
			<button class="btn"><h4><a>Edit</a></h4></button>
		</form>
	</body>
</html>
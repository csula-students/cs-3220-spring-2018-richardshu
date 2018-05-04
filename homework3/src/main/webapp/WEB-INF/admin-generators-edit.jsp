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
		<link rel="stylesheet" href="../../app.css">
	</head>
	<body>
		<h1 id="title" class="text-center">Edit Generator</h1>
		
		<!-- Form for editing the generators -->
		<div class="text-center">
			<form method="POST">
				<input name="generator_name" class="input-box" placeholder="Generator name" required><br/>
				<input name="generator_rate" class="input-box" placeholder="Generator rate" required><br/>
				<input name="base_cost" class="input-box" placeholder="Base cost" required><br/>
				<input name="unlock_at" class="input-box" placeholder="Unlock at" required><br/>
				<textarea name="description" class="input-box" placeholder="Description" required></textarea><br/>
				<button class="btn"><h4><a>Edit</a></h4></button>
			</form>
		</div>
	</body>
</html>
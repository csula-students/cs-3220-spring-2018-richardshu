<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Strawberry Clicker</title>
	<link rel="stylesheet" href="../game.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
	<script>
		state = ${state}; // State is passed from the Controrller as a JSON string
	</script>
</head>
<body>
	<h1 id="title">Strawberry Clicker</h1>
	<game-story-book></game-story-book>
	<div id="resource_header">
		<h2>Strawberries: </h2>
		<game-counter></game-counter>
		<game-button>Harvest Strawberry</game-button>
	</div>

	<div class="generators">
		<game-generator data-id="0"></game-generator>
		<game-generator data-id="1"></game-generator>
		<game-generator data-id="2"></game-generator>
	</div>
	<div class="generators">
		<game-generator data-id="3"></game-generator>
		<game-generator data-id="4"></game-generator>
		<game-generator data-id="5"></game-generator>
	</div>

	<!-- Import app.bundle.js -->
	<script src="../app.bundle.js"></script>
</body>
</html>
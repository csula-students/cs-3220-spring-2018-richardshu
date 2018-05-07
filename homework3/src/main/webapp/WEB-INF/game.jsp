<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Strawberry Clicker</title>
	<link rel="stylesheet" href="game.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
	<script>
		state = ${state}; // State is passed from the Controrller as a JSON string

		for (var i = 0; i < state.generators.length; i++) {
			var generator = state.generators[i];
			generator.type = 'autonomous';
	        generator.quantity = 0;
	        generator.unlockValue = generator.unlockAt;
	    }

	    for (var i = 0; i < state.story.length; i++) {
	    	var story = state.story[i];
	    	story.state = 'hidden';
	    	story.triggeredAt = story.triggerAt;
	    }
	    
	    window.defaultState = state; // Set the state in app.bundle.js to be the JSON string passed in
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
		<c:forEach var = "i" begin = "0" end = "${lastGeneratorIndex}">
			<game-generator data-id="${i}"></game-generator>
		</c:forEach>
	</div>

	<!-- Import app.bundle.js -->
	<script src="app.bundle.js"></script>
</body>
</html>
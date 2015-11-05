<html>
<head>
<title>Spring MVC Tutorial by Crunchify - Hello World Spring MVC
	Example</title>
</head>
<body>
	welcome
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$.ajax({
				url : "getList",
				sync : false,
				type : "GET",
				dataType : "json",
				success : function(data) {
					console.log(data);
				}
			});
		});
	</script>
</body>
</html>
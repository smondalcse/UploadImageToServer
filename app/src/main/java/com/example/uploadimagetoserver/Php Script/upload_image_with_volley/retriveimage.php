 <?php
 
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "upload_image_with_volley";

	// Create connection
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	// Check connection
	if ($conn) {
		
		$return_arr = array();
		
		$query = "SELECT * FROM imageinfo";

		$fetch = mysqli_query($conn, $query); 

		while ($row = mysqli_fetch_array($fetch, MYSQL_ASSOC)) {
			$row_array['id'] = $row['id'];
			$row_array['name'] = $row['name'];
			$row_array['url'] = $row['url'];

			array_push($return_arr,$row_array);
		}

		echo json_encode($return_arr);
		
	} else {
		echo json_encode(array('response' => 'Connection Error'));
	}
	
	mysqli_close($conn);
	
?> 
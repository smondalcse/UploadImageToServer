 <?php
 
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "upload_image_with_volley";

	// Create connection
	$conn = mysqli_connect($servername, $username, $password, $dbname);

	// Check connection
	if ($conn) {
		
		//echo json_encode(array('response' => 'Connection Successfully'));
	
		$image = $_POST["image"];
		$img_url = $_POST["img_url"];
		$name = $_POST["name"];
		$sql = "insert into imageinfo(name, url) values('$name', '$img_url')";
		$upload_path = "uploads/$name.jpg";
		
		if(mysqli_query($conn, $sql))
		{
			file_put_contents($upload_path, base64_decode($image));
			echo json_encode(array('response' => 'Image Upload Successfully'));
		} else {
			echo json_encode(array('response' => 'Image Upload Failed'));
		}
		
	} else {
		echo json_encode(array('response' => 'Connection Error'));
	}
	
	mysqli_close($conn);
?> 
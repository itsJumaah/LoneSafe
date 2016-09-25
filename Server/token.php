<?php 

//DELETE IF NOT USING.....
	
	$userId = $_POST["user_id"];
	
	if (isset($_POST["token"])) {
		
		$token=$_POST["token"];
	   	$conn = mysqli_connect("localhost","root","lonely","users") or die("Error connecting");

	 //	$query = 
		$q="INSERT INTO names (token) VALUES ('$token') WHERE user_id = $userId"
          	." ON DUPLICATE KEY UPDATE token = '$token';";

      //   $q = mysqli_prepare($con, "UPDATE names SET token = '$token' WHERE user_id = '3'");
   
      	mysqli_query($conn,$q) or die(mysqli_error($conn));
      	mysqli_close($conn);
	}

 ?>

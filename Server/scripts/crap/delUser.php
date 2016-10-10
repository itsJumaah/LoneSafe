<?PHP

	$host="localhost";
	$dbuser="root";
	$dbpass='lonely';
	$db="users";

	$con = mysqli_connect($host, $dbuser, $dbpass, $db) or die("connection failed");

    $username = $_POST["username"];
   

    $statement = mysqli_prepare($con, "DELETE FROM names WHERE username = ?");
    

    mysqli_stmt_bind_param($statement, "s", $username);
    
    mysqli_stmt_execute($statement);

 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>

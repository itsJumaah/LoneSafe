<?PHP

	$host="localhost";
	$dbuser="root";
	$dbpass='lonely';
	$db="users";

	$con = mysqli_connect($host, $dbuser, $dbpass, $db) or die("connection failed");

	$user_id = $_POST["user_id"];
  //  $startTime = date ("Y-m-s H:i:s", strtotime($_POST["startTime"]));
    $endTime = date ("Y-m-s H:i:s", strtotime($_POST["endTime"]));
    $rskLevel = $_POST["rskLevel"];

    $statement = mysqli_prepare($con, "INSERT INTO job (user_id, startTime, endTime, rskLevel) VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "siss", $user_id, $startTime, $endTime, $rskLevel);
    mysqli_stmt_execute($statement);

 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
?>


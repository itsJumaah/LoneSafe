<?PHP

    require 'var.php';

	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

   // echo date("Y-m-d");

	$username = $_POST["username"];
    $date = date("Y-m-d");
    $workinghours = $_POST["workinghours"];
    $risklevel = $_POST["risklevel"];


    //$cmd = "INSERT INTO jobt (username, workinghours, risklevel) VALUES (?,?,?)";

    $statement = mysqli_prepare($con, "INSERT INTO jobs (username, date, workinghours, risklevel) VALUES (?,?,?,?)");

  //  $statement = mysqli_prepare($con, "INSERT INTO job (user_id, startTime, endTime, rskLevel) VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssii", $username, $date, $workinghours, $risklevel);
    mysqli_stmt_execute($statement);

 	$response = array();
    $response["success"] = true;

    $last_id = mysqli_insert_id($con);
   //	echo "New record created successfully. Last inserted ID is: " . $last_id;


	$response["job_num"] = $last_id;

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>


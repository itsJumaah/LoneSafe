<?PHP

    require 'var.php';

	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");


	if (isset($_POST["onjob"])) {

     	$onjob = $_POST["onjob"];
     	$username = $_POST["username"];

        $cmd = "UPDATE users SET onjob = ? WHERE username = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "is", $onjob, $username);
        
        mysqli_stmt_execute($query); 
  
    } 

	if (isset($_POST["isactive"])) {

     	$isactive = $_POST["isactive"];
     	$job_num = $_POST["job_num"];

     	$response["job_num"] = $job_num;

        $cmd = "UPDATE jobs SET isactive = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "ii", $isactive, $job_num);
        
        mysqli_stmt_execute($query); 
  
    } 



 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>
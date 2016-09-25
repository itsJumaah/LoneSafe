<?PHP

    require 'var.php';

	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

	$username = $_POST["username"];


	if (isset($_POST["onjob"])) {

     	$onjob = $_POST["onjob"];

        $cmd = "UPDATE users SET onjob = ? WHERE username = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "is", $onjob, $username);
        
        mysqli_stmt_execute($query); 
  
    } 


 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>
<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

   	$username = $_POST["username"];

    $cmd = "DELETE FROM jobs WHERE username = ? AND isactive = '1'";

 	$statement1 = mysqli_prepare($con, $cmd);
        
    mysqli_stmt_bind_param($statement1, "s", $username);
        
    mysqli_stmt_execute($statement1);

    //respond the response as json
    
    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>
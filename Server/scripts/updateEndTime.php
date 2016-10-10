<?PHP

    require 'var.php';

	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    $job_num = $_POST["job_num"];

    if (isset($_POST["endtime"])) {

        $endtime = $_POST["endtime"]; 
        $endtime = date('H:i', strtotime($endtime));

        $cmd = "UPDATE jobs SET endtime = ? WHERE job_num = ?";
        
        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "ss", $endtime, $job_num);
            
        mysqli_stmt_execute($query); 
        
    } 


 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>


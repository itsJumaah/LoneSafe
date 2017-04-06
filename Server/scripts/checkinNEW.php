<?PHP

    require 'var.php';

    date_default_timezone_set('NZ');

    $checkin1 = $_POST["checkin1"];
    $checkin2 = $_POST["checkin2"];
    $checkin3 = $_POST["checkin3"];
    $checkin4 = $_POST["checkin4"];
    $checkin5 = $_POST["checkin5"];
    $checkin6 = $_POST["checkin6"];
    $checkin7 = $_POST["checkin7"];
    $checkin8 = $_POST["checkin8"];

    $job_num = $_POST["job_num"];


	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

	
    if ($checkin1 != "null" && $checkin2 == "null") {

        if ($checkin1 == "Checked") {
            $checkin1 = date("H:i:s");
        }
        
        $cmd = "UPDATE jobs SET checkin1 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin1, $job_num);
        
        mysqli_stmt_execute($query); 
        
    } 

    else if ($checkin2 == "Checked" && $checkin3 == "null") {

        $checkin2 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin2 = ? WHERE job_num = ?";
        
        $query = mysqli_prepare($con, $cmd);
        
        mysqli_stmt_bind_param($query, "si", $checkin2, $job_num);
        
        mysqli_stmt_execute($query); 
    
  
    } 

    else if ($checkin3 == "Checked" && $checkin4 == "null") {

        $checkin3 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin3 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin3, $job_num);
        
        mysqli_stmt_execute($query); 
        
    } 

    else if ($checkin4 == "Checked" && $checkin5 == "null") {

        $checkin4 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin4 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin4, $job_num);
        
        mysqli_stmt_execute($query); 
           

    } 
    else if ($checkin5 == "Checked" && $checkin6 == "null") {

        $checkin5 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin5 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin5, $job_num);
        
        mysqli_stmt_execute($query);  
        
    } 

    else if ($checkin6 == "Checked" && $checkin7 == "null") {

        $checkin6 = date("H:i:s"); 

        $cmd = "UPDATE jobs SET checkin6 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin6, $job_num);
        
        mysqli_stmt_execute($query); 
        
    } 

    else if ($checkin7 == "Checked" && $checkin8 == "null") {

        $checkin7 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin7 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin7, $job_num);
  
        mysqli_stmt_execute($query); 
         
    }

    else if ($checkin8 == "Checked") {

        $checkin8 = date("H:i:s");

        $cmd = "UPDATE jobs SET checkin8 = ? WHERE job_num = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $checkin8, $job_num);
    
        mysqli_stmt_execute($query); 
        
    } 

 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>


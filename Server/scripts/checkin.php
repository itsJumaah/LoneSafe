<?PHP

    require 'var.php';

	$con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

	$job_num = $_POST["job_num"];

    if (isset($_POST["checkin1"])) {

        $checkin1 = $_POST["checkin1"];
         if ($checkin1 != "null"){

            $cmd = "UPDATE jobs SET checkin1 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin1, $job_num);
            
            mysqli_stmt_execute($query); 
        }
    } 

      if (isset($_POST["checkin2"])) {

        $checkin2 = $_POST["checkin2"];
        if ($checkin2 != "null"){

            $cmd = "UPDATE jobs SET checkin2 = ? WHERE job_num = ?";
            $query = mysqli_prepare($con, $cmd);
            mysqli_stmt_bind_param($query, "si", $checkin2, $job_num);
            mysqli_stmt_execute($query); 
        }
  
    } 
    if (isset($_POST["checkin3"])) {

        $checkin3 = $_POST["checkin3"];
        if ($checkin3 != "null"){
            $cmd = "UPDATE jobs SET checkin3 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin3, $job_num);
            
            mysqli_stmt_execute($query); 
        }  
    } 

    if (isset($_POST["checkin4"])) {

        $checkin4 = $_POST["checkin4"];

        if($checkin4 != "null"){
             $cmd = "UPDATE jobs SET checkin4 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin4, $job_num);
            
            mysqli_stmt_execute($query); 
        }     

    } 
    if (isset($_POST["checkin5"])) {

        $checkin5 = $_POST["checkin5"];

        if($checkin5 != "null"){
           $cmd = "UPDATE jobs SET checkin5 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin5, $job_num);
            
            mysqli_stmt_execute($query);  
        }
    } 
    if (isset($_POST["checkin6"])) {

        $checkin6 = $_POST["checkin6"];

        if($checkin6 != "null"){
            $cmd = "UPDATE jobs SET checkin6 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin6, $job_num);
            
            mysqli_stmt_execute($query); 
        }
  
    } 
    if (isset($_POST["checkin7"])) {

        $checkin7 = $_POST["checkin7"];

        if($checkin7 != "null"){
            $cmd = "UPDATE jobs SET checkin7 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin7, $job_num);
      
            mysqli_stmt_execute($query); 
        }     
    } 
    if (isset($_POST["checkin8"])) {

        $checkin8 = $_POST["checkin8"];

        if($checkin8 != "null"){
            $cmd = "UPDATE jobs SET checkin8 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin8, $job_num);
        
            mysqli_stmt_execute($query); 
        }
    } 

 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>

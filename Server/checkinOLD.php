<?PHP

	$host="localhost";
	$dbuser="root";
	$dbpass='lonely';
	$db="users";

	$con = mysqli_connect($host, $dbuser, $dbpass, $db) or die("connection failed");

	$job_num = $_POST["job_num"];

    if (isset($_POST["checkin1"])) {

        $checkin1 = $_POST["checkin1"];
         if ($checkin1 != "null"){

            $cmd = "UPDATE jobt SET checkin1 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin1, $job_num);
            
            mysqli_stmt_execute($query); 
        }
    } 

      if (isset($_POST["checkin2"])) {

        $checkin2 = $_POST["checkin2"];
        if ($checkin2 != "null"){

            $cmd = "UPDATE jobt SET checkin2 = ? WHERE job_num = ?";
            $query = mysqli_prepare($con, $cmd);
            mysqli_stmt_bind_param($query, "si", $checkin2, $job_num);
            mysqli_stmt_execute($query); 
        }
  
    } 
    if (isset($_POST["checkin3"])) {

        $checkin3 = $_POST["checkin3"];
        if ($checkin3 != "null"){
            $cmd = "UPDATE jobt SET checkin3 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin3, $job_num);
            
            mysqli_stmt_execute($query); 
        }  
    } 

    if (isset($_POST["checkin4"])) {

        $checkin4 = $_POST["checkin4"];

        if($checkin4 != "null"){
             $cmd = "UPDATE jobt SET checkin4 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin4, $job_num);
            
            mysqli_stmt_execute($query); 
        }     

    } 
    if (isset($_POST["checkin5"])) {

        $checkin5 = $_POST["checkin5"];

        if($checkin5 != "null"){
           $cmd = "UPDATE jobt SET checkin5 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin5, $job_num);
            
            mysqli_stmt_execute($query);  
        }
    } 
    if (isset($_POST["checkin6"])) {

        $checkin6 = $_POST["checkin6"];

        if($checkin6 != "null"){
            $cmd = "UPDATE jobt SET checkin6 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin6, $job_num);
            
            mysqli_stmt_execute($query); 
        }
  
    } 
    if (isset($_POST["checkin7"])) {

        $checkin7 = $_POST["checkin7"];

        if($checkin7 != "null"){
            $cmd = "UPDATE jobt SET checkin7 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin7, $job_num);
      
            mysqli_stmt_execute($query); 
        }     
    } 
    if (isset($_POST["checkin8"])) {

        $checkin8 = $_POST["checkin8"];

        if($checkin8 != "null"){
            $cmd = "UPDATE jobt SET checkin8 = ? WHERE job_num = ?";

            $query = mysqli_prepare($con, $cmd);

            mysqli_stmt_bind_param($query, "si", $checkin8, $job_num);
        
            mysqli_stmt_execute($query); 
        }
    } 

//    $statement = mysqli_prepare($con, "INSERT INTO jobt (checkin1, checkin2, checkin3, checkin4, checkin5, 
 //       checkin6, checkin7, checkin8) VALUES (?,?,?,?,?,?,?,?)");

  //  $statement = mysqli_prepare($con, "INSERT INTO job (user_id, startTime, endTime, rskLevel) VALUES (?,?,?,?)");
   // mysqli_stmt_bind_param($statement, "ssssssss", $checkin1, $checkin2, $checkin3, $checkin4,
   //     $checkin5, $checkin6, $checkin7, $checkin8);
  //  mysqli_stmt_execute($statement);

 	$response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>


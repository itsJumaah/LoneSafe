<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $secret = $_POST["ABEX"];

    //-----------------------------

    $cmd2 = "SELECT * FROM sec WHERE MEGATRON = ?";

    $statement2 = mysqli_prepare($con, $cmd2);
    mysqli_stmt_bind_param($statement2, "s", $secret);
    mysqli_stmt_execute($statement2);
    
    mysqli_stmt_store_result($statement2);

    mysqli_stmt_bind_result($statement2, $secret);

    //-----------------------------

    //prepare the response and initialize it to false
    $response = array();
    $response["success"] = false;  
    
    //-----------------------------

    while(mysqli_stmt_fetch($statement2)){
        //if the security statement run then make it true
        $response["success"]  = true;
    }

    //-----------------------------

    if($response["success"]==true) { //if its true then delete
        
//***************************ACTUAL CODE BEGINS**********************************************
    
      $cmd = "SELECT users.firstname, users.lastname, users.username, users.mobile, users.phone, users.email, users.rego, jobs.starttime, jobs.endtime, jobs.risklevel, jobs.needsos, jobs.checkin1, jobs.checkin2, jobs.checkin3, jobs.checkin4, jobs.checkin5, jobs.checkin6, jobs.checkin7, jobs.checkin8, location.time, location.latitude, location.longitude FROM jobs JOIN users ON (jobs.username = users.username) JOIN location ON (jobs.job_num = location.job_num) WHERE users.onjob = 1 AND jobs.isactive = 1";

  
      $statement = mysqli_prepare($con, $cmd);

      mysqli_stmt_execute($statement);
      
      mysqli_stmt_store_result($statement);

      mysqli_stmt_bind_result($statement, $firstname, $lastname, $username, $mobile, $phone, $email, $rego, $starttime, $endtime, $risklevel, $needsos, $checkin1, $checkin2, $checkin3, $checkin4, $checkin5, $checkin6, $checkin7, $checkin8, $emtime, $latitude, $longitude);

      $users = array();
      while(mysqli_stmt_fetch($statement)){
         
        $users[]=array(
              'LiveUser'=> array(
                    'firstname' => $firstname,
                    'lastname'  => $lastname,
                    'username'  => $username,
                    'mobile'    => $mobile,
                    'phone'     => $phone,
                    'email'     => $email,
                    'rego'      => $rego,
                    'starttime' => $starttime,
                    'endtime'   => $endtime,
                    'risklevel' => $risklevel,
                    'needsos'   => $needsos,
                    'checkin1'  => $checkin1,
                    'checkin2'  => $checkin2,
                    'checkin3'  => $checkin3,
                    'checkin4'  => $checkin4,
                    'checkin5'  => $checkin5,
                    'checkin6'  => $checkin6,
                    'checkin7'  => $checkin7,
                    'checkin8'  => $checkin8,
                    'emtime'    => $emtime,
                    'latitude'  => $latitude,
                    'longitude' => $longitude,
              ),
          );
      }


//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    //echo json_encode($response);
    echo json_encode($users);
    mysqli_close($con);
?>
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
    
      $cmd = "SELECT id, firstname, lastname, username, manager, onjob, mobile, phone, email, rego FROM users";
  
      $statement = mysqli_prepare($con, $cmd);

      mysqli_stmt_execute($statement);
      
      mysqli_stmt_store_result($statement);

      mysqli_stmt_bind_result($statement, $id, $firstname, $lastname, $username, $manager, $onjob, $mobile, $phone, $email, $rego);


      while(mysqli_stmt_fetch($statement)){
         
        $users[]=array(
              'DisplayUser'=> array(
                    'id'        => $id,
                    'firstname' => $firstname,
                    'lastname'  => $lastname,
                    'username'  => $username,
                    'manager'   => $manager,
                    'onjob'     => $onjob,
                    'mobile'    => $mobile,
                    'phone'     => $phone,
                    'email'     => $email,
                    'rego'      => $rego,
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

<?PHP

    require 'var.php';

    $job_num = $_POST["job_num"];

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $secret = "b5a15c2a6bf6a815e426e97cdac732261840e16e92c46f80a3077f7f236cf148";

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
      
      $cmd = "SELECT firstbool, secondbool FROM coverage WHERE job_num = ?";

  
      $statement = mysqli_prepare($con, $cmd);

      mysqli_stmt_bind_param($statement, "i", $job_num);

      mysqli_stmt_execute($statement);
      
      mysqli_stmt_store_result($statement);

      mysqli_stmt_bind_result($statement, $firstbool, $secondbool);

      $coverage = array();
      while(mysqli_stmt_fetch($statement)){
        $coverage[]=array(
              'Coverage'=> array(
                    'firstbool' => $firstbool,
                    'secondbool'  => $secondbool,
              ),
          );
      }



//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    //echo json_encode($response);
    echo json_encode($coverage);
    mysqli_close($con);
?>
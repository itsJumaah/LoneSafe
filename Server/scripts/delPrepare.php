<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $username = $_POST["username"];
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
    
        $cmd = "SELECT * FROM users WHERE username = ?";

        $statement = mysqli_prepare($con, $cmd);
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        
        mysqli_stmt_store_result($statement);

        mysqli_stmt_bind_result($statement, $id, $firstname, $lastname, $username, $password, $manager, $onjob, $mobile, $phone, $email, $rego, $token);
        
        $response = array();
        $response["success"] = false;  
        
        while(mysqli_stmt_fetch($statement)){
           
            $response["success"]    = true;  

            $response["id"]         = $id;
            $response["firstname"]  = $firstname;
            $response["lastname"]   = $lastname;
            $response["username"]   = $username;
            $response["manager"]    = $manager;
            $response["onjob"]      = $onjob;
            $response["mobile"]     = $mobile;
            $response["phone"]      = $phone;
            $response["email"]      = $email;
            $response["rego"]       = $rego;
        }


//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($response);
    mysqli_close($con);
?>
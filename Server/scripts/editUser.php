<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $firstname = $_POST["firstname"];
    $lastname  = $_POST["lastname"];
    $username  = $_POST["username"];
    $mobile    = $_POST["mobile"];
    $phone     = $_POST["phone"];
    $email     = $_POST["email"];
    $rego      = $_POST["rego"];

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
    
        $cmd = "UPDATE users SET firstname = '$firstname', lastname = '$lastname', mobile = '$mobile', phone = '$phone', email = '$email', rego = '$rego' WHERE username = '$username'";

        $statement = mysqli_prepare($con, $cmd);
    
        // mysqli_stmt_bind_param($statement, "ssssssss", $firstname, $lastname, $mobile, $phone, $email, $rego, $username);
    
        mysqli_stmt_execute($statement);


//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($response);
    mysqli_close($con);
?>


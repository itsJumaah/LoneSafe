<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $username = $_POST["username"];
    $secret = $_POST["ABEX"];

    //-----------------------------

    $cmd = "SELECT * FROM sec WHERE MEGATRON = ?";

    $statement = mysqli_prepare($con, $cmd);
    mysqli_stmt_bind_param($statement, "s", $secret);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);

    mysqli_stmt_bind_result($statement, $secret);

    //-----------------------------

    //prepare the response and initialize it to false
    $response = array();
    $response["success"] = false;  
    
    //-----------------------------

    while(mysqli_stmt_fetch($statement)){
        //if the security statement run then make it true
        $response["success"]  = true;
    }

    //-----------------------------

    if($response["success"]==true) { //if its true then delete
        
//***************************ACTUAL CODE BEGINS**********************************************

        $cmd1 = "DELETE FROM users WHERE username = ?";

        $statement1 = mysqli_prepare($con, $cmd1);
        
        mysqli_stmt_bind_param($statement1, "s", $username);
        
        mysqli_stmt_execute($statement1);

//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($response);
    mysqli_close($con);
?>

<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $username = $_POST["username"];
    $checkin = $_POST["checkin"];
    $esc     = $_POST["esc"];

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
        
        if($checkin == "checkin1") {
            $cmd1 = "UPDATE jobs SET checkin1 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin2") {
            $cmd1 = "UPDATE jobs SET checkin2 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin3") {
            $cmd1 = "UPDATE jobs SET checkin3 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin4") {
            $cmd1 = "UPDATE jobs SET checkin4 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin5") {
            $cmd1 = "UPDATE jobs SET checkin5 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin6") {
            $cmd1 = "UPDATE jobs SET checkin6 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin7") {
            $cmd1 = "UPDATE jobs SET checkin7 = '$esc' WHERE username = '$username'";
        }
        else if($checkin == "checkin8") {
            $cmd1 = "UPDATE jobs SET checkin8 = '$esc' WHERE username = '$username'";
        }
        
        $statement1 = mysqli_prepare($con, $cmd1);
        
        mysqli_stmt_execute($statement1);


//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($response);
    mysqli_close($con);
?>

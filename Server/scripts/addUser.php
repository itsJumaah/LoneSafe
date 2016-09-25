<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $firstname = $_POST["firstname"];
    $lastname  = $_POST["lastname"];
    $username  = $_POST["username"];
    $password  = $_POST["password"];
    $manager   = $_POST["manager"];
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
    
        $cmd = "INSERT INTO users (firstname, lastname, username, password, manager, mobile, phone, email, rego) VALUES (?,?,?,?,?,?,?,?,?)";

        $statement = mysqli_prepare($con, $cmd);
        
        mysqli_stmt_bind_param($statement, "ssssissss", $firstname, $lastname, $username, $password, $manager, $mobile, $phone, $email, $rego);
        
        mysqli_stmt_execute($statement);


//***************************ACTUAL CODE ENDS************************************************
    }

    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($response);
    mysqli_close($con);

?>
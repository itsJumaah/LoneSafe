<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    //-----------------------------

    $username = $_POST["username"];
    $password = $_POST["password"];

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
    
       // $cmd = "SELECT * FROM users WHERE username = ? AND password = ?";
        $cmd = "SELECT * FROM users WHERE username = '$username' AND password = '$password'";

        $statement = mysqli_prepare($con, $cmd);
       // mysqli_stmt_bind_param($statement, "ss", $username, $password);
        mysqli_stmt_execute($statement);
        
        mysqli_stmt_store_result($statement);

        mysqli_stmt_bind_result($statement, $id, $firstname, $lastname, $username, $password, $manager, $onjob, $mobile, $phone, $email, $rego, $token);
        
        

        
        $user = array();
        $user["success"]    = false;
        
        while(mysqli_stmt_fetch($statement)){
            

            $user["success"]    = true;  

            $user["id"]         = $id;
            $user["firstname"]  = $firstname;
            $user["lastname"]   = $lastname;
            $user["username"]   = $username;
            $user["manager"]    = $manager;
            $user["onjob"]      = $onjob;
            $user["mobile"]     = $mobile;
            $user["phone"]      = $phone;
            $user["email"]      = $email;
            $user["rego"]       = $rego;
        }


//***************************ACTUAL CODE ENDS************************************************
    }

    if (isset($_POST["token"])) {

        $token=$_POST["token"];

        $cmd = "UPDATE users SET token = ? WHERE id = ?";

        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "si", $token, $id);
        
        mysqli_stmt_execute($query);   
    } 



    //-----------------------------

    //respond the response as json
    header("Content-Type: text/json");
    echo json_encode($user);
    mysqli_close($con);
?>
<?php

 
    $con = mysqli_connect("localhost", "db_user", "dbpass", "users");
    
    $username = $_POST["username"];
    //$password = $_POST["password"];
    //$hash_password = hash('sha1', $_POST["password"]);
    $password = sha1($_POST["password"]); //hash the password
    $statement = mysqli_prepare($con, "SELECT * FROM names WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password); //$password
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $name, $username, $age, $password, $isAdmin, $token);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
       
        $response["success"] = true;  
        $response["name"] = $name;
        $response["age"] = $age;
        $response["user_id"] = $user_id;
        $response["username"] = $username;
        $response["password"] = $password;
        $response["isAdmin"] = $isAdmin;

    }

     echo json_encode($response);

        if (isset($_POST["token"])) {
            $token=$_POST["token"];
            $query = mysqli_prepare($con, "UPDATE names SET token = '$token' WHERE user_id = $user_id");
            mysqli_stmt_bind_param($query, "s", $token);
            mysqli_stmt_execute($query);   
        } 

?>
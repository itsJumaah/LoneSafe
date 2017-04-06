<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    $job_num = $_POST["job_num"];
    $firstbool = 1;
    $secondbool = 0;


     $cmd = "INSERT INTO coverage (job_num, firstbool, secondbool) VALUES (?,?,?)";
            
        $query = mysqli_prepare($con, $cmd);

        mysqli_stmt_bind_param($query, "iii", $job_num, $firstbool, $secondbool);
                
        mysqli_stmt_execute($query); 


    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>
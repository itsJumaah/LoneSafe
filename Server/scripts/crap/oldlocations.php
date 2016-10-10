<?PHP

    require 'var.php';

    $con = mysqli_connect(DATAHOST, DATAUSER, DATAPASS, DATABASE) or die("connection failed");

    $job_num = $_POST["job_num"];
    date_default_timezone_set("Pacific/Auckland");
    $time = date("h:i:sa");
    $longitude = $_POST["lng"];
    $latitude = $_POST["lat"];

    $longitude = doubleval($longitude);
    $latitude = doubleval($latitude);


    $cmd = "INSERT INTO location (job_num, time, longitude, latitude) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE time = VALUES(time), longitude = VALUES(longitude), latitude = VALUES(latitude)";
        
    $query = mysqli_prepare($con, $cmd);

    mysqli_stmt_bind_param($query, "isdd", $job_num, $time, $longitude, $latitude);
            
    mysqli_stmt_execute($query); 
        



    $response = array();
    $response["success"] = true;  

    echo json_encode($response);
    
    mysqli_close($con); //closes the connection

?>


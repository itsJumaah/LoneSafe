<?PHP

	$host="localhost";
	$dbuser="root";
	$dbpass='lonely';
	$db="users";

	$con = mysqli_connect($host, $dbuser, $dbpass, $db) or die("connection failed");

	$name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $isAdmin = $_POST["isAdmin"];

    $statement = mysqli_prepare($con, "INSERT INTO names (name, username, password, isAdmin) VALUES (?,?,?,?)");
    

    mysqli_stmt_bind_param($statement, "sssi", $name, $username, $password, $isAdmin);
    
    mysqli_stmt_execute($statement);

 	$response = array();
    
    $response["success"] = true;  

    echo json_encode($response);
?>


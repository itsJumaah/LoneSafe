<?php
	require 'mail/PHPMailerAutoload.php';

	$eventType = $_POST["eType"];

	$adminFname = $_POST["aFname"];
	$adminLname = $_POST["aLname"];

	$userFname = $_POST["uFname"];
	$userLname = $_POST["uLname"];
	$userFullname = $userFname . " " . $userLname;
	$username = $_POST["username"];

	$eventTime = $_POST["eTime"];

	$risklevel = $_POST["risklevel"];
	$startTime = $_POST["startTime"];
	$endTime = $_POST["endTime"];

	$mobile = $_POST["mobile"];
	$email = $_POST["email"];
	$phone = $_POST["phone"];
	$rego = $_POST["rego"];

	$longi = $_POST["longi"];
	$lati = $_POST["lati"];

	$adminEmail = $_POST["aemail"];
	$adminFullname = $adminFname . " " . $adminLname;

	if($adminEmail != null || $adminEmail != "") {
		
		$mail = new PHPMailer;


		$mail->isSMTP();                                      // Set mailer to use SMTP
		$mail->Host = 'smtp.gmail.com';  						// Specify main and backup SMTP servers
		$mail->SMTPAuth = true;                               // Enable SMTP authentication
		$mail->Username = 'lonesafeapp@gmail.com';                 // SMTP username
		$mail->Password = 'Lonesafe2016';                           // SMTP password
		$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
		$mail->Port = 587;                                    // TCP port to connect to

		$mail->setFrom('lonesafeapp@gmail.com', 'LoneSafe');
		$mail->addAddress($adminEmail, $adminFullname);     // Add a recipient
		$mail->addReplyTo('dont-reply@lonesafe.cu.cc', 'Dont Reply');
		$mail->isHTML(true);                                  // Set email format to HTML

		$mail->Subject = '[LoneSafe] ' . $eventType . ' Alert!';
		$mail->Body    = 'Hello ' . $adminFname . ',<br/><br/>' .

     	'The user <b>' . $userFullname . '</b> (' . $username . ') has triggered ' .
    	 $eventType . ' event at ' . $eventTime . '! Please make sure someone is following up on this.<br/><br/><hr/>' .


    	 '<u>More Info about this event</u>:<br/>' .
    	 'Risk of level: ' . $risklevel . '<br/>' .
    	 'Started at: ' . $startTime . '<br/>' .
    	 'Ends at: ' . $endTime . '<br/><br/><u>' .

     	$userFname . '\'s Details</u>:<br/>' .
     	'Mobile: <b>' . $mobile . '</b><br/>' .
    	 'Email: ' . $email . '<br/>' .
    	 'Home phone: ' . $phone . '<br/>' .
    	 'Vehicle Rego: ' . $rego . '<br/><br/>' .
     
    	 '<u>Last known location</u>: <br/>' .
    	 '<a href="https://www.google.com/maps?q=loc:'. $lati . ',' . $longi .'">Latitude: ' . $lati . '   Longitude: ' . $longi .

    	 '</a><br/><br/><br/><hr/>' .

    	 'You have recieved this email because you were logged in to LoneSafe system during this event.<br/><br/>' .


    	 'If you wish to stop recieving this email, ask an admin to remove it from the system.<br/><br/>' .


     	'This is an auto generated email, <b>please do NOT reply!</b><br/><br/><br/>' .



     	'Thanks,<br/>LoneSafe<br/>';







		if(!$mail->send()) {
	  	  echo 'Message could not be sent.';
	  	  echo 'Mailer Error: ' . $mail->ErrorInfo;
		} 
		else {
	    echo 'Message has been sent to ' . $adminEmail;
		}

	}

	else {
		echo "Admin email is null";
	}
	
?>
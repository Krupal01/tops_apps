<?php

    $server="localhost";
    $username="root";
    $password="";
    $dbName="feb22";

    $conn=new mysqli($server, $username, $password, $dbName);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $flag=$_GET["flag"];

    switch($flag){
        case "INSERT":
            insertUser($conn);
            break;
        case "SELECT":
            fetchUser($conn);
            break;
        case "UPDATE":
            updateUser($conn);
            break;
        case "DELETE":
            deleteUser($conn);
            break;
    }

    function updateUser($conn){
        $firstName=$_POST['first_name'];
        $lastName=$_POST['last_name'];
        $email=$_POST['email'];
        $mobile=$_POST['mobile'];
        $id=$_POST['id'];

        $sql="update user set first_name='$firstName', last_name='$lastName', email='$email', mobile='$mobile' where id=$id";

        if ($conn->query($sql) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
          
        $conn->close();

    }

    function deleteUser($conn){
        $id=$_POST['id'];

        $sql="delete from user where id=$id";

        if ($conn->query($sql) === TRUE) {
            echo "Record deleted successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
        
        $conn->close();
    }

    function fetchUser($conn){
        $sql="select * from user";

        $result=$conn->query($sql);

        if ($result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
                $data[]=$row;
            }
            echo json_encode($data);
        } else {
            echo "0 results";
        }
        $conn->close();

}

    function insertUser($conn){
        $firstName=$_POST['first_name'];
        $lastName=$_POST['last_name'];
        $email=$_POST['email'];
        $mobile=$_POST['mobile'];

        $sql="insert into user (first_name, last_name, email, mobile) values ('$firstName','$lastName','$email','$mobile')";

        if ($conn->query($sql) === TRUE) {
            echo "New record created successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
          
        $conn->close();
    }
?>
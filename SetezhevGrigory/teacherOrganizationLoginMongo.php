<?php
require 'vendor/autoload.php';

$db = new MongoDB\Client("mongodb://localhost:27017");
?>
<html>
<meta charset="UTF-8">
<head>
    <title>Communication Portal</title>
    <style>
        div.container {
            width: 100%;
            border: 1px solid gray;
        }

        header, footer {
            padding: 1em;
            color: white;
            background-color: black;
            clear: left;
            text-align: center;
        }

        nav {
            float: left;
            max-width: 160px;
            margin: 0;
            padding: 1em;
        }

        nav ul {
            list-style-type: none;
            padding: 0;
        }

        nav ul a {
            text-decoration: none;
        }

        article {
            margin-left: 170px;
            border-left: 1px solid gray;
            padding: 1em;
            overflow: hidden;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
        }

        a:link {
            color: gray;
            background-color: transparent;
            text-decoration: none;
        }

        a:visited {
            color: gray;
            background-color: transparent;
            text-decoration: none;
        }

        a:hover {
            color: blue;
            background-color: transparent;
            text-decoration: underline;
        }

        a:active {
            color: blue;
            background-color: transparent;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">

    <header>
        <h1>Communication Portal</h1>
    </header>
    <p>Write your full name and password to get access to scheduling system.</p>
    <p><b>Step 1 of 2</b></p>
    <div>
        <form id='insertform' action='teacherOrganizationLoginMongo.php' method='post'>
            <fieldset>
                <legend><b>Teacher/organization name and password:</b><br></legend>
                Full name:<br>
                <input id='username' name='username' type='text' size='20' value='' required/><br>
                Password:<br>
                <input id='password' name='password' type='password' size='20' value='' required/><br>
                <input id='submit' type='submit' value='Login as teacher/organization'/><br>
            </fieldset>
        </form>
    </div>
    <?php
    //handle insert
    if (isset($_POST['username'])) {
            $userCollection = $db->IMSE->User;
            $loginPasswordData = $userCollection->findOne(['login_user_name' => $_POST['username']]);

            //$teacherPasswordQuery = pg_query_params($db, 'SELECT password FROM login WHERE user_name = $1', array($_POST['username']));

            if (is_null($loginPasswordData)) {
                echo "<script type='text/javascript'>alert('There is no record about this teacher/organization in login database.')</script>";
            } else {
                $teacherPassword = $loginPasswordData['password'];
                if (!($teacherPassword === $_POST['password'])) {
                    echo "<script type='text/javascript'>alert('The password does not match.')</script>";
                } else {
                    header("Location: selectCourseMongo.php?teacherId=" . $_POST['username']);
                }
            }
    }
    ?>

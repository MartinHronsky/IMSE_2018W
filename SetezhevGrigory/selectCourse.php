<?php
   $host        = "host = 127.0.0.1";
   $port        = "port = 5432";
   $dbname      = "dbname = imse";
   $credentials = "user = postgres password=password";

   $db = pg_connect( "$host $port $dbname $credentials"  );

   if (isset($_REQUEST['teacherId']))
       $teacherId = $_REQUEST['teacherId'];
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
    <p>Select the appropriate date and time for your course/exam.</p> <p><b>Step 2 of 2</b></p>
<div>
  <form id='insertform' action='selectCourse.php' method='post'>
      <fieldset>
          <legend><b>Choose date/time:</b><br></legend>
          <?php

          if (isset($teacherId)) {
              $courseListQuery = pg_query_params($db, 'SELECT id_course, language, type_of_the_course FROM course WHERE id_teacher = $1', array($teacherId));

              echo "<select name='courses'>";
              while ($row = pg_fetch_array($courseListQuery)) {
                  echo "<option value='" . $row['id_course'] . "'>" . ($row['language'] . '-' . $row['type_of_the_course']) . "</option>";
              }
              echo "</select>";

              $locationListQuery = pg_query($db, 'SELECT id_location, note FROM location');

              echo "<select name='locations'>";
              while ($row = pg_fetch_array($locationListQuery)) {
                  echo "<option value='" . $row['id_location'] . "'>" . $row['note'] . "</option>";
              }
              echo "</select>";
          }

          ?>
          <input type="date" id="start" name="date_from">
          <input type="date" id="stop" name="date_to">

          <select name='PartOfTheDay'>
              <option value="Morning schedule">Morning schedule</option>
              <option value="Afternoon schedule">Afternoon schedule</option>
              <option value="Evening schedule">Evening schedule</option>
              <option value="Time upon agreement">Time upon agreement</option>
          </select>
       <input id='submit' type='submit' value='Insert!' /><br>
      </fieldset>
  </form>
</div>
<?php
   //handle insert
    $timeMismatch = false;
    if(isset($_REQUEST['courses'])){
        $dateFrom = strtotime($_REQUEST['date_from']);
        $dateTo = strtotime($_REQUEST['date_to']);
        $locationDatesQuery = pg_query_params($db,'SELECT date_from, date_to, note FROM schedule WHERE id_location = $1', array($_POST['locations']));
        while($row = pg_fetch_array($locationDatesQuery))
        {
            $currentDateFrom = strtotime($row["date_from"]);
            $currentDateTo = strtotime($row["date_to"]);

            if ((($dateFrom < $currentDateFrom) && ($currentDateFrom < $dateTo)) ||
            (($dateFrom < $currentDateTo) && ($currentDateTo < $dateTo)) ||
            (($currentDateFrom < $dateFrom) && ($dateTo < $currentDateTo)))
                if (($_REQUEST['PartOfTheDay'] === 'agreement') || ($row['note'] === 'Time upon agreement') || ($_REQUEST['PartOfTheDay'] === $row['note']))
                {
                    $timeMismatch = true;
                    break;
                }
        }

        if ($timeMismatch) {
            echo "<script type='text/javascript'>alert('The time you have selected is already taken.')</script>";
        }
        else {
            $dateInsertQuery = "INSERT INTO schedule(id_course, id_location, date_from, date_to, note) VALUES ('" . $_REQUEST['courses'] . "','" . $_REQUEST['locations'] . "','" . date("Y-m-d", $dateFrom) . "','" . date("Y-m-d", $dateTo) . "','" . $_REQUEST['PartOfTheDay'] . "');";
            $dateInsertResult = pg_query($db, $dateInsertQuery);
            if (!$dateInsertResult) {
                echo "<script type='text/javascript'>alert('Error in inserting to schedule database.')</script>";
            } else {
                echo "<script type='text/javascript'>alert('Schedule was successfully updated.')</script>";
            }
        }
    }
   pg_close($db);
?>

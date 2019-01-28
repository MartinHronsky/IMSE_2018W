<?php
require 'vendor/autoload.php';


    $db = new MongoDB\Client("mongodb://localhost:27017");

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
  <form id='insertform' action='selectCourseMongo.php' method='post'>
      <fieldset>
          <legend><b>Choose date/time:</b><br></legend>
          <?php

          if (isset($teacherId)) {
              $courseCollection = $db->IMSE->Course;
              $courseListDistinct = $courseCollection->distinct('id_course', ['id_teacher' => $teacherId]);

              echo "<select name='courses'>";
              foreach ($courseListDistinct as $uniqueCourseId) {
                  $courseDocument = $courseCollection->findOne(['id_course' => $uniqueCourseId]);
                  echo "<option value='" . $courseDocument['id_course'] . "'>" . ($courseDocument['language'] . '-' . $courseDocument['type_of_the_course']) . "</option>";
              }
              echo "</select>";

              $locationCollection = $db->IMSE->Course;
              $locationListDistinct = $locationCollection->distinct("id_location");

              echo "<select name='locations'>";
              foreach ($locationListDistinct as $uniqueLocationId) {
                  $locationDocument = $locationCollection->findOne(['id_location' => $uniqueLocationId]);
                  echo "<option value='" . $locationDocument['id_location'] . "'>" . $locationDocument['location_note'] . "</option>";
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
        $locationCollection = $db->IMSE->Course;
        $locationListCursor = $locationCollection->find(['id_location' => $_POST['locations']]);
        foreach ($locationListCursor as $locationDocument)
        {
            $currentDateFrom = strtotime($locationDocument["schedule_date_from"]);
            $currentDateTo = strtotime($locationDocument["schedule_date_to"]);

            if ((($dateFrom < $currentDateFrom) && ($currentDateFrom < $dateTo)) ||
            (($dateFrom < $currentDateTo) && ($currentDateTo < $dateTo)) ||
            (($currentDateFrom < $dateFrom) && ($dateTo < $currentDateTo)))
                if (($_REQUEST['PartOfTheDay'] === 'agreement') || ($locationDocument['schedule_note'] === 'Time upon agreement') || ($_REQUEST['PartOfTheDay'] === $locationDocument['schedule_note']))
                {
                    $timeMismatch = true;
                    break;
                }
        }

        if ($timeMismatch) {
            echo "<script type='text/javascript'>alert('The time you have selected is already taken.')</script>";
        }
        else {
            $resultCourseCollection = $db->IMSE->Course;
            $resultCourse = $resultCourseCollection->findOne(['id_course' => $_REQUEST['courses']]);

            $resultLocationCollection = $db->IMSE->Course;
            $resultLocation = $resultLocationCollection->findOne(['id_location' => $_REQUEST['locations']]);

            $resultCourse['id_location'] = $resultLocation['id_location'];
            $resultCourse['schedule_date_from'] = date("Y-m-d", $dateFrom);
            $resultCourse['schedule_date_to'] = date("Y-m-d", $dateTo);
            $resultCourse['schedule_note'] = $_REQUEST['PartOfTheDay'];
            $resultCourse['room_capacity'] = $resultLocation['room_capacity'];
            $resultCourse['location_note'] = $resultLocation['location_note'];
            $resultCourse['_id'] = new \MongoDB\BSON\ObjectId();

            $resultCourseInserted = $resultCourseCollection->insertOne($resultCourse);
            if (!$resultCourseInserted->isAcknowledged()) {
                echo "<script type='text/javascript'>alert('Error in inserting to schedule database.')</script>";
            } else {
                echo "<script type='text/javascript'>alert('Schedule was successfully updated.')</script>";
            }
        }
    }
?>

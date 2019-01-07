<%@ page import = "StudentRegistrationDatabase.*" %>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <style media="screen">
    div{
      background-color: silver;
      border: 1px solid silver;
      border-radius: 5px;
      padding: 20px;
    }
    input[type=text], input[type=date] , select{
      padding: 12px 10px;
      width: 100%;
      margin: 20px 0px;
      box-sizing: border-box;
      display: inline-block;
    }
    input[type=submit],input[type=reset]{
      width: 100%;
      padding: 10px;
      margin: 20px 0px;
      background-color: brown;
      border: none;
      border-radius: 5px;
      color: white;
    }
    input[type=submit]:hover{
      background-color: lightgreen;
      color: white;
    }
    input[type=reset]:hover{
      background-color: red;
      color: white;
    }
  </style>
  <body>
    <h1>ADD STUDENT</h1>
    <% Student student = (Student) request.getAttribute("student"); %>
    <% int id = (Integer)request.getAttribute("id"); %>
    <%
    	String getChecked = student.getPaid();
    	String halfChecked = "";
    	String fullChecked = "";
    	switch(getChecked){
    		case "half":
    			halfChecked = "checked";
    			fullChecked = "unchecked";
    			break;
    		case "full":
    			halfChecked = "unchecked";
    			fullChecked = "checked";
    			break;
    	}
    %>
    <div class="outer">
      <form action="StudentControllerServlet" method="get">
		<input type = "hidden" name = "command" value = "UPDATE-DONE">
        <label for="fName"> Enter Your First Name</label>
        <input type="text" name="firstName" value="${student.getFirstName() }" required>

        <label for="lName"> Enter Your Last Name</label>
        <input type="text" name="lastName" value="${student.getLastName() }"  required>

        <label for="email"> Enter Your Email</label>
        <input type="text" name="email" value="${student.getEmail() }"  required>

        <label for="phoneNo"> Enter Your Phone Number</label>
        <input type="text" name="phoneNo" value="${student.getPhoneNo() }"  required>

        <label for="address"> Enter Your Address</label>
        <input type="text" name="address" value="${student.getAddress() }"  required>

        <label for="city"> Enter Your City</label>
        <input type="text" name="city" value="${student.getCity() }"  required>

        <label for="course"> Select Your Course</label>
        <select name="course" required>
          <option selected value = ""><%= student.getCourse() %></option>
          <option value="JAVA">JAVA</option>
          <option value="C">C</option>
          <option value="PYTHON">PYTHON</option>
          <option value="PHP">PHP</option>
        </select>
        <br><br>
        <label for="payment"> Payment : </label>
        <input type="radio" name="payment" value="half" checked = "${halfChecked }"> Half
        <input type="radio" name="payment" value="full" checked = "${fullChecked }"> Full
        <br><br>

        <label for="startDate"> Date Of Enrollment</label>
        <input type="date" name="startDate" value="${student.getStartDate() }"  required>


        <input type="submit" name = "submit" value="REGISTER">
        <input type="reset" name = "reset" value = "RESET">
      </form>
    </div>
  </body>
</html>

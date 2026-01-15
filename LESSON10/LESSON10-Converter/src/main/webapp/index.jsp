<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Currency Converter</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<h2>Currency Converter</h2>
<form action="converter.jsp" method="post">
    <label>Rate: </label> <br>
    <input type="text" name="rate" placeholder="Rate" value="26000" /> <br>
    <label>USD: </label> <br>
    <input type="text" name="usd" value="0" placeholder="USD" /> <br><br>
    <input type="submit" id="submit" value="Converter" />
</form>
</body>
</html>
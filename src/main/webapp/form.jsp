<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul  >
        <li><a href="<%=request.getContextPath()%>/list"  >Products</a ></li>
        </ul>
    <br>
  
  
<form action="insert" method="post">
  
<h2>
    Add New Product
</h2>

 <label>Product Name</label> <input type="text"  name="name"><br><br>
                        
<label>Product Price</label> <input type="text" name="rs"><br><br>                    
<button type="submit">Save</button>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Stocks</title>
    <style>
        /* CSS styling for the page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #333;
            text-align: center;
        }

        h3 {
            color: #666;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .buy-now {
            margin-top: 30px;
            text-align: center;
        }

        .buy-now a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #f44336;
            color: #fff;
            font-size: 24px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .buy-now a:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h2>My Stocks</h2>
    <h3>List of Products</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <c:forEach var="product" items="${a}">
            <tr>
                <td><c:out value="${product.id}" /></td>
                <td><c:out value="${product.name}" /></td>
                <td><c:out value="${product.rs + (product.rs / 10)}" /></td>
                <td><c:out value="${product.quantity}" /></td>
            </tr>
        </c:forEach>
    </table>
    
    <h3>Cart</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Single Price</th>
            <th>Total Quantity</th>
            <th>Actions</th>
            <th>Your Cart Value</th>
        </tr>
        
        
        
      
<c:forEach var="product" items="${a}">
    <tr>
        <td><c:out value="${product.id}" /></td>
        <td><c:out value="${product.name}" /></td>
        <td><c:out value="${product.rs + (product.rs / 10)}" /></td>
        <td><c:out value="${product.quantity}" /></td>
        <td>
            <a href="plus?id=<c:out value='${user.id}' />">+</a>
            <c:out value="${product.dq}" />
            <a href="minus?id=<c:out value='${user.id}' />">-</a>
        </td>
        <td><c:out value="${product.dq * price}" /></td>
    </tr>
</c:forEach>
</table>

<div class="buy-now">
    <a href="<%= request.getContextPath() %>/buy">Buy now</a>
</div>
</body>
</html>
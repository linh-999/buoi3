<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category List</title>
</head>
<body>
    <h2>Category List</h2>
    <a href="<c:url value='/admin/category/add'/>">Add New Category</a>
    
    <table border="1">
        <thead>
            <tr>
                <th>STT</th>
                <th>Image</th>
                <th>Category Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cateList}" var="cate" varStatus="STT">
                <tr class="odd gradeX">
                    <td>${STT.index+1}</td>
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                    <td><img height="150" width="200" src="${imgUrl}" /></td>
                    <td>${cate.catename}</td>
                    <td>
                        <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>">Sửa</a>
                        | 
                        <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

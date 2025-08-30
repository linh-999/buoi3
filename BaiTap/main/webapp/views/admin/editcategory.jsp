<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
    <style>
        .form-group {
            margin-bottom: 15px;
        }
        .form-control {
            width: 300px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 8px 12px;
            margin-right: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-default {
            background-color: #4CAF50;
            color: white;
        }
        .btn-primary {
            background-color: #f44336;
            color: white;
        }
        img {
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <h2>Chỉnh Sửa Danh Mục</h2>
    <c:url value="/admin/category/edit" var="edit"></c:url>
    <form role="form" action="${edit}" method="post" enctype="multipart/form-data">
        <input name="id" value="${category.cateid}" hidden="">
        <div class="form-group">
            <label>Tên danh mục:</label> 
            <input type="text" class="form-control" value="${category.catename}" name="name" />
        </div>
        <div class="form-group">
            <c:url value="/image?fname=${category.icon}" var="imgUrl"></c:url>
            <img class="img-responsive" width="100px" src="${imgUrl}" alt="Category Image">
            <br>
            <label>Ảnh đại diện</label> 
            <input type="file" name="icon" />
        </div>
        <button type="submit" class="btn-default">Lưu</button>
        <button type="reset" class="btn-primary">Hủy</button>
    </form>
</body>
</html>
<button type="reset" class=
"btn btn-primary">Reset</button>
</form>
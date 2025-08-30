<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
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
    </style>
</head>
<body>
    <h2>Thêm Danh Mục Mới</h2>
    <form role="form" action="add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>Tên danh mục:</label> 
            <input class="form-control" placeholder="Nhập tên danh mục" name="name" />
        </div>
        <div class="form-group">
            <label>Ảnh đại diện</label> 
            <input type="file" name="icon" />
        </div>
        <button type="submit" class="btn-default">Thêm</button>
        <button type="reset" class="btn-primary">Hủy</button>
    </form>
</body>
</html>
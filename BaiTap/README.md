# Web Application with Java Servlets

## Cấu hình Docker
Dự án này sử dụng Docker để triển khai:
- MySQL 8.0: Cơ sở dữ liệu
- Tomcat 10.1: Máy chủ ứng dụng

### Khởi động thủ công

1. Dừng và xóa tất cả containers và volumes:
   ```
   docker-compose down -v
   ```

2. Build ứng dụng:
   ```
   mvn clean package -DskipTests
   ```

3. Khởi động containers:
   ```
   docker-compose up -d
   ```

## Truy cập ứng dụng

- Trang chủ: http://localhost:8080/baitap2/
- Đăng nhập: http://localhost:8080/baitap2/login
- Đăng ký: http://localhost:8080/baitap2/register
- Quản lý danh mục: http://localhost:8080/baitap2/admin/category/list

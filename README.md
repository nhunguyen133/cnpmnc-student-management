# Student Management System

Web application quản lý sinh viên sử dụng Spring Boot, Thymeleaf, và PostgreSQL.

## Công nghệ sử dụng

- **Backend:** Spring Boot 3.4.1
- **Template Engine:** Thymeleaf
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **UI Framework:** Bootstrap 5

## Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- PostgreSQL 12 hoặc cao hơn
- Maven 3.6+

## Cài đặt và Chạy

### 1. Clone repository

```bash
git clone https://github.com/nhunguyen133/cnpmnc-student-management.git
cd cnpmnc-student-management
```

### 2. Cài đặt PostgreSQL

- Tải và cài đặt PostgreSQL từ [postgresql.org](https://www.postgresql.org/download/)
- Tạo database mới:

```sql
CREATE DATABASE student_management;
```

### 3. Cấu hình môi trường

Tạo file `.env` từ template:

```bash
cp .env.example .env
```

Chỉnh sửa file `.env` với thông tin database của bạn:

```properties
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=student_management
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password
```

### 4. Build và chạy ứng dụng

```bash
# Build project
./mvnw clean install

# Chạy ứng dụng
./mvnw spring-boot:run
```

### 5. Truy cập ứng dụng

Mở trình duyệt và truy cập:
- **Web UI:** http://localhost:8080/students
- **REST API:** http://localhost:8080/api/students

## Cấu trúc Project

```
src/main/java/vn/edu/hcmut/cse/adsoftweng/lab/
├── config/              # Configuration classes
│   ├── DataInitializer.java
│   └── DotenvConfig.java
├── controller/
│   ├── StudentController.java      # REST API
│   └── StudentWebController.java   # Web MVC
├── entity/
│   └── Student.java
├── repository/
│   └── StudentRepository.java
└── service/
    └── StudentService.java

src/main/resources/
├── templates/           # Thymeleaf templates
│   ├── fragments/
│   │   └── layout.html
│   └── students/
│       ├── list.html
│       ├── detail.html
│       └── form.html
└── application.properties
```

## Chức năng

### Web UI (`/students`)
- Xem danh sách sinh viên
- Tìm kiếm sinh viên theo tên
- Xem chi tiết sinh viên
- Thêm sinh viên mới
- Chỉnh sửa thông tin sinh viên
- Xóa sinh viên (với xác nhận)

### REST API (`/api/students`)
- `GET /api/students` - Lấy danh sách tất cả sinh viên
- `GET /api/students/{id}` - Lấy thông tin sinh viên theo ID

<!-- ## Screenshots

### Trang Danh Sách
![List View](docs/screenshots/list.png)

### Trang Chi Tiết
![Detail View](docs/screenshots/detail.png)

### Form Thêm/Sửa
![Form View](docs/screenshots/form.png) -->

## License

This project is licensed under the MIT License.
# API Quản Lý Công Việc

Đây là một dự án API backend giúp quản lý các công việc (tasks) và người dùng.

---

## Mục Lục

1.  [Giới Thiệu](#1-giới-thiệu)
2.  [Tính Năng](#2-tính-năng)
3.  [Công Nghệ Sử Dụng](#3-công-nghệ-sử-dụng)
4.  https://scholar.google.com/citations?user=weDBKuYAAAAJ&hl=en(#4-url-triển-khai)
5.  [Thiết Lập Môi Trường Phát Triển](#5-thiết-lập-môi-trường-phát-triển)
6.  [Cấu Trúc Dự Án](#6-cấu-trúc-dự-án)
7.  [Liên Hệ](#7-liên-hệ)

---

## 1. Giới Thiệu

API Quản Lý Công Việc là một ứng dụng backend được xây dựng để cung cấp các dịch vụ quản lý tác vụ và người dùng. Nó cho phép người dùng đăng ký, đăng nhập, tạo, xem, cập nhật và xóa các công việc của họ. Ngoài ra, có các chức năng quản trị để quản lý người dùng và xem tất cả các công việc trong hệ thống.

---

## 2. Tính Năng

* **Xác thực người dùng:** Đăng ký và đăng nhập tài khoản người dùng.
* **Quản lý công việc:**
    * Tạo, xem, cập nhật và xóa công việc.
    * Xem các công việc theo trạng thái hoặc độ ưu tiên.
    * Xem tóm tắt các công việc.
* **Quản lý người dùng (Dành cho Admin):**
    * Xem danh sách tất cả người dùng.
    * Xóa người dùng.
* **Phân quyền:** Phân biệt các chức năng cho người dùng thông thường và quản trị viên.

---

## 3. Công Nghệ Sử Dụng

* **Backend:** Spring Boot (Java)
* **Cơ sở dữ liệu:** JPA / Hibernate (Thông thường sẽ là PostgreSQL, MySQL, H2, v.v., bạn có thể ghi rõ ở đây nếu biết.)
* **Công cụ xây dựng:** Maven
* **Tiện ích:** Lombok (để giảm mã boilerplate)

---

## 4. URL Triển Khai

API hiện đã được triển khai và có thể truy cập tại:
**`https://task-madr.onrender.com/`**

---

## 5. Thiết Lập Môi Trường Phát Triển

Để chạy dự án này trên máy tính cục bộ của bạn, hãy làm theo các bước sau:

1.  **Sao chép kho lưu trữ (Clone Repository):**
    ```bash
    git clone <url_kho_luu_tru_cua_ban>
    cd <thu_muc_du_an_cua_ban>
    ```
    (Thay thế `<url_kho_luu_tru_cua_ban>` và `<thu_muc_du_an_cua_ban>` bằng URL kho lưu trữ thực tế và tên thư mục mong muốn của bạn.)

2.  **Cấu hình cơ sở dữ liệu:**
    * Mở file `src/main/resources/application.properties` (hoặc `application.yml`).
    * Cấu hình các thuộc tính kết nối cơ sở dữ liệu của bạn (ví dụ cho H2, MySQL, PostgreSQL):
        ```properties
        spring.datasource.url=jdbc:h2:mem:taskdb
        spring.datasource.username=sa
        spring.datasource.password=
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        ```
        * **Lưu ý:** Đối với môi trường sản xuất, hãy sử dụng cơ sở dữ liệu bên ngoài và điều chỉnh `ddl-auto` (ví dụ: `none` hoặc `validate`).

3.  **Xây dựng dự án (Build Project):**
    ```bash
    mvn clean install
    ```

4.  **Chạy ứng dụng (Run Application):**
    ```bash
    mvn spring-boot:run
    ```
    Ứng dụng sẽ chạy trên `http://localhost:8080` theo mặc định.

---

## 6. Cấu Trúc Dự Án

Dự án được tổ chức theo các gói (packages) chính sau:

* **`com.entity.task.controller`**: Chứa các lớp REST Controller xử lý các yêu cầu HTTP.
* **`com.entity.task.service.impl`**: Chứa các lớp triển khai logic nghiệp vụ (business logic).
* **`com.entity.task.repository`**: Chứa các interface Spring Data JPA để tương tác với cơ sở dữ liệu.
* **`com.entity.task.entities`**: Chứa các lớp Entity đại diện cho các bảng trong cơ sở dữ liệu.
* **`com.entity.task.dto.request`**: Chứa các đối tượng Data Transfer Object (DTO) cho dữ liệu yêu cầu từ client.
* **`com.entity.task.dto.response`**: Chứa các đối tượng DTO cho dữ liệu phản hồi trả về client.
* **`com.entity.task.webconstants`**: Chứa các hằng số (constants) được sử dụng trong ứng dụng (ví dụ: thông báo, mã lỗi).

---

## 7. Liên Hệ

Nếu có bất kỳ câu hỏi nào, vui lòng liên hệ:
[Địa chỉ Email của bạn]
[Liên kết LinkedIn/GitHub của bạn]

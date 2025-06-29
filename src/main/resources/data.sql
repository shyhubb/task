INSERT IGNORE INTO roles (id, role) VALUES (1, 'USER');
INSERT IGNORE INTO roles (id, role) VALUES (2, 'ADMIN');

INSERT IGNORE INTO users (id, account, password, name, phone_number, address, role_id)
VALUES (
    1,
    'admin',
    '$2a$10$YJYz9t9bxysd/0XeCVeNteuhKhfhzVbg9gbr3kaXfrRv8VnKIVZyW',
    'Administrator',
    '0901234567',
    '123 Admin Street, Hanoi',
    (SELECT id FROM roles WHERE role = 'ADMIN')
);

INSERT IGNORE INTO users (id, account, password, name, phone_number, address, role_id)
VALUES (
    2,
    'user1',
    '$2a$10$Z.Wz0tV0l0c0d0e0f0g0h0i0j0k0l0m0n0o0p0q0r0s0t0u0v0w0x0y0z0',
    'Nguyen Van An',
    '0987654321',
    '456 User Lane, Ho Chi Minh City',
    (SELECT id FROM roles WHERE role = 'USER')
);

INSERT IGNORE INTO task_priority (id, level, description) VALUES (1, 1, 'LOW');
INSERT IGNORE INTO task_priority (id, level, description) VALUES (2, 2, 'MEDIUM');
INSERT IGNORE INTO task_priority (id, level, description) VALUES (3, 3, 'HIGH');

INSERT IGNORE INTO task_status (id, status, description) VALUES (1, 'PENDING', 'Nhiệm vụ đang chờ xử lý');
INSERT IGNORE INTO task_status (id, status, description) VALUES (2, 'COMPLETED', 'Nhiệm vụ đã hoàn thành');
INSERT IGNORE INTO task_status (id, status, description) VALUES (3, 'IN_PROGRESS', 'Nhiệm vụ đang thực hiện');
INSERT IGNORE INTO task_status (id, status, description) VALUES (4, 'CANCELLED', 'Nhiệm vụ đã bị hủy');

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    1,
    'Học Spring Boot nâng cao',
    'Hoàn thành các bài tập thực hành về Spring Security và Microservices.',
    '2025-07-30 23:59:59',
    (SELECT id FROM users WHERE account = 'admin'),
    (SELECT id FROM task_priority WHERE level = 3),
    (SELECT id FROM task_status WHERE status = 'PENDING')
);

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    2,
    'Mua quà sinh nhật cho bạn',
    'Tìm món quà phù hợp và gói ghém cẩn thận.',
    '2025-07-05 18:00:00',
    (SELECT id FROM users WHERE account = 'user1'),
    (SELECT id FROM task_priority WHERE level = 2),
    (SELECT id FROM task_status WHERE status = 'IN_PROGRESS')
);

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    3,
    'Lên kế hoạch du lịch hè',
    'Nghiên cứu địa điểm, chi phí và đặt vé.',
    '2025-08-15 12:00:00',
    (SELECT id FROM users WHERE account = 'admin'),
    (SELECT id FROM task_priority WHERE level = 1),
    (SELECT id FROM task_status WHERE status = 'PENDING')
);

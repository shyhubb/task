-- CREATE DATABASE IF NOT EXISTS taskmanager CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- USE taskmanager;

INSERT IGNORE INTO roles (id, role_name) VALUES (1, 'USER');
INSERT IGNORE INTO roles (id, role_name) VALUES (2, 'ADMIN');

INSERT IGNORE INTO users (id, account, password, name, phone_number, address, role_id)
VALUES (
    1,
    'admin',
    '$2a$10$K2jXQH6W8iq0U0/iRPRETeIJmKFBZMENtX57zh2s4u3Xh1xrb9ZM2', 
    'Administrator',
    '0901234567',
    '123 Admin Street, Ha Noi',
    (SELECT id FROM roles WHERE role_name = 'ADMIN')
);

INSERT IGNORE INTO users (id, account, password, name, phone_number, address, role_id)
VALUES (
    2,
    'user1',
    '$2a$10$y.I.E.C.A.T.E.A.K.Y.Z.X.E.N.O.N.I.U.M.V.I.P.Y.O.U.R.P.A.S.S.W.O.R.D',
    'Nguyen Van An',
    '0987654321',
    '456 User Lane, Ho Chi Minh City',
    (SELECT id FROM roles WHERE role_name = 'USER')
);

INSERT IGNORE INTO task_priority (id, level, description) VALUES (1, 1, 'LOW');
INSERT IGNORE INTO task_priority (id, level, description) VALUES (2, 2, 'MEDIUM');
INSERT IGNORE INTO task_priority (id, level, description) VALUES (3, 3, 'HIGH');

INSERT IGNORE INTO task_status (id, status, description) VALUES (1, 'PENDING', 'Nhiem vu dang cho xu ly');
INSERT IGNORE INTO task_status (id, status, description) VALUES (2, 'COMPLETED', 'Nhiem vu da hoan thanh');
INSERT IGNORE INTO task_status (id, status, description) VALUES (3, 'IN_PROGRESS', 'Nhiem vu dang thuc hien');
INSERT IGNORE INTO task_status (id, status, description) VALUES (4, 'CANCELLED', 'Nhiem vu da bi huy');

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    1,
    'Hoc Spring Boot nang cao',
    'Hoan thanh cac bai tap thuc hanh ve Spring Security va Microservices.',
    '2025-07-30 23:59:59',
    (SELECT id FROM users WHERE account = 'admin'),
    (SELECT id FROM task_priority WHERE level = 3),
    (SELECT id FROM task_status WHERE status = 'PENDING')
);

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    2,
    'Mua qua sinh nhat cho ban',
    'Tim mon qua phu hop va goi ghem can than.',
    '2025-07-05 18:00:00',
    (SELECT id FROM users WHERE account = 'user1'),
    (SELECT id FROM task_priority WHERE level = 2),
    (SELECT id FROM task_status WHERE status = 'IN_PROGRESS')
);

INSERT IGNORE INTO task (id, task_name, description, due_date, user_id, task_priority_id, task_status_id)
VALUES (
    3,
    'On tap thi cuoi ky',
    'On lai tat ca bai hoc va lam de thi thu de chuan bi cho ky thi cuoi ky.',
    '2025-08-15 12:00:00',
    (SELECT id FROM users WHERE account = 'admin'),
    (SELECT id FROM task_priority WHERE level = 1),
    (SELECT id FROM task_status WHERE status = 'PENDING')
);

-- Inserting a system_user with Admin role
INSERT INTO system_users (user_id, username, role, email, password)
VALUES (999999,'SushmitaBishwakarma', 'Admin', 'glamourjewellerynp@gmail.com', '$2a$12$8QnQpAeSERbdP8/epfWtJOyhwcysnyHEPItkv1mbVbbqkRJOSBbZ.');


-- Inserting a role for Admin
INSERT INTO roles (id, name) VALUES (1, 'Admin');
INSERT INTO roles (id, name) VALUES (2, 'Customer');

-- Mapping role for UserId as RoleId for Admin
INSERT INTO users_roles (user_id, role_id) VALUES (999999, 1);


-- Inserting Email Credentials
INSERT INTO public.email_credentials (id, email, password, host, port, date, active, protocol)
VALUES (1,'glamourjewellerynp@gmail.com', 'uelf skna fcog cyig', 'smtp.gmail.com', '587','2024-02-24 21:05:00.000000', true, 'smtp');



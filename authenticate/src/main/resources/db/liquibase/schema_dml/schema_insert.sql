INSERT INTO "users_role"("object_id", "name") VALUES (1, 'Admin');
INSERT INTO "users_role"("object_id", "name") VALUES (2, 'Employee');
INSERT INTO "users_role"("object_id", "name") VALUES (3, 'Others');

INSERT INTO users( role_id, user_name,  firstname,  lastname, email, status )
            VALUES ( 1, 'system', 'SYSTEM',  'USER', 'Khushboo_Vansh@epam.com', 'active');
            
INSERT INTO users( role_id, user_name,  firstname,  lastname, email, status )
            VALUES ( 1, 'Khushboo_Vansh', 'Khushboo',  'Vansh', 'Khushboo_Vansh@epam.com', 'active');  

COMMIT;


INSERT INTO agent (id, name, email, status)
VALUES (1, 'Alice', 'alice@gmail.com', 'AVAILABLE'),
       (2, 'Bob', 'bob@gmail.com', 'AVAILABLE'),
       (3, 'Carol', 'carol@gmail.com', 'AVAILABLE'),
       (4, 'David', 'david@gmail.com', 'ON_CALL'),
       (5, 'Eve', 'eve@gmail.com', 'OFFLINE'),
       (6, 'Frank', 'frank@gmail.com', 'AVAILABLE'),
       (7, 'Grace', 'grace@gmail.com', 'AVAILABLE');

INSERT INTO agent_categories (agent_id, category)
VALUES (1, 'TECHNICAL'),
       (1, 'GENERAL'),

       (2, 'BILLING'),
       (2, 'GENERAL'),

       (3, 'SALES'),
       (3, 'BILLING'),

       (4, 'TECHNICAL'),

       (5, 'GENERAL'),

       (6, 'TECHNICAL'),
       (6, 'SALES'),

       (7, 'BILLING'),
       (7, 'SALES');

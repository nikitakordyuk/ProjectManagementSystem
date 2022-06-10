INSERT INTO developers(first_name, last_name, age, gender) VALUES
    ('Nikita', 'Kordyuk', '20', 'male'),
    ('Name2', 'Surname2', '21', 'female'),
    ('Name3', 'Surname3', '22', 'male'),
    ('Name4', 'Surname4', '23', 'female'),
    ('Name5', 'Surname5', '24', 'male');

INSERT INTO skills(language_name, skill_level) VALUES
    ('Java', 8),
    ('Java', 5),
    ('Python', 10),
    ('R', 4),
    ('Go', 7),
    ('JavaScript', 6),
    ('Assembler', 3);

INSERT INTO developers_skills(developer_id, skill_id) VALUES
    (1, 3),
    (1, 4),
    (1, 5),
    (2, 1),
    (3, 2),
    (3, 4),
    (3, 7),
    (5, 6),
    (5, 7);

INSERT INTO companies(company_name, is_famous) VALUES
    ('Company 1', false),
    ('Company 2', true),
    ('Company 3', false),
    ('Company 4', true);

INSERT INTO projects(project_name, complexity) VALUES
    ('Project 1', 6),
    ('Project 2', 4),
    ('Project 3', 2),
    ('Project 4', 10),
    ('Project 5', 7),
    ('Project 6', 9);

INSERT INTO developers_projects(developer_id, project_id) VALUES
    (1, 5),
    (1, 1),
    (1, 2),
    (2, 2),
    (2, 6),
    (4, 1),
    (4, 2),
    (3, 1);

INSERT INTO customers(first_name, last_name) VALUES
    ('CustomerName 1', 'CustomerSurname 1'),
    ('CustomerName 2', 'CustomerSurname 2'),
    ('CustomerName 3', 'CustomerSurname 3'),
    ('CustomerName 4', 'CustomerSurname 4'),
    ('CustomerName 5', 'CustomerSurname 5');

INSERT INTO companies_projects(company_id, project_id) VALUES
    (1, 1),
    (2, 4),
    (3, 2),
    (4, 3),
    (2, 1);
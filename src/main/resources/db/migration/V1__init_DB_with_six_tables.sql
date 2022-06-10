CREATE TABLE IF NOT EXISTS developers(
    developer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age SMALLINT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    salary NUMERIC(20, 2) NOT NULL DEFAULT 0.00);

CREATE TABLE IF NOT EXISTS skills(
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    language_name VARCHAR(30) NOT NULL,
    skill_level VARCHAR(10) NOT NULL);

CREATE TABLE IF NOT EXISTS projects(
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    complexity SMALLINT NOT NULL,
    cost DOUBLE NOT NULL DEFAULT abs(ROUND(RAND() * 10000, 0)),
    date_of_creation DATE NOT NULL DEFAULT '2020-10-29');

CREATE TABLE IF NOT EXISTS companies(
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    is_famous BOOLEAN NOT NULL);

CREATE TABLE IF NOT EXISTS customers(
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES projects(project_id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS developers_projects(
    developer_id INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (developer_id, project_id),
    FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS developers_skills(
    developer_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (developer_id, skill_id),
    FOREIGN KEY (developer_id) REFERENCES developers(developer_id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skills(skill_id) ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS companies_projects(
    company_id INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (company_id, project_id),
    FOREIGN KEY (company_id) REFERENCES companies(company_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE CASCADE);

ALTER TABLE customers
ADD CONSTRAINT customer_id_fk
    FOREIGN KEY (customer_id)
        REFERENCES projects(project_id) ON DELETE CASCADE;
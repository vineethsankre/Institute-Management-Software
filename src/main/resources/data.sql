-- Insert instructors
INSERT INTO instructor (name, email, specialization) VALUES ('John Doe', 'john.doe@example.com', 'Java Development');
INSERT INTO instructor (name, email, specialization) VALUES ('Jane Smith', 'jane.smith@example.com', 'Database Design');

-- Insert courses
INSERT INTO course (name, description, duration, instructor_id) VALUES ('Spring Boot Basics', 'Learn the basics of Spring Boot', '3 months', 1);
INSERT INTO course (name, description, duration, instructor_id) VALUES ('Advanced MySQL', 'Deep dive into MySQL queries', '2 months', 2);

-- Insert students
INSERT INTO student (name, email, phone, enrollment_date, course_id) VALUES ('Alice Brown', 'alice.brown@example.com', '1234567890', '2024-06-01', 1);
INSERT INTO student (name, email, phone, enrollment_date, course_id) VALUES ('Bob Green', 'bob.green@example.com', '0987654321', '2024-06-15', 2);

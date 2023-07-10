SELECT s.name, s.age
FROM student s
         LEFT JOIN faculty f ON s.faculty_id = f.id;

SELECT s.name, a.id
FROM student s
         INNER JOIN avatar a on s.id = a.student_id;
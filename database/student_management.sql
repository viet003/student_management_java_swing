CREATE DATABASE student_management;
USE student_management;

CREATE TABLE tbl_role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE tbl_teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    dob DATE,
    status VARCHAR(50)
);

CREATE TABLE tbl_account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    teacher_id INT UNIQUE, -- Mỗi giáo viên có một tài khoản duy nhất
    FOREIGN KEY (role_id) REFERENCES tbl_role(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES tbl_teacher(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tbl_course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(255) NOT NULL,
    begin_date DATE,
    end_date DATE
);

CREATE TABLE tbl_subject (
    id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(255) NOT NULL,
    accredit VARCHAR(50),
    price DOUBLE,
    status VARCHAR(50)
);

CREATE TABLE tbl_class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(255) NOT NULL,
    course_id INT,
    teacher_id INT,
    subject_id INT,
    FOREIGN KEY (subject_id) REFERENCES tbl_subject(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES tbl_course(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES tbl_teacher(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Bảng tbl_student với msv làm khóa chính, loại bỏ id và img
CREATE TABLE tbl_student (
    msv VARCHAR(50) PRIMARY KEY,  -- Mã sinh viên làm khóa chính
    student_name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(255),
    address TEXT,
    dob DATE,
    gender VARCHAR(10),
    status VARCHAR(50)
);

-- Cập nhật khóa ngoại trong tbl_Enroll và tbl_mark để tham chiếu đến msv thay vì id
CREATE TABLE tbl_Enroll (
    student_msv VARCHAR(50),
    class_id INT,
    PRIMARY KEY (student_msv, class_id),
    FOREIGN KEY (student_msv) REFERENCES tbl_student(msv) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES tbl_class(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tbl_mark (
    student_msv VARCHAR(50),
    sub_id INT,
    mark DOUBLE,
    status VARCHAR(50),
    note TEXT,
    ex_date DATE,
    PRIMARY KEY (student_msv, sub_id),
    FOREIGN KEY (student_msv) REFERENCES tbl_student(msv) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (sub_id) REFERENCES tbl_subject(id) ON UPDATE CASCADE ON DELETE CASCADE
);

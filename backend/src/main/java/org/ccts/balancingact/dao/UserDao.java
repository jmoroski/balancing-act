package org.ccts.balancingact.dao;

import java.util.List;

import org.ccts.balancingact.model.api.Administrator;
import org.ccts.balancingact.model.api.Student;
import org.ccts.balancingact.model.api.User;

public interface UserDao {
    List<User> getUsers();
    List<Administrator> getAdministrators();
    List<Student> getStudents();
    Administrator addAdministrator(Administrator administrator);
    Student addStudent(Student student);
    User updateUser(User user);
    void removeUser(String id);
}

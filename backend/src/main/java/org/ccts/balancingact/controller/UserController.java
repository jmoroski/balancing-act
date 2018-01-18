package org.ccts.balancingact.controller;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.dao.BankingDao;
import org.ccts.balancingact.dao.UserDao;
import org.ccts.balancingact.model.api.Administrator;
import org.ccts.balancingact.model.api.Student;
import org.ccts.balancingact.model.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BankingDao bankingDao;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userDao.getUsers(), HttpStatus.OK);
    }

    @PostMapping(path = "/administrators")
    public ResponseEntity<Administrator> addAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(this.userDao.addAdministrator(administrator), HttpStatus.CREATED);
    }

    @PostMapping(path = "/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = this.userDao.addStudent(student);
        this.bankingDao.createBankAccount(UUID.fromString(newStudent.getId()), this.bankingDao.getBankId());

        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userDao.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable final String id) {
        userDao.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(params = "students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(userDao.getStudents(), HttpStatus.OK);
    }

    @GetMapping(params = "administrators")
    public ResponseEntity<List<Administrator>> getAdministrators() {
        return new ResponseEntity<>(userDao.getAdministrators(), HttpStatus.OK);
    }
}

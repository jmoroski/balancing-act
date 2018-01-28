package org.ccts.balancingact.controller;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.dao.ProgramDao;
import org.ccts.balancingact.model.api.ProgramGroup;
import org.ccts.balancingact.model.api.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/programGroups")
public class ProgramController {
    @Autowired
    private ProgramDao programDao;

    @GetMapping
    public ResponseEntity<List<ProgramGroup>> getProgramGroups() {
        return new ResponseEntity<>(programDao.getProgramGroups(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgramGroup> addProgramGroup(@RequestBody ProgramGroup programGroup) {
        return new ResponseEntity<>(programDao.addProgramGroup(programGroup), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> removeProgramGroup(@PathVariable UUID id) {
        programDao.removeProgramGroup(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping(path = "{id}")
//    public ResponseEntity<ProgramGroup> getProgramGroup(@PathVariable UUID id) {
//        return new ResponseEntity<>(programDao.getProgramGroup(id), HttpStatus.OK);
//    }

      @GetMapping(path = "{id}/students")
      public ResponseEntity<List<Student>> getProgramGroupStudents(@PathVariable UUID id) {
          return new ResponseEntity<>(programDao.getProgramGroupStudents(id), HttpStatus.OK);
      }

      @PutMapping(path = "{id}/students")
      public ResponseEntity<List<Student>> setProgramGroupStudents(@PathVariable UUID id, @RequestBody List<Student> students) {
          return new ResponseEntity<>(programDao.setProgramGroupStudents(id, students), HttpStatus.OK);
      }



//    @PostMapping
//    public ResponseEntity<?> addUser(@RequestBody Program dto) {
//        return new ResponseEntity<>(programDao.addProgram(dto.name, dto.administratorId), HttpStatus.CREATED);
//    }
}

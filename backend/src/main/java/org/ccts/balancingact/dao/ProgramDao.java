package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.ProgramGroup;
import org.ccts.balancingact.model.api.Student;

public interface ProgramDao {
    List<ProgramGroup> getProgramGroups();
    ProgramGroup addProgramGroup(final ProgramGroup programGroup);
    ProgramGroup getProgramGroup(final UUID id);
    void removeProgramGroup(final UUID id);
    List<Student> getProgramGroupStudents(final UUID programGroupId);
    List<Student> setProgramGroupStudents(final UUID programGroupId, List<Student> students);
}

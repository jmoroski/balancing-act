package org.ccts.balancingact.model.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "program_group_students", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "student_id" }, name = "uk_program_group_students")
})
public class ProgramGroupStudentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "program_group_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_students_program_groups_id"))
    private ProgramGroupEntity programGroup;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_program_group_students_users_id"))
    private StudentEntity student;
}
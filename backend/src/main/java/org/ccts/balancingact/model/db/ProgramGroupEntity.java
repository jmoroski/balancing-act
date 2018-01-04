package org.ccts.balancingact.model.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "program_groups")
public class ProgramGroupEntity extends BaseEntity {
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "program_id", foreignKey = @ForeignKey(name = "fk_program_groups_programs_id"))
    private ProgramEntity program;

    @ManyToOne
    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(name = "fk_program_groups_users_id"))
    private AdministratorEntity admin;

    @OneToMany(mappedBy = "programGroup")
    private List<ProgramGroupStudentEntity> students;

    public AdministratorEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdministratorEntity admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

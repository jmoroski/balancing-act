package org.ccts.balancingact.model.db;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_payroll")
public class UserPayrollEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_payroll_users_id"))
    private UserEntity user;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date payDate;

    @OneToMany(mappedBy = "userPayroll")
    private List<UserPayrollItemEntity> items;

}

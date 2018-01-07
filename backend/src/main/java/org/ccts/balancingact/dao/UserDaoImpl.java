package org.ccts.balancingact.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ccts.balancingact.model.api.Administrator;
import org.ccts.balancingact.model.api.Student;
import org.ccts.balancingact.model.api.User;
import org.ccts.balancingact.model.db.AdministratorEntity;
import org.ccts.balancingact.model.db.StudentEntity;
import org.ccts.balancingact.model.db.UserEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Map<String, Class<? extends User>> SUBTYPES = new HashMap<>();
    {
        SUBTYPES.put("Administrator", Administrator.class);
        SUBTYPES.put("Student", Student.class);
    }

    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> getUsers() {
        ModelMapper mapper = new ModelMapper();
        DetachedCriteria criteria = DetachedCriteria.forClass(UserEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.addOrder(Order.asc("firstName"));
        List<UserEntity> results = sessionFactoryTemplate.findByCriteria(criteria);

        Type apiType = (new ArrayList<User>()).getClass();
        return mapper.map(results, apiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Student> getStudents() {
        DetachedCriteria criteria = DetachedCriteria.forClass(StudentEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.addOrder(Order.asc("firstName"));
        List<StudentEntity> results =  sessionFactoryTemplate.findByCriteria(criteria);

        ModelMapper mapper = new ModelMapper();
        Type apiType = (new ArrayList<Student>()).getClass();
        return mapper.map(results, apiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Administrator> getAdministrators() {
        DetachedCriteria criteria = DetachedCriteria.forClass(AdministratorEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.addOrder(Order.asc("firstName"));
        List<AdministratorEntity> results = sessionFactoryTemplate.findByCriteria(criteria);

        ModelMapper mapper = new ModelMapper();
        Type apiType = (new ArrayList<Administrator>()).getClass();
        return mapper.map(results, apiType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Administrator addAdministrator(final Administrator administrator) {
        ModelMapper mapper = new ModelMapper();
        AdministratorEntity entity = mapper.map(administrator, AdministratorEntity.class);
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, Administrator.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student addStudent(final Student student) {
        ModelMapper mapper = new ModelMapper();
        StudentEntity entity = mapper.map(student, StudentEntity.class);
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, Student.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User updateUser(User user) {
        ModelMapper mapper = new ModelMapper();
        UserEntity entity = sessionFactoryTemplate.findById(UUID.fromString(user.getId()), UserEntity.class);
        mapper.map(user, entity);

        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeUser(String id) {
        sessionFactoryTemplate.delete(sessionFactoryTemplate.load(UUID.fromString(id), UserEntity.class));
    }
}

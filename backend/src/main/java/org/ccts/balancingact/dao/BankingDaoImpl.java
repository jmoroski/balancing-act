package org.ccts.balancingact.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ccts.balancingact.model.ModelMapperUtils;
import org.ccts.balancingact.model.api.BankAccountSummary;
import org.ccts.balancingact.model.api.BankAccountTransaction;
import org.ccts.balancingact.model.db.BankAccountEntity;
import org.ccts.balancingact.model.db.BankAccountTransactionEntity;
import org.ccts.balancingact.model.db.BankEntity;
import org.ccts.balancingact.model.db.UserEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BankingDaoImpl implements BankingDao {
    @Autowired
    private SessionFactoryTemplate sessionFactoryTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createBankAccount(final UUID userId, final UUID bankId) {
        UserEntity user = sessionFactoryTemplate.load(userId, UserEntity.class);
        BankEntity bank = sessionFactoryTemplate.load(bankId, BankEntity.class);

        BankAccountEntity account = new BankAccountEntity(user, bank);
        sessionFactoryTemplate.save(account);
        createTransaction(account.getId(), BigDecimal.ZERO, LocalDate.now(), "Initial Balance");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UUID getBankId() {
        return sessionFactoryTemplate.findAll(BankEntity.class).get(0).getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BankAccountTransaction addAccountTransaction(BankAccountTransaction transaction) {
        ModelMapper mapper = ModelMapperUtils.getInstance();
        BankAccountTransactionEntity entity = mapper.map(transaction, BankAccountTransactionEntity.class);
        entity.setAccount(sessionFactoryTemplate.findById(UUID.fromString(transaction.getAccountId()), BankAccountEntity.class));
        sessionFactoryTemplate.save(entity);

        return mapper.map(entity, BankAccountTransaction.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BankAccountTransaction updateAccountTransaction(final BankAccountTransaction transaction) {
        BankAccountTransactionEntity entity = sessionFactoryTemplate.findById(UUID.fromString(transaction.getId()), BankAccountTransactionEntity.class);
        ModelMapperUtils.getInstance().map(transaction, entity);

        return transaction;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removeTransaction(final UUID transactionId) {
        sessionFactoryTemplate.delete(sessionFactoryTemplate.load(transactionId, BankAccountTransactionEntity.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BankAccountSummary> getAllAccountBalances() {
        DetachedCriteria criteria = DetachedCriteria.forClass(BankAccountTransactionEntity.class);
        criteria.createAlias("account", "account", JoinType.RIGHT_OUTER_JOIN);
        criteria.createAlias("account.user", "user");
        criteria.createAlias("account.bank", "bank");

        ProjectionList projections = Projections.projectionList();
        projections.add(Projections.alias(Projections.sum("amount"), "balance"));
        projections.add(Projections.alias(Projections.groupProperty("account.id"), "accountId"));
        projections.add(Projections.alias(Projections.groupProperty("account.name"), "accountName"));
        projections.add(Projections.alias(Projections.property("user.firstName"), "ownerFirstName"));
        projections.add(Projections.alias(Projections.property("user.lastName"), "ownerLastName"));
        projections.add(Projections.alias(Projections.property("bank.name"), "bankName"));
        criteria.setProjection(projections);

        criteria.addOrder(Order.asc("ownerLastName")).addOrder(Order.asc("ownerFirstName"));
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> results = sessionFactoryTemplate.findByCriteria(criteria);

        return ModelMapperUtils.mapList(results, BankAccountSummary.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<BankAccountTransaction> getAccountTransactions(UUID accountId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BankAccountTransactionEntity.class);
        criteria.add(Restrictions.eq("account.id", accountId));
        criteria.addOrder(Order.desc("transactionTime"));

        return ModelMapperUtils.mapList(sessionFactoryTemplate.findByCriteria(criteria), BankAccountTransaction.class);
    }

    @Override
    public BankAccountSummary getAccount(UUID accountId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BankAccountTransactionEntity.class);
        criteria.createAlias("account", "account", JoinType.RIGHT_OUTER_JOIN);
        criteria.createAlias("account.user", "user");
        criteria.createAlias("account.bank", "bank");

        ProjectionList projections = Projections.projectionList();
        projections.add(Projections.alias(Projections.sum("amount"), "balance"));
        projections.add(Projections.alias(Projections.groupProperty("account.id"), "accountId"));
        projections.add(Projections.alias(Projections.groupProperty("account.name"), "accountName"));
        projections.add(Projections.alias(Projections.property("user.firstName"), "ownerFirstName"));
        projections.add(Projections.alias(Projections.property("user.lastName"), "ownerLastName"));
        projections.add(Projections.alias(Projections.property("bank.name"), "bankName"));
        criteria.setProjection(projections);

        criteria.add(Restrictions.eq("account.id", accountId));
        criteria.addOrder(Order.asc("ownerLastName")).addOrder(Order.asc("ownerFirstName"));
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return ModelMapperUtils.getInstance().map(sessionFactoryTemplate.findUniqueByCriteria(criteria), BankAccountSummary.class);
    }

    private BankAccountTransactionEntity createTransaction(final UUID accountId, final BigDecimal amount, final LocalDate time, final String name) {
        BankAccountEntity account = sessionFactoryTemplate.load(accountId, BankAccountEntity.class);
        BankAccountTransactionEntity transaction = new BankAccountTransactionEntity(account, time, amount, name);
        sessionFactoryTemplate.save(transaction);
        return transaction;
    }
}

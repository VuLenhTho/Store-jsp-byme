package dao.impl;

import dao.IAccountDAO;
import mapper.AccountMapper;
import model.AccountModel;
import paging.PageAble;

import java.util.List;

public class AccountDAOImpl extends AbstractDAO<AccountModel> implements IAccountDAO {

    @Override
    public void insertAccount(AccountModel accountModel) {
        String sql = "INSERT INTO account(userName, password, " +
                "firstName, lastName, createdDate, email, phoneNumber, sexID, roleID)" +
                " VALUE (?,?,?,?,?,?,?,?,?)";
        insertUpdateDelete(sql, accountModel.getUserName(), accountModel.getPassword(),
                accountModel.getFirstName(), accountModel.getLastName(),
                accountModel.getCreatedDate(), accountModel.getEmail(),
                accountModel.getPhoneNumber(), accountModel.getSexID(),
                accountModel.getRoleID());
    }

    @Override
    public void updateAccount(Long id, AccountModel accountModel) {
        String sql = "UPDATE account SET userName =?,password=?,firstName=?,lastName=?," +
                "email=?,phoneNumber=?,sexID=?,roleID=? WHERE id =?";
        insertUpdateDelete(sql, accountModel.getUserName(), accountModel.getPassword(),
                accountModel.getFirstName(), accountModel.getLastName(),
                accountModel.getEmail(), accountModel.getPhoneNumber(),
                accountModel.getSexID(), accountModel.getRoleID(), id);
    }

    @Override
    public void deleteAccount(Long id) {
        String sql = "DELETE FROM account WHERE id =?";
        insertUpdateDelete(sql, id);
    }

    @Override
    public List<AccountModel> findAllAccount(PageAble pageAble) {
        String sql = "SELECT * FROM account";
        if (pageAble.getPage() != 0 && pageAble.getLimit() != 0){
            sql += " LIMIT "+ pageAble.getLimit() + " OFFSET " + pageAble.getObset();
        }
        return findByProperties(sql, new AccountMapper());
    }

    @Override
    public AccountModel findByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT * FROM account WHERE userName =? AND password =?";
        List<AccountModel> accountModel = findByProperties(sql, new AccountMapper(), userName, password);
        return accountModel.isEmpty() ? null : accountModel.get(0);
    }

    @Override
    public AccountModel findByID(Long id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        List<AccountModel> accountModel = findByProperties(sql, new AccountMapper(), id);
        return accountModel.isEmpty() ? null : accountModel.get(0);
    }

    @Override
    public long countAllAccount() {
        String sql = "SELECT COUNT(*) FROM account";
        return countAll(sql);
    }

}

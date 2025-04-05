package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    // Registering new account.
    // Username can't be blank.
    // Password must be longer 4 characters.
    // Username can't already be taken.

    public Account registerAccount(String username, String password) {

        // Validating whether a username is blank or null:
        if (username == null || username.isBlank()) {
            return null;
        }

        // Validating whether a password is blank, null, or too short:
        if (password == null || password.isBlank() || password.length() < 4) {
            return null;
        }

        // Validating whether an account with a specified username already exists in the database:
        Account exists = accountDAO.getAccountByUsername(username);
        if (exists != null) {
            return null;
        }

        // Creating new account and inserting into the database:
        Account newAccount = new Account(username, password);

        return accountDAO.insertAccount(newAccount);
    }

    // Authenticating credentials; A specified username and password must match a real account in the database:
    public Account login(String username, String password) {
        // Validating whether the username is blank or null:
        if (username == null || username.isBlank()) {
            return null;
        }

        // Validating whether a password is blank, null or too short:
        if (password == null || password.isBlank() || password.length() < 4) {
            return null;
        }

        return accountDAO.getAccountByUsernameAndPassword(username, password);
    }
}
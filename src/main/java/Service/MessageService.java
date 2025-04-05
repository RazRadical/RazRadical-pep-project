package Service;

import Model.Message;
import Model.Account;
import DAO.MessageDAO;
import DAO.AccountDAO;
import java.util.*;

public class MessageService {
    MessageDAO messageDAO;
    AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    } 
    
    // Creating a new message.
    // A 'message_text' can't be blank or longer than 255 characters.
    // The 'posted_by' must from a real account.

    public Message createMessage(String message_text, int posted_by, long time_posted_epoch) {
        // Validating whether 'message_text' isn't null, blank, or too long:
        if (message_text == null || message_text.isBlank() || message_text.length() > 255) {
            return null;
        }

        // Validating whether 'posted_by' is associated with an existing user:
        Account exists = accountDAO.getAccountByAccountId(posted_by);
        if (exists == null) {
            return null;
        }

        // Creating a new message and inserting it into the database:
        Message message = new Message(posted_by, message_text, time_posted_epoch);
        return messageDAO.insertMessage(message);
    }

    // Returning a list of all messages in the database from any user:
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    // Returning a message by ID:
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    // Deleting a message by ID:
    public void deleteMessageById(int message_id) {
        messageDAO.deleteMessageById(message_id);
    }

    public void updateMessageText(int message_id, String message_text) {
        messageDAO.updateMessageText(message_id, message_text);
    }

    public List<Message> getMessagesByPoster(int posted_by) {
        return messageDAO.getMessagesByPoster(posted_by);
    }
}

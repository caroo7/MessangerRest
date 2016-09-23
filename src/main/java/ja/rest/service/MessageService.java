package ja.rest.service;

import ja.rest.database.DatabaseInMemory;
import ja.rest.model.Comment;
import ja.rest.model.Message;

import java.util.*;

public class MessageService {

    private Map<Long, Message> messages = DatabaseInMemory.getMessages();

    // improve to accept UTF-8 chars
    public MessageService() {
        Message msg1 = new Message(1, "karol", new Date(), "Hello!");
        Message msg2 = new Message(2, "grzes", new Date(), "Hello JAS!");

        Map<Long, Comment> msg1Comments = new HashMap<>();
        msg1Comments.put(1L, new Comment(1L, "welcome abroad!", new Date(), "grzes"));
        msg1Comments.put(2L, new Comment(2L, "thanks grzes!", new Date(), "karol"));
        msg1.setComments(msg1Comments);

        Map<Long, Comment> msg2Comments = new HashMap<>();
        msg2Comments.put(1L, new Comment(1L, "OMG, JAS?!", new Date(), "karol"));
        msg2Comments.put(1L, new Comment(2L, "shit happens!", new Date(), "grzes"));
        msg2.setComments(msg2Comments);

        messages.put(msg1.getId(), msg1);
        messages.put(msg2.getId(), msg2);
    }

    // GET (retrieve)
    public List<Message> getMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesByYear(int year) {
        List<Message> filteredList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for(Message msg: messages.values()) {
            cal.setTime(msg.getCreated());
            if(cal.get(Calendar.YEAR) == year) {
                filteredList.add(msg);
            }
        }
        return filteredList;
    }

    public List<Message> getPaginatedMessages(int start, int number) {
        List<Message> paginatedMessages = new ArrayList<>(messages.values());
        int startIndex = start - 1;
        if(startIndex + number > paginatedMessages.size()) {
            return new ArrayList<>();
        }
        return paginatedMessages.subList(startIndex, startIndex + number);
    }

    // GET specific
    public Message getMessage(Long id) {
        return messages.get(id);
    }


    // POST (create)
    public Message createMessage(Message message) {
        Long id = messages.size() + 1L;
        message.setId(id);
        messages.put(id, message);
        return message;
    }

    // PUT (update)
    public Message updateMessage(Message message) {
        if(message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    // DELETE (delete)
    public void deleteMessage(Long id) {
        messages.remove(id);
    }

}
package org.kartheek.mygithubproject.Service;

import org.kartheek.mygithubproject.DatabaseClass.DatabaseClass;
import org.kartheek.mygithubproject.Exceptions.DataNotFoundException;
import org.kartheek.mygithubproject.Models.Message;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by kartheekvadlamani on 1/6/17.
 */
public class MessageService {

    public Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {

        messages.put(1L, new Message(1L, "Kartheek", "I am a champ"));
        messages.put(2L, new Message(2L, "kartheek", "If not iam a champ"));

    }

    public List<Message> getAllMessages() {

        return new ArrayList<Message>(messages.values());

    }

    public List<Message> getAllMessagesForYear(int year) {

        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        for (Message message: messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {

        ArrayList<Message> list = new ArrayList<>();
        if (start + size > list.size()) {
            return new ArrayList<>();
        }
        return list.subList(start, start + size);
    }

    public Message getMessage(Long id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id" + id + "Not found");
        }
        return message;
    }

    public Message addMessage(Message message) {

        message.setId((long) (messages.size() + 1));
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {

        if (message.getId() <= 0) {
            return null;
        }

        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Long id) {

       return messages.remove(id);
    }
}

package tn.enig.dao;

import java.util.List;

import tn.enig.model.Client;
import tn.enig.model.Message;

public interface IGestion {
	public Client getClientByLoginAndPassword(String login,String password);
	public void addMessage(Message m);
	public Message getMessageById(int id);
	public List<Message> getMessagesByIdReceiver(int id);
	public List<Message> getMessagesByIdSender(int id);
	public List<Client> getAllClients();
	public void updateMessage(int id);
	public void deleteMessage(int id);
	public int getSentMessagesNumber(int id);
	public int getReceivedMessagesNumber(int id);

}

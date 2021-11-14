package tn.enig.model;

public class Message {
	private int id;
	private String subject;
	private String contenu;
	private Client sender;
	private Client receiver;
	private String date;
	private boolean seen;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(int id, String subject, String contenu, Client sender, Client receiver, String date, boolean seen) {
		super();
		this.id = id;
		this.subject = subject;
		this.contenu = contenu;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.seen = seen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Client getSender() {
		return sender;
	}
	public void setSender(Client sender) {
		this.sender = sender;
	}
	public Client getReceiver() {
		return receiver;
	}
	public void setReceiver(Client receiver) {
		this.receiver = receiver;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", contenu=" + contenu + ", sender=" + sender
				+ ", receiver=" + receiver + ", date=" + date + ", seen=" + seen + "]";
	}
	
	

}

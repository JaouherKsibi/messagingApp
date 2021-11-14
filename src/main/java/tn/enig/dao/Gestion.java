package tn.enig.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tn.enig.model.Client;
import tn.enig.model.Message;
@Repository
public class Gestion implements IGestion{
	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Client getClientByLoginAndPassword(String login, String password) {
		try {
			String req="select * from client where (login=? and password=?)";
			Connection con=dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setString(1, login);
			prst.setString(2, password);
			ResultSet rs=prst.executeQuery();
			if(rs.next()) {
				return(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Client getClientByid(int id) {
		try {
			String req="select * from client where id=?";
			Connection con=dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setInt(1, id);
			ResultSet rs=prst.executeQuery();
			if(rs.next()) {
				return(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addMessage(Message m) {
		try {
			String req="insert into message(subject,contenu,senderid,receiverid,date,seen) values(?,?,?,?,?,?)";
			Connection con=dataSource.getConnection();
			PreparedStatement prst=con.prepareStatement(req);
			prst.setString(1, m.getSubject());
			prst.setString(2, m.getContenu());
			prst.setInt(3, m.getSender().getId());
			prst.setInt(4, m.getReceiver().getId());
			prst.setString(5, m.getDate());
			prst.setBoolean(6, m.isSeen());
			prst.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public Message getMessageById(int id) {
		try {
			String req="select * from message where id=?";
			Connection con=dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setInt(1, id);
			 ResultSet rs= prst.executeQuery();
			 if(rs.next()) {
				 return(new Message(rs.getInt(1),rs.getString(2),rs.getString(3),getClientByid(rs.getInt(4)),getClientByid(rs.getInt(5)),rs.getString(6),rs.getBoolean(7)));
			 }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		return null;
	}

	public List<Message> getMessagesByIdReceiver(int id) {
		List<Message> l=new ArrayList<Message>();
		
			try {
				String req="select * from message where receiverid=?";
				Connection con=dataSource.getConnection();
				PreparedStatement prst =con.prepareStatement(req);
				prst.setInt(1, id);
				 ResultSet rs= prst.executeQuery();
				 while(rs.next()) {
					 l.add(new Message(rs.getInt(1),rs.getString(2),rs.getString(3),getClientByid(rs.getInt(4)),getClientByid(rs.getInt(5)),rs.getString(6),rs.getBoolean(7)));
				 }
				 return l;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public List<Message> getMessagesByIdSender(int id) {
		List<Message> l=new ArrayList<Message>();
		
		try {
			String req="select * from message where senderid=?";
			Connection con=dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setInt(1, id);
			 ResultSet rs= prst.executeQuery();
			 while(rs.next()) {
				 l.add(new Message(rs.getInt(1),rs.getString(2),rs.getString(3),getClientByid(rs.getInt(4)),getClientByid(rs.getInt(5)),rs.getString(6),rs.getBoolean(7)));
			 }
			 return l;
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return null;
	}

	public List<Client> getAllClients() {
		List<Client> l=new ArrayList<Client>();
		try {
			String req="select * from client";
			Connection con=dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				l.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateMessage(int id) {
		String req="UPDATE message SET seen = true WHERE id=?";
		try {
			Connection con=dataSource.getConnection();
			PreparedStatement prst=con.prepareStatement(req);
			prst.setInt(1, id);
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void deleteMessage(int id) {
		 try {
			String req="delete from message where id = ?";
			Connection con =dataSource.getConnection();
			PreparedStatement prst=con.prepareStatement(req);
			prst.setInt(1, id);
			prst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public int getSentMessagesNumber(int id) {
		try {
			String req="select count(*) from message where senderid=?";
			Connection con =dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setInt(1, id);
			ResultSet rs= prst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getReceivedMessagesNumber(int id) {
		try {
			String req="select count(*) from message where receiverid=?";
			Connection con =dataSource.getConnection();
			PreparedStatement prst =con.prepareStatement(req);
			prst.setInt(1, id);
			ResultSet rs= prst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

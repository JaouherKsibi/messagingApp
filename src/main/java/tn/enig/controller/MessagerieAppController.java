package tn.enig.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import tn.enig.dao.IGestion;
import tn.enig.model.Client;
import tn.enig.model.Message;


@Controller
public class MessagerieAppController {
	@Autowired
	IGestion g;
	public void setG(IGestion g) {
		this.g = g;
	}
	
	@GetMapping("login")
	public String goToLogin(Model m) {
		Client c=new Client();
		m.addAttribute("client", c);
		return "login";
	}
	
	@PostMapping("login1")
	public String login(Model m,@ModelAttribute("client") Client c,HttpSession session) {
		Client c1=g.getClientByLoginAndPassword(c.getLogin(), c.getPassword());
		
		if(c1!=null) {
			System.out.println(c1);
			List<Message> msgl=g.getMessagesByIdReceiver(c1.getId()) ;
			session.setAttribute("client1", c1);
			session.setMaxInactiveInterval(-1);
			//session.setAttribute("listeMessageRecu", msgl);
			//m.addAttribute("client1", c1);
			//m.addAttribute("listeMessageRecu", msgl);
			return "redirect:/home";
		}
		return "login";
	}
	
	
	
	@GetMapping("home")
	public String goToHome(Model m,HttpSession session) {
		
		Client c=(Client) session.getAttribute("client1");
		List<Message> msgl=g.getMessagesByIdReceiver(c.getId()) ;
		int sentnb=g.getSentMessagesNumber(c.getId());
		int received=g.getReceivedMessagesNumber(c.getId());
		m.addAttribute("sentNb",sentnb);
		m.addAttribute("receidNb",received);
		System.out.println(msgl);
		m.addAttribute("listeMsg",msgl);
		
		return "home";
	}
	@GetMapping("sendMessage")
	public String goToSendMessage(Model m, HttpSession session) {
		List<Client> lc=g.getAllClients();
		Message m1=new Message();
		Client c=(Client) session.getAttribute("client1");
		int sentnb=g.getSentMessagesNumber(c.getId());
		int received=g.getReceivedMessagesNumber(c.getId());
		m.addAttribute("sentNb",sentnb);
		m.addAttribute("receidNb",received);
		m.addAttribute("message", m1);
		m.addAttribute("listeClient",lc);
		return "envoyerMessage";
	}
	@PostMapping("addMessage")
	public String goToSendMessage(Model m, @ModelAttribute("message") Message m1,@SessionAttribute("client1") Client c) {
		m1.setSeen(false);
		Date d=new Date();
		m1.setSender(c);
		m1.setDate(""+d.getDate()+"/"+d.getMonth()+"/"+d.getYear()+"   "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());
		g.addMessage(m1);
		return "redirect:/sendMessage";
	}
	
	
	@RequestMapping(value = "delete/{msgId}", method=RequestMethod.GET)
	public String deleteMsgById(Model m,@PathVariable int msgId){
	
		g.deleteMessage(msgId);
		
		return "redirect:/home";
	}
	@RequestMapping(value = "update/{msgId}", method=RequestMethod.GET)
	public String updateMsgById(Model m,@PathVariable int msgId,HttpSession session){
		Client c=(Client) session.getAttribute("client1");
		int sentnb=g.getSentMessagesNumber(c.getId());
		int received=g.getReceivedMessagesNumber(c.getId());
		m.addAttribute("sentNb",sentnb);
		m.addAttribute("receidNb",received);
		g.updateMessage(msgId);
		
		Message msg= g.getMessageById(msgId);
		m.addAttribute("msg", msg);
		return "msg";
	}

	@GetMapping("allSentMessages")
	public String goToSentMessages(Model m, HttpSession session) {
		Client c=(Client)session.getAttribute("client1");
		System.out.println(c);
		List<Message> lc=g.getMessagesByIdSender(c.getId());
		int sentnb=g.getSentMessagesNumber(c.getId());
		int received=g.getReceivedMessagesNumber(c.getId());
		m.addAttribute("sentNb",sentnb);
		m.addAttribute("receidNb",received);
		m.addAttribute("listeMessage",lc);
		return "sentMessages";
	}
	@GetMapping("logout")
	public String logout(Model m, HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}

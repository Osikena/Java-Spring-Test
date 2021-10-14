/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ma.start;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Osikena
 */

@Controller
public class ControllerConfig {
	
	
	ApiConfig api = new ApiConfig();
	Logic logic = new Logic();
	
	@RequestMapping("/home")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return "userlogin";
	}
	
	@RequestMapping("/user")
	public String viewpage(Model model){
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		UserData data = new UserData();
		api.getAccountInformation(data, principal.getName());
		model.addAttribute("data", data);
		
		return "userinfo";
	}
	
	@RequestMapping("/create_account")
	public String register(Model model){
		UserData data = new UserData();
		model.addAttribute("data", data);
		return "createaccount";
	}
	
	@RequestMapping(value = "/saveaccount", method = RequestMethod.POST)
	public String createaccount(@ModelAttribute("data") UserData data){
		
		
		if(Integer.parseInt(data.getInitialdeposit()) < 500){
			return "createaccount";
		}else{
			api.createaccount(data);
			return "index";
		}
		
	}
	
	@RequestMapping("/withdrawal")
	public String viewwithdrawlpage(Model model){
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		UserData data = new UserData();
		model.addAttribute("data", data);
		
		return "withdrawal";
	}
	
	@RequestMapping("/deposit")
	public String viewdepositpage(Model model){
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		UserData data = new UserData();
		model.addAttribute("data", data);
		
		return "deposit";
	}
	
	@RequestMapping("/statement")
	public String viewstatmentpage(Model model){
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		UserData data = new UserData();
		api.getaccountstatement(data, principal.getName());
		model.addAttribute("data", data);
		
		return "statement";
	}
	
	@RequestMapping(value = "/performwithdrawal", method = RequestMethod.POST)
	public String performwithdrawal(@ModelAttribute("data") UserData data){
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		api.getWithdrawalInformation(data, principal.getName());
		
		int amountrem = Integer.parseInt(data.getBalance()) - Integer.parseInt(data.getAmount());
		
		if(amountrem < 500){
			
			
			return "withdrawal";
			
		}else{
			
			api.setWithdrawalInformation(data, Integer.toString(amountrem));
			return "responsepage";
			
		}
		
	}
	
	@RequestMapping(value = "/performdeposit", method = RequestMethod.POST)
	public String performdeposit(@ModelAttribute("data") UserData data){
		
		
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		api.getdepositInformation(data, principal.getName());
		
		int amountrem = Integer.parseInt(data.getBalance()) + Integer.parseInt(data.getAmount());
		
		if(Integer.parseInt(data.getAmount()) > 1000000 || Integer.parseInt(data.getAmount()) < 1){
			
			
			return "deposit";
			
		}else{
			
			api.setWithdrawalInformation(data, Integer.toString(amountrem));
			return "responsepage";
			
		}
		
	}
	
	
}

package com.italk2learn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.italk2learn.bo.inter.IExerciseSequenceBO;
import com.italk2learn.bo.inter.ILoginUserService;
import com.italk2learn.vo.ExerciseSequenceRequestVO;
import com.italk2learn.vo.ExerciseSequenceResponseVO;
import com.italk2learn.vo.ExerciseVO;
import com.italk2learn.vo.HeaderVO;
import com.italk2learn.vo.UserDetailsVO;
import com.italk2learn.vo.WozVO;

/**
 * Handles requests for the application exercise sequence.
 */
@Controller
@Scope("session")
@RequestMapping("/monitorWOZ")
public class MonitorWOZController {
	
	
	private LdapUserDetailsImpl user;
	
	private static final Logger logger = LoggerFactory
			.getLogger(MonitorWOZController.class);

	/*Services*/
	private IExerciseSequenceBO exerciseSequenceService;
	private ILoginUserService loginUserService;

    @Autowired
    public MonitorWOZController(IExerciseSequenceBO exerciseSequence, ILoginUserService loginUserService) {
    	this.exerciseSequenceService = exerciseSequence;
    	this.loginUserService=loginUserService;
    }
    
    @ModelAttribute("allExercises")
    public List<ExerciseVO> populateExercises() {
    	logger.info("JLF --- MonitorWOZ populateExercises --- Getting all exercises from the database");
    	try {
	    	user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
	    	request.setHeaderVO(new HeaderVO());
			request.getHeaderVO().setLoginUser(user.getUsername());
	        return this.exerciseSequenceService.findAllExercises(request).getResponse();
    	} catch (Exception e){
			logger.error(e.toString());
			return null;
		}    
    }
    
    
    /**
	 * JLF: Gett all students that they have woz account
	 */
    @ModelAttribute("allStudents")
    public List<UserDetailsVO> populateStudents() {
    	logger.info("JLF --- MonitorWOZ populateStudents --- getting all students that they have woz account");
    	List<UserDetailsVO> res= new ArrayList<UserDetailsVO>();
    	UserDetailsVO var= new UserDetailsVO();
    	var.setUser("student1");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("student2");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("student3");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("student4");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("student5");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("schuler1");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("schuler2");
    	res.add(var);
    	var= new UserDetailsVO();
    	var.setUser("schuler3");
    	res.add(var);
        return res;
    }
	
	/**
	 * JLF: Get the main view
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String monitorWOZInit(Model model) {
		logger.info("JLF --- MonitorWOZ init --- Initiliasing the main view from the Wizard of Oz");
		try {
			user = (LdapUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("messageInfo", new ExerciseVO());
			return "monitorWOZ";
		} catch (Exception e){
			logger.info("Returning to loginpage due previous errors");
			logger.error(e.toString());
			return "redirect:/login";
		}
	}

	/**
	 * JLF: Insert next exercise in a sequence of exercise by a given user
	 */
	@RequestMapping(value = "/insertNextID", method = RequestMethod.POST)
    public @ResponseBody String insertNextExercise(@RequestBody WozVO messageForm, HttpServletRequest req){
		logger.info("JLF --- insertNextExercise()");
		ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("monitorWOZ");
		try{
			request.setHeaderVO(new HeaderVO());
			request.getHeaderVO().setLoginUser(user.getUsername());
			request.setIdUser(getLoginUserService().getIdUser(messageForm.getUser()));
			request.setIdExercise(getLoginUserService().getSimpleIdExersiceUser(messageForm.getUser()));
			request.setIdNextexercise(Integer.parseInt(messageForm.getIdNextexercise()));
			ExerciseSequenceResponseVO response=getExerciseSequenceService().insertNextIDExercise(request);
			return "success";
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return "error";
		
	}
	
	/**
	 * JLF: Insert a sequence of id's by a given user
	 */
	@RequestMapping(value = "/insertSequenceByUser", method = RequestMethod.POST)
    public @ResponseBody String insertSequenceByUser(@RequestBody WozVO messageForm){
		logger.info("JLF --- MonitorWOZ insertSequenceByUser() --- Inserting a sequence of exercises for that user");
		logger.info("user= "+ messageForm.getUser()+" ,sequence="+ messageForm.getSequence());
		ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("monitorWOZ");
		try{
			request.setHeaderVO(new HeaderVO());
			request.getHeaderVO().setLoginUser(user.getUsername());
			request.setIdUser(getLoginUserService().getIdUser(messageForm.getUser()));
			request.setSequence(messageForm.getSequence());
			ExerciseSequenceResponseVO response=getExerciseSequenceService().insertSequenceByUser(request);
			return "success";
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return "error";
		
	}
	
	/**
	 * JLF: Insert a sequence of id's by a given user
	 */
	@RequestMapping(value = "/insertConditionByUser", method = RequestMethod.POST)
    public @ResponseBody String insertConditionByUser(@RequestBody WozVO messageForm, HttpServletRequest req){
		logger.info("JLF --- MonitorWOZ insertSequenceByUser() --- Inserting a sequence of exercises for that user");
		logger.info("user= "+ messageForm.getUser()+" ,condition="+ messageForm.getCondition());
		ExerciseSequenceRequestVO request= new ExerciseSequenceRequestVO();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("monitorWOZ");
		try{
			request.setHeaderVO(new HeaderVO());
			request.getHeaderVO().setLoginUser(user.getUsername());
			request.setIdUser(getLoginUserService().getIdUser(messageForm.getUser()));
			request.setCondition(messageForm.getCondition());
			ExerciseSequenceResponseVO response=getExerciseSequenceService().insertCondition(request);
			if (messageForm.getCondition()==3) {
				if (messageForm.getLang().contains("de")){
					String[] seq = new String[]{"67","61","64","65","63","69","74","78","73","77","70","71","75","79","62","68"};
					messageForm.setSequence(seq);
					insertSequenceByUser(messageForm);
				} else {
					String[] seq = new String[]{"103","104","105","106","107","108","109","110","111","112","113","114"};
					messageForm.setSequence(seq);
					insertSequenceByUser(messageForm);
				}
			} else if (messageForm.getCondition()==4) {
				String[] seq = new String[]{"122","117","123","116","118","124","125","119","126","127","128","120","129","130","131","121"};
				messageForm.setSequence(seq);
				insertSequenceByUser(messageForm);
			} else {
				String[] seq = new String[]{"101"};
				messageForm.setSequence(seq);
				insertSequenceByUser(messageForm);
			}
			return "success";
		}
		catch (Exception e){
			logger.error(e.toString());
		}
		return "error";
		
	}

	public IExerciseSequenceBO getExerciseSequenceService() {
		return exerciseSequenceService;
	}

	public void setExerciseSequenceService(IExerciseSequenceBO exerciseSequenceService) {
		this.exerciseSequenceService = exerciseSequenceService;
	}

	public ILoginUserService getLoginUserService() {
		return loginUserService;
	}

	public void setLoginUserService(ILoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

}

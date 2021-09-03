package com.org.tutorials.tutorialservices;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.org.tutorialservices.model.Tutorials;

@RestController
@CrossOrigin(origins="http://localhost:4200") 
@RequestMapping(value="/api")
public class TutorialController {
	
	@Autowired
	private TutorialService tutorialService; 
	
	@PostMapping("addTutorial")
	public int saveTutorial(@RequestBody Tutorials tutorials)
	{
		System.out.println("controller");
		
		try {
			int status=tutorialService.saveTutorial(tutorials);
			return status;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
			
		}
		
	}
	
	@GetMapping("Tutorials-list")
	public List<Tutorials> getAllTutorials()
	{
		List<Tutorials> tutorialList=new ArrayList<Tutorials>();
		try
		{
			tutorialList=tutorialService.getTutorials();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tutorialList;
	}
	
	 @GetMapping("tutorial/{tutorial_id}")  
	    public List<Tutorials> allstudentByID(@PathVariable("tutorial_id") int tutorial_id,Tutorials tutorials) { 
		 List<Tutorials> tutorialList=new ArrayList<Tutorials>();
		 try{
			 tutorials.setTutorial_Id(tutorial_id);  
			 tutorialList= tutorialService.getTutorialByID(tutorials); 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	       return tutorialList;   
	    }
	
	@DeleteMapping("delete-tutorial/{tutorial_id}")
	public boolean deleteTutorials(@PathVariable("tutorial_id") int tutorial_id,Tutorials tutorials)
	{
		System.out.println(tutorial_id);
		boolean status=false;
		try
		{
			tutorials.setTutorial_Id(tutorial_id);  
			status=tutorialService.deleteTutorials(tutorials);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	@PostMapping("updateTutorials/{tutorial_id}")
	public boolean updateTutorials(@PathVariable("tutorial_id") int tutorial_id,@RequestBody Tutorials tutorials)
	{
		boolean status=false;
		try
		{
			tutorials.setTutorial_Id(tutorial_id);  
			status=tutorialService.updateTutorials(tutorials);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
		
	}
	@PostMapping("/uploadImage/{tutorial_id}")  
    public int handleFileUpload(@PathVariable int tutorial_id , @RequestParam("file") MultipartFile file, HttpSession session) {
		System.out.println("upload");
        return tutorialService.store(file, tutorial_id, session);          
    }  

}

package com.org.tutorials.tutorialservices;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.org.tutorialservices.model.Tutorials;

public interface TutorialService {
	
	  public int saveTutorial(Tutorials tutorials);  
	    public List<Tutorials> getTutorials();  
	    public boolean deleteTutorials(Tutorials tutorials);  
	    public boolean updateTutorials(Tutorials tutorials);
	    public List<Tutorials> getTutorialByID(Tutorials tutorials);
	    public int store(MultipartFile file, int tutorial_id ,  HttpSession session);

}

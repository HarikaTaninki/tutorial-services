package com.org.tutorials.tutorialservices;

import java.util.List;

import com.org.tutorialservices.model.Tutorials;

public interface TutorialDao {
	 
	   public int saveTutorial(Tutorials tutorials);  //test
	    public List<Tutorials> getTutorials();  
	    public boolean deleteTutorials(Tutorials tutorials);  
	    public boolean updateTutorials(Tutorials tutorials); 
	    public List<Tutorials> getTutorialByID(Tutorials tutorials); 
	    public Tutorials getTutorialDetails(int tutorial_id); 
	    public int updateProfileImage(String profileImage , int tutorial_id); 

}

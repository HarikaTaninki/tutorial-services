package com.org.tutorials.tutorialservices;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.tutorialservices.model.Tutorials;

@Service  
@Transactional 
public class TutorialServiceImpl implements TutorialService {
	
	@Autowired
	private TutorialDao tutorial_dao;

	@Override
	public int saveTutorial(Tutorials tutorials) {
		return tutorial_dao.saveTutorial(tutorials);
	}

	@Override
	public List<Tutorials> getTutorials() {
		return tutorial_dao.getTutorials();
	}

	@Override
	public boolean deleteTutorials(Tutorials tutorials) {
		return tutorial_dao.deleteTutorials(tutorials);
	}

	@Override
	public boolean updateTutorials(Tutorials tutorials) {
		// TODO Auto-generated method stub
		return tutorial_dao.updateTutorials(tutorials);
	}

	@Override
	public List<Tutorials> getTutorialByID(Tutorials tutorials) {
		// TODO Auto-generated method stub
		return tutorial_dao.getTutorialByID(tutorials);
	}
	
	@Transactional  
    public Tutorials getTutorialsDetails(int tutorial_id) {  
        return tutorial_dao.getTutorialDetails(tutorial_id);  
    }
	 @Transactional  
	    public int store(MultipartFile file, int tutorial_id, HttpSession session) {
		 System.out.println("multi");
	          
	        Path rootLocation = Paths.get(session.getServletContext().getRealPath("/resources/images"));  
	          
	        System.out.println("rootLocation  ==  " + rootLocation);  
	          
	        Tutorials tutorialsDetails = this.getTutorialsDetails(tutorial_id);  
	           
	         String nameExtension[] = file.getContentType().split("/");  
	  
	         String profileImage = tutorial_id + "." + nameExtension[1];  
	              
	         System.out.println("ProfileImage  :: " + profileImage);  
	           
	         if(tutorialsDetails.getTutorial_Id() > 0 )  
	         {  
	               
	            if(tutorialsDetails.getProfileImage() == null || tutorialsDetails.getProfileImage() == " " || tutorialsDetails.getProfileImage() == "" )  
	            {  
	                try  
	                {  
	                    Files.copy(file.getInputStream(),rootLocation.resolve(profileImage));
	                    System.out.println("file");
	                    int result = tutorial_dao.updateProfileImage(profileImage, tutorial_id);    
	                    if(result > 0)  
	                        return result;  
	                    else  
	                        return -5;  
	                }  
	                catch(Exception exception)  
	                {  
	                    System.out.println("error while uploading image catch:: " + exception.getMessage());  
	                    return -5;  
	                }  
	            }  
	            else  
	            {  
	                try  
	                {  
	                    //Files.delete(rootLocation.resolve(profileImage));  
	                      
	                    Files.delete(rootLocation.resolve(tutorialsDetails.getProfileImage()));  
	                      
	                    Files.copy(file.getInputStream(),rootLocation.resolve(profileImage));  
	                    int result = tutorial_dao.updateProfileImage(profileImage, tutorial_id);    
	                    if(result > 0)  
	                        return result;  
	                    else  
	                        return -5;  
	                }  
	                catch(Exception exception)  
	                {  
	                    System.out.println("Error while uploading image when image is already Exists :: " + exception.getMessage());  
	                    return -5;  
	                }  
	            }  
	        }  
	        else {  
	            return 0;  
	        }  
	    }  
	  

}

package com.org.tutorials.tutorialservices;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.tutorialservices.model.Tutorials;

@Repository 
public class TutorialDaoImpl implements TutorialDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int saveTutorial(Tutorials tutorials) {
		System.out.println("dao");
		
		try
		{
			int status = (Integer)sessionFactory.getCurrentSession().save(tutorials);
			return status;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<Tutorials> getTutorials() {
	Session currentSession=sessionFactory.getCurrentSession();
	Query<Tutorials> query=currentSession.createQuery("from Tutorials",Tutorials.class);
	List<Tutorials> tutorialList=query.getResultList();
	return tutorialList;
	}

	@Override
	public boolean deleteTutorials(Tutorials tutorials) {
		boolean status=false;
		try
		{
			sessionFactory.getCurrentSession().delete(tutorials);
			status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public boolean updateTutorials(Tutorials tutorials) {
		boolean status=false;
		try
		{
			sessionFactory.getCurrentSession().update(tutorials);
			status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Tutorials> getTutorialByID(Tutorials tutorials) {
		Session currentSession = sessionFactory.getCurrentSession();  
        Query<Tutorials> query=currentSession.createQuery("from Tutorials where tutorial_Id=:tutorial_Id", Tutorials.class);  
        query.setParameter("tutorial_Id", tutorials.getTutorial_Id());  
        List<Tutorials> list=query.getResultList();  
        return list;  
	}
	 public Tutorials getTutorialDetails(int tutorial_id) {  
	        Session session = null;  
	        try  
	        {  
	            session = sessionFactory.getCurrentSession();  
	            Tutorials tutorialsDetail= session.get(Tutorials.class, tutorial_id);  
	            return tutorialsDetail;  
	        }  
	        catch(Exception exception)  
	        {  
	            System.out.println("Exception in saving data into the database " + exception);  
	            return null;  
	        }  
	        finally  
	        {  
	            session.flush();  
	        }  
	    }  
	  
	    public int updateProfileImage(String profileImage, int tutorial_id) {  
	        Session session= sessionFactory.getCurrentSession();  
	        int result;  
	          
	        try  
	        {  
	            Query<Tutorials> query = session.createQuery("update Tutorials set profileImage = :profileImage where tutorial_id=:tutorial_id ");  
	            query.setParameter("profileImage", profileImage);  
	            query.setParameter("tutorial_id", tutorial_id);  
	            result = query.executeUpdate();  
	            if(result > 0)  
	            {  
	                return result;  
	            }  
	            else return -5;  
	        }  
	        catch(Exception exception)  
	        {  
	            System.out.println("Error while updating profileImage from DAO :: " + exception.getMessage());  
	            return -5;  
	        }  
	        finally  
	        {  
	            session.flush();  
	        }  
	          
	          
	    }  

}

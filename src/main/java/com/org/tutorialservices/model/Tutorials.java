package com.org.tutorialservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tutorials")
public class Tutorials {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tutorial_Id;
	private String title;
	private String description;
	private String profileImage;

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public int getTutorial_Id() {
		return tutorial_Id;
	}

	public void setTutorial_Id(int tutorial_Id) {
		this.tutorial_Id = tutorial_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.shubhamInstaWeb.services;

import java.util.List;

import com.shubhamInstaWeb.exception.StoryException;
import com.shubhamInstaWeb.exception.UserException;
import com.shubhamInstaWeb.model.Story;

public interface StoryService {

	public Story createStory(Story story,Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException;
	
	
}

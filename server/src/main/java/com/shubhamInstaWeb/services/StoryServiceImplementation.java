package com.shubhamInstaWeb.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhamInstaWeb.dto.UserDto;
import com.shubhamInstaWeb.exception.StoryException;
import com.shubhamInstaWeb.exception.UserException;
import com.shubhamInstaWeb.model.Story;
import com.shubhamInstaWeb.model.User;
import com.shubhamInstaWeb.repository.StoryRepository;
import com.shubhamInstaWeb.repository.UserRepository;

@Service
public class StoryServiceImplementation implements StoryService {
	
	@Autowired
	private StoryRepository storyRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Story createStory(Story story, Integer userId) throws UserException {
		
		User user = userService.findUserById(userId);
	
		
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		
		story.setUserDto(userDto);
		story.setTimestamp(LocalDateTime.now());
		
		user.getStories().add(story);
		
//		userRepo.save(user);
		return storyRepo.save(story);
		
		
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws UserException,StoryException {
		
		User user = userService.findUserById(userId);
		
		List<Story>  stories = user.getStories();
		
		if(stories.size()==0) {
			throw new StoryException("This user Don't have any Story");
		}
		
		return stories;
	}

}

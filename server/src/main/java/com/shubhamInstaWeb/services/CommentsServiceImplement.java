package com.shubhamInstaWeb.services;

import com.shubhamInstaWeb.dto.UserDto;
import com.shubhamInstaWeb.exception.CommentException;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhamInstaWeb.exception.PostException;
import com.shubhamInstaWeb.exception.UserException;
import com.shubhamInstaWeb.model.Comments;
import com.shubhamInstaWeb.model.Post;
import com.shubhamInstaWeb.model.User;
import com.shubhamInstaWeb.repository.CommentRepository;
import com.shubhamInstaWeb.repository.PostRepository;

@Service
public class CommentsServiceImplement implements CommentService {
	
	@Autowired
	private CommentRepository repo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepo;

	
	
	@Override
	public Comments createComment(Comments comment, Integer postId, Integer userId) throws PostException, UserException {
		
		User user=userService.findUserById(userId);
		
		Post post=postService.findePostById(postId);
		
		// TODO Auto-generated method stub
		
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		
		comment.setUserDto(userDto);
		comment.setCreatedAt(LocalDateTime.now());
		Comments newComment= repo.save(comment);
		
		post.getComments().add(newComment);
		
		postRepo.save(post);
		
		return newComment;
	}


	@Override
	public Comments findCommentById(Integer commentId) throws CommentException {
		Optional<Comments> opt=repo.findById(commentId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CommentException("comment not exist with id : "+commentId);
	}

	@Override
	public Comments likeComment(Integer commentId, Integer userId) throws UserException, CommentException {
		// TODO Auto-generated method stub
		
		User user=userService.findUserById(userId);
		Comments comment=findCommentById(commentId);
		
		
		UserDto userDto=new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		
		comment.getLikedByUsers().add(userDto);
		System.out.println(("like ------- "+" ------ "+comment));
		return repo.save(comment);
		
	}


	@Override
	public Comments unlikeComment(Integer commentId, Integer userId) throws UserException, CommentException {
		User user=userService.findUserById(userId);
		Comments comment=findCommentById(commentId);
		
		comment.getLikedByUsers().remove(user);
		
		return repo.save(comment);
		
	}




	
	
	
	
	

}

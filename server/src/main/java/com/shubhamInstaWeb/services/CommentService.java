package com.shubhamInstaWeb.services;

import com.shubhamInstaWeb.exception.CommentException;
import com.shubhamInstaWeb.exception.PostException;
import com.shubhamInstaWeb.exception.UserException;
import com.shubhamInstaWeb.model.Comments;

public interface CommentService {
	
	public Comments createComment(Comments comment,Integer postId,Integer userId) throws PostException, UserException;

	public Comments findCommentById(Integer commentId) throws CommentException;
	public Comments likeComment(Integer CommentId,Integer userId) throws UserException, CommentException;
	public Comments unlikeComment(Integer CommentId,Integer userId) throws UserException, CommentException;
}

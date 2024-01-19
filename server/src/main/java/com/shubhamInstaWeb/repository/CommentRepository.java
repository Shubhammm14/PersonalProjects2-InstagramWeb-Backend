package com.shubhamInstaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shubhamInstaWeb.model.Comments;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

}

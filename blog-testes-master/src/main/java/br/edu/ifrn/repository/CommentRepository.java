package br.edu.ifrn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	  Optional<Comment> findByBody(String body);
}

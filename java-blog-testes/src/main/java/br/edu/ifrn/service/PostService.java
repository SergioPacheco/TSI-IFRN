package br.edu.ifrn.service;

import org.springframework.data.domain.Page;

import br.edu.ifrn.model.Post;
import br.edu.ifrn.model.User;

import java.util.Optional;

public interface PostService {

    Optional<Post> findForId(Long id);

    Post save(Post post);

    /**
     * Encontra uma {@link Page) de {@link Post} do utilizador fornecido, ordenada por data
     */
    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    /**
     * Encontra uma {@link Page) de todas as {@link postagens} ordenadas por data
     */
    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);
}

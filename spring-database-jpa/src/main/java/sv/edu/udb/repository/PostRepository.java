package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Leer todos los posts
    public List<Post> findAll() {
        final String QUERY = "select p from Post p";
        return entityManager
                .createQuery(QUERY, Post.class)
                .getResultList();
    }

    // Leer un post por su ID (usando Optional para mayor seguridad)
    public Optional<Post> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(Post.class, id));
    }

    // Crear un nuevo post
    @Transactional
    public Post save(final Post post) {
        entityManager.persist(post);
        return post;
    }

    // Actualizar un post existente
    @Transactional
    public Post update(final Post post) {
        // merge actualiza el estado del post y lo retorna
        return entityManager.merge(post);
    }

    // Eliminar un post
    @Transactional
    public void delete(final Post post) {
        // Si el post no está gestionado, se hace un merge antes de eliminar
        entityManager.remove(entityManager.contains(post) ? post : entityManager.merge(post));
    }

    // Eliminar un post por su ID (opcional, para facilitar la eliminación directa)
    @Transactional
    public void deleteById(final Long id) {
        findById(id).ifPresent(this::delete);
    }
}


package com.example.geriadur.repositories;

import com.example.geriadur.domain.consultation.Author;
import com.example.geriadur.domain.consultation.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByAuthorName(String authorName);
}

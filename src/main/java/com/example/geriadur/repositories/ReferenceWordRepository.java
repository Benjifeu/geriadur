package com.example.geriadur.repositories;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.domain.ReferenceWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReferenceWordRepository extends CrudRepository<ReferenceWord, Long> {
    Optional<ReferenceWord> findByRefWordName(String refWordName);

}

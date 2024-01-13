package com.example.geriadur.repositories;

import com.example.geriadur.domain.EtymonName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EtymonNameRepository extends JpaRepository<EtymonName, Long> {
    Optional<EtymonName> findEtymonNameByEtymonId(Long id);
    Optional<EtymonName> findEtymonNameByCurrentName(String currentName);
    @Query("FROM EtymonName g where g.wordTheme = :word_theme ORDER BY RAND() LIMIT 15")
    Set<EtymonName> find15EtymonNamesByWordTheme(@Param("word_theme")int wordTheme);

    Set<EtymonName> findAllEtymonNamesByWordTheme(@Param("word_theme")int wordTheme);
}

package com.example.geriadur.repositories;

import com.example.geriadur.entity.ProperNoun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProperNounRepository extends JpaRepository<ProperNoun, Long> {
    Optional<ProperNoun> findProperNounByCurrentName(String currentName);
    @Query(value = "SELECT * FROM propernoun where word_theme = ?1 and confirmed=1 ORDER BY RAND() LIMIT 15",
            nativeQuery = true)
    Set<ProperNoun> find15ProperNounsByWordTheme(@Param("word_theme")int wordTheme);
    
    @Query(value = "SELECT * FROM propernoun where word_theme = ?1 and confirmed=1",
            nativeQuery = true)
    Set<ProperNoun> findAllProperNounsByWordTheme(@Param("word_theme")int wordTheme);

    @Query(value = "SELECT * FROM propernoun where confirmed=1",
            nativeQuery = true)
    Set<ProperNoun> findAllConfirmedNouns();
}

package com.example.geriadur.repositories;

import com.example.geriadur.entity.EtymonName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EtymonNameRepository extends JpaRepository<EtymonName, Long> {
    Optional<EtymonName> findEtymonNameByCurrentName(String currentName);
    @Query(value = "SELECT * FROM etymon_name where word_theme = ?1 ORDER BY RAND() LIMIT 15",
            nativeQuery = true)
    Set<EtymonName> find15EtymonNamesByWordTheme(@Param("word_theme")int wordTheme);

    Set<EtymonName> findAllEtymonNamesByWordTheme(@Param("word_theme")int wordTheme);
}

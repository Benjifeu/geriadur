package com.example.geriadur.repositories;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.SemanticField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EtymonNameRepository extends JpaRepository<EtymonName, Long> {
    Optional<EtymonName> findEtymonNameByEtymonId(Long id);
    Page<EtymonNameRepository> findRandomAmout(Pageable pageable);
    Set<EtymonName> findEtymonNamesBySemanticField(SemanticField semanticField);
}

package com.ghostnet.ghostnet.repository;

import com.ghostnet.ghostnet.model.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
    // spring data jpa generiert die SQL abfrage automatisch, sehr praktisch
    List<GhostNet> findByStatus(GhostNet.Status status);
}
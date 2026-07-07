package com.example.nutripath.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nutripath.Entity.ClientProfile;
import java.util.Optional;

public interface ClientProfileRepo extends JpaRepository<ClientProfile, Long> {
Optional<ClientProfile> findByAccountId(Long accountId);   
}

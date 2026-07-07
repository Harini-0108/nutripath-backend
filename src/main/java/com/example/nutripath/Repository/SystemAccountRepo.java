package com.example.nutripath.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nutripath.Entity.SystemAccount;
import java.util.*;

public interface SystemAccountRepo extends JpaRepository<SystemAccount,Long> {
  Optional<SystemAccount> findByEmail(String email);
}

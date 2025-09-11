package com.steve.Picgo.repositories;

import com.steve.Picgo.entites.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserFileRepository extends JpaRepository<UserFile, Long> {
    Optional<UserFile> findByUserIdAndType(String userId, UserFile.FileType type);
}

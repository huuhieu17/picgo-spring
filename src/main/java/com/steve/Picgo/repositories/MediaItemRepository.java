package com.steve.Picgo.repositories;

import com.steve.Picgo.entites.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaItemRepository extends JpaRepository<MediaItem, UUID> {
}

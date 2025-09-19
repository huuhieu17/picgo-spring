package com.steve.Picgo.repositories;

import com.steve.Picgo.entites.Post;
import com.steve.Picgo.entites.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query(value = """
            SELECT p FROM Post p
            WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(p.latitude)) *
                               cos(radians(p.longitude) - radians(:lng)) +
                               sin(radians(:lat)) * sin(radians(p.latitude)))) < :radius
            """)
    List<Post> findNearbyPosts(@Param("lat") Double latitude,
                               @Param("lng") Double longitude,
                               @Param("radius") Double radiusKm);

    Slice<Post> findByAuthor(UserEntity author, Pageable pageable);
}

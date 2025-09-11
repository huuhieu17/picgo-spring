package com.steve.Picgo.entites;

import com.steve.Picgo.enums.PostVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Column(length = 1000)
    private String caption;

    @ElementCollection
    @CollectionTable(name = "post_hashtags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "hashtag")
    private List<String> hashtags;

    @ElementCollection
    @CollectionTable(name = "post_mentions", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "mention")
    private List<String> mentions;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaItem> media;

    @Enumerated(EnumType.STRING)
    private PostVisibility visibility = PostVisibility.PUBLIC;

    @Embedded
    private PostMetrics metrics = new PostMetrics();

    private boolean isPinned = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}


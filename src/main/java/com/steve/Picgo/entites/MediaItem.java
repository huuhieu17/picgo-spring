package com.steve.Picgo.entites;

import com.steve.Picgo.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "media_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

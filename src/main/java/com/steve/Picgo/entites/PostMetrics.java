package com.steve.Picgo.entites;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMetrics {
    private int likes = 0;
    private int comments = 0;
    private int shares = 0;
    private int views = 0;
    private int saves = 0;
}

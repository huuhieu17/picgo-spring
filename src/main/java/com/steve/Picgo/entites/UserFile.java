package com.steve.Picgo.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userId;

    String fileName; // file name trên server
    String fileUrl;  // url trả về từ server

    @Enumerated(EnumType.STRING)
    FileType type; // AVATAR, COVER, OTHER

    public enum FileType {
        AVATAR, COVER, OTHER
    }
}

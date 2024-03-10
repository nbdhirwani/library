package com.sample.app.library.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private UUID id;
    private String name;
    private String authorName;
    private Boolean isBorrowed;
}

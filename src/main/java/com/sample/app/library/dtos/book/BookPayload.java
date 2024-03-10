package com.sample.app.library.dtos.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPayload {
    private String name;
    private String authorName;
    private Boolean isBorrowed;
}

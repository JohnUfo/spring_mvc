package com.web.dto;

import com.web.entity.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;

    @NotEmpty(message = "Event name should not be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @NotEmpty(message = "Event type should not be empty")
    private String type;

    @NotEmpty(message = "Event photo Url should not be empty")
    private String photoUrl;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;
}

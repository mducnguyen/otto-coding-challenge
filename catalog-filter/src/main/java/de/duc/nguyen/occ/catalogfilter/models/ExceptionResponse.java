package de.duc.nguyen.occ.catalogfilter.models;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String error;
    private Integer code;
    private String errorMessage;
}
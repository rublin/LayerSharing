package io.golayer.sharing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class ShareResponseDto {
    private Long id;
    private String sheet;
    private String selection;
    private List<String> emails;
}

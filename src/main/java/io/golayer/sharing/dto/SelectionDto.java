package io.golayer.sharing.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SelectionDto {

    private Long id;

    @NotEmpty(message = "is mandatory field.")
    @Size(max = 50)
    @Pattern(regexp = "^('[\\w\\s]+'|[\\w\\s]+)(![A-Z]+[0-9]+)?(:[A-Z]+[0-9]+)?", message = "selection has wrong format")
    private String selection;
}

package io.golayer.sharing.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateSharingRequestDto {
    @Valid
    @NotEmpty
    private List<EmailDto> emails;

    @Valid
    @NotEmpty
    private List<SelectionDto> selections;
}

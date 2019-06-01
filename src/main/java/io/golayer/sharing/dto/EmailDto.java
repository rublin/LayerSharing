package io.golayer.sharing.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EmailDto {
    @NotEmpty(message = "is mandatory field.")
    @Size(max = 64)
    @Email
    private String email;
}

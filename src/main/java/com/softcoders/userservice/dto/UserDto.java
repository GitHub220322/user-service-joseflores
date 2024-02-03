package com.softcoders.userservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {

    @NotNull
    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @Size(min = 1,max = 2)
    @Min(value = 1)
    @Max(value = 2)
    private Integer edad;

    @NotNull
    @Size(min = 1,max = 8)
    private String dni;

}

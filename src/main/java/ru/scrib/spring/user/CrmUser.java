package ru.scrib.spring.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CrmUser {

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 3, message = "Должно содержать не меньше 3 символов")
    private String userName;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String password;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String matchingPassword;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String firstName;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String lastName;

    @NotNull(message = "обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String email;

    public CrmUser() {
    }
}

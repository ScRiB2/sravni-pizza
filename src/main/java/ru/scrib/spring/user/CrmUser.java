package ru.scrib.spring.user;

import lombok.Getter;
import lombok.Setter;
import ru.scrib.spring.string.StringHelper;
import ru.scrib.spring.validation.FieldMatch;
import ru.scrib.spring.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@FieldMatch.List(
        @FieldMatch(first = "password", second = "matchingPassword", message = "Пароли не совпадают"))
public class CrmUser {

    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 3, message = "Должно содержать не меньше 3 символов")
    private String userName;

    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String password;

    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String matchingPassword;

    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String firstName;

    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String lastName;

    @ValidEmail
    @NotNull(message = "Обязательно к заполнению")
    @Size(min = 1, message = "слишком коротко")
    private String email;

    public CrmUser() {
    }

    public void setFirstName(String firstName) {
        this.firstName = StringHelper.convertFromUTF8(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = StringHelper.convertFromUTF8(lastName);
    }
}

package ru.user;

import javax.validation.constraints.NotNull;

public class EnterToPage {
    @NotNull(message = "Введите email")
    private String email;
    @NotNull(message = "Введите пароль")
    private String password;

    public EnterToPage(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public EnterToPage() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

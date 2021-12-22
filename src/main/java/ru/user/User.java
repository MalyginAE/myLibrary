package ru.user;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class User {
    private int user_id;
    @NotNull(message = "Вы не ввели имя")
    private String name;
    @NotNull(message = "Вы не ввели фамилию")
    private String surname;
    //private Date date;
    @NotNull(message = "Вы не ввели email")
    private String email;
    @NotNull(message = "Вы не ввели пароль")
    private String password;
    private int imageId;

    public User() {
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.valueOf(getUser_id());
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUser_id() == user.getUser_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getEmail());
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

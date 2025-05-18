package org.dofire.models;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Cloneable {

    private String name;
    private String email;
    private String password;

    public Object shallowClone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }

    public User deepClone(){
        return new User(this.name, this.email, this.password);
    }

    public static List<User> fake(int count) {
        Faker faker = new Faker();
        List<User> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            users.add(new User(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password()
            ));
        }

        return users;
    }

    public static User fake() {
        Faker faker = new Faker();
        return new User(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password()
        );
    }

}

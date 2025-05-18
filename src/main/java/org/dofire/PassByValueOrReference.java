package org.dofire;

import org.dofire.models.User;

public class PassByValueOrReference {

    public static void main(String[] args) {
        var user = User.fake();

        System.out.println("Before: " + user);
        tryModify(user);

        System.out.println("After tryModify: " + user);
    }

    public static void tryModify(User user) {
        user = User.fake();
        System.out.println("Inside tryModify: " + user);
    }

}

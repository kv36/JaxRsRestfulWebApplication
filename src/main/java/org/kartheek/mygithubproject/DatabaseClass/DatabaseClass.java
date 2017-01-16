package org.kartheek.mygithubproject.DatabaseClass;

import org.kartheek.mygithubproject.Models.Message;
import org.kartheek.mygithubproject.Models.Profile;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kartheekvadlamani on 1/7/17.
 */
public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}

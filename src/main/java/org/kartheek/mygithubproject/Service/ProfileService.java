package org.kartheek.mygithubproject.Service;

import org.kartheek.mygithubproject.DatabaseClass.DatabaseClass;
import org.kartheek.mygithubproject.Models.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kartheekvadlamani on 1/7/17.
 */
public class ProfileService {

    public Map<String, Profile> profiles = DatabaseClass.getProfiles();


    public ProfileService() {

        profiles.put("Kartheek", new Profile(1L, "Kartheek@RevenueConduit", "kartheek", "vadlamani"));
        profiles.put("Udaya Pitchika", new Profile(2L, "UdayaCrazyboy@RevenueConduit", "Udaya", "Pitchika"));
    }


    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {

        profile.setId((long) (profiles.size() + 1));
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {

        if (profile.getProfileName().isEmpty()) {
            return null;
        }

        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {

        return profiles.remove(profileName);
    }
}



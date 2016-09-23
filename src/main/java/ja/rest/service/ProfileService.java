package ja.rest.service;

import ja.rest.database.DatabaseInMemory;
import ja.rest.model.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseInMemory.getProfiles();

    public ProfileService() {
        Profile profile1 = new Profile(1L, "najlepszy", "karol", "skupien", new Date());
        Profile profile2 = new Profile(2L, "gorszy", "grzesiu", "sledz", new Date());
        profiles.put(profile1.getProfileName(), profile1);
        profiles.put(profile2.getProfileName(), profile2);
    }

    // GET (retrieve all profiles)
    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles.values());
    }

    // GET (specific profile)
    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    // POST
    public Profile createProfile(Profile profile) {
        long id = profiles.size() + 1;
        profile.setId(id);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    // PUT
    public Profile updateProfile(Profile profile) {
        if(profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    // DELETE
    public void deleteProfile(String profileName) {
        profiles.remove(profileName);
    }

}
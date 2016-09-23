package ja.rest.controller;

import ja.rest.model.Profile;
import ja.rest.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(value = "/profiles")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ProfileResource {

    private static ProfileService service = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return service.getProfiles();
    }

    @GET
    @Path(value = "/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return service.getProfile(profileName);
    }

    @POST
    public Profile createProfile(Profile profile) {
        return service.createProfile(profile);
    }

    @PUT
    @Path(value = "/{profileName}")
    public Profile updateProfile(@PathParam(value = "profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return service.updateProfile(profile);
    }

    @DELETE
    @Path(value = "/{profileName}")
    public void deleteProfile(@PathParam(value = "profileName") String profileName) {
        service.deleteProfile(profileName);
    }

}
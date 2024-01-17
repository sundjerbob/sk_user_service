package raf.sk.sk_user_service.inter_service_comunication;

import java.util.Map;

public class UserPerksDto {

    Integer bookedWorkouts;

    //* free protein shake, or some extra free perk based on client
    Map<String, String> customPerks;

    public UserPerksDto setBookedWorkouts(Integer bookedWorkouts) {
        this.bookedWorkouts = bookedWorkouts;
        return this;
    }

    public Integer getBookedWorkouts() {
        return bookedWorkouts;
    }

    public Map<String, String> getCustomPerks() {
        return customPerks;
    }

    public void setCustomPerks(Map<String, String> customPerks) {
        this.customPerks = customPerks;
    }
}

package raf.sk.sk_user_service.jwt.impl;

import raf.sk.sk_user_service.enumeration.Role;

import java.util.Date;

public class UnpackedAuthToken {

    private String requesterUsername;
    private Role requesterRole;

    private Date sessionStartTime;

    private Date sessionEndTime;


    public String getRequesterUsername() {
        return requesterUsername;
    }

    public Role getRequesterRole() {
        return requesterRole;
    }

    public Date getSessionStart() {
        return sessionStartTime;
    }

    public Date getSessionEnd() {
        return sessionEndTime;
    }

    private UnpackedAuthToken(String requesterUsername, Object requesterRole, Date sessionStartTime, Date sessionEndTime) {
        this.requesterUsername = requesterUsername;
        this.requesterRole = requesterRole instanceof Role ? (Role) requesterRole : Role.valueOf((String) requesterRole);
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;


    }

    public static class Builder {

        private String requesterUsername;

        private Object requesterRole;

        private Date sessionStartTime;

        private Date sessionEndTime;

        public Builder() {

        }

        public UnpackedAuthToken build() {
            return new UnpackedAuthToken(requesterUsername, requesterRole, sessionStartTime, sessionEndTime);
        }

        public Builder setRequesterUsername(String requesterUsername) {
            this.requesterUsername = requesterUsername;
            return this;
        }

        public Builder setRequesterRole(Role requesterRole) {
            this.requesterRole = requesterRole;
            return this;
        }

        public Builder setRequesterRole(String requesterRole) {
            this.requesterRole = requesterRole;
            return this;
        }

        public Builder setSessionStartTime(Date sessionStartTime) {
            this.sessionStartTime = sessionStartTime;
            return this;
        }

        public Builder setSessionEndTime(Date sessionEndTime) {
            this.sessionEndTime = sessionEndTime;
            return this;
        }


    }


}

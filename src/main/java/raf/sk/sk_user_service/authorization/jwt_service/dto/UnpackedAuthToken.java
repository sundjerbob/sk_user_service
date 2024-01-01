package raf.sk.sk_user_service.authorization.jwt_service.dto;

import raf.sk.sk_user_service.entity_model.Role;

import java.util.Date;

public class UnpackedAuthToken {

    private String requesterUsername;
    private Role requesterRole;
    private long requesterId;

    private Date sessionStartTime;

    private Date sessionEndTime;

    private boolean disabled;


    private UnpackedAuthToken(String requesterUsername,
                              Object requesterRole,
                              long requesterId,
                              Date sessionStartTime,
                              Date sessionEndTime,
                              boolean disabled) {
        this.requesterUsername = requesterUsername;
        this.requesterRole = requesterRole instanceof Role ? (Role) requesterRole : Role.valueOf((String) requesterRole);
        this.requesterId = requesterId;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "UnpackedAuthToken{" +
                "requesterUsername='" + requesterUsername + '\'' +
                ", requesterRole=" + requesterRole +
                ", requesterId=" + requesterId +
                ", sessionStartTime=" + sessionStartTime +
                ", sessionEndTime=" + sessionEndTime +
                ", disabled=" + disabled +
                '}';
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public Role getRequesterRole() {
        return requesterRole;
    }

    public long getRequesterId() {
        return requesterId;
    }

    public Date getSessionStart() {
        return sessionStartTime;
    }

    public Date getSessionEnd() {
        return sessionEndTime;
    }


    public boolean isDisabled() {
        return disabled;
    }

    public static class Builder {

        private String requesterUsername;

        private Object requesterRole;

        private long requesterId;
        private Date sessionStartTime;

        private Date sessionEndTime;

        private boolean disabled;


        public Builder() {

        }

        public UnpackedAuthToken build() {
            return new UnpackedAuthToken(requesterUsername, requesterRole, requesterId, sessionStartTime,
                    sessionEndTime, disabled);
        }

        public Builder setRequesterUsername(String requesterUsername) {
            this.requesterUsername = requesterUsername;
            return this;
        }

        public Builder setRequesterRole(Role requesterRole) {
            this.requesterRole = requesterRole;
            return this;
        }

        public Builder setRequesterId(long requesterId) {
            this.requesterId = requesterId;
            return this;
        }

        public Builder setDisabled(boolean disabled) {
            this.disabled = disabled;
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

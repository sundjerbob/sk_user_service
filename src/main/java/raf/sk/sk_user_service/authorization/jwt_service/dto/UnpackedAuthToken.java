package raf.sk.sk_user_service.authorization.jwt_service.dto;

import raf.sk.sk_user_service.authorization.roles.Role;

import java.util.Date;

public class UnpackedAuthToken {

    private String requesterUsername;
    private String requesterEmail;
    private Role requesterRole;
    private long requesterId;

    private Date sessionStartTime;

    private Date sessionEndTime;

    private boolean disabled;


    private UnpackedAuthToken(String requesterUsername,
                              String requesterEmail,
                              Object requesterRole,
                              long requesterId,
                              Date sessionStartTime,
                              Date sessionEndTime,
                              boolean disabled) {
        this.requesterUsername = requesterUsername;
        this.requesterEmail = requesterEmail;
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

    public long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(long requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public Role getRequesterRole() {
        return requesterRole;
    }

    public void setRequesterRole(Role requesterRole) {
        this.requesterRole = requesterRole;
    }

    public Date getSessionStart() {
        return sessionStartTime;
    }

    public void setSessionStartTime(Date sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public Date getSessionEnd() {
        return sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public static class Builder {

        private String requesterUsername;
        private String requesterEmail;

        private Object requesterRole;

        private long requesterId;
        private Date sessionStartTime;

        private Date sessionEndTime;

        private boolean disabled;


        public Builder() {

        }

        public UnpackedAuthToken build() {
            return new UnpackedAuthToken(
                    requesterUsername,
                    requesterEmail,
                    requesterRole,
                    requesterId,
                    sessionStartTime,
                    sessionEndTime,
                    disabled);
        }

        public Builder setRequesterUsername(String requesterUsername) {
            this.requesterUsername = requesterUsername;
            return this;
        }

        public Builder setRequesterEmail(String requesterEmail) {
            this.requesterEmail = requesterEmail;
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

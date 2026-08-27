package com.qalight.demoshop.api.models;

public class AuthResponse {

    private boolean status;
    private boolean showPromoPopup;
    private String flow;
    private User user;

    public boolean isStatus() {
        return status;
    }

    public boolean isShowPromoPopup() {
        return showPromoPopup;
    }

    public String getFlow() {
        return flow;
    }

    public User getUser() {
        return user;
    }

    public static class User {
        private long id;
        private String token;

        public long getId() {
            return id;
        }

        public String getToken() {
            return token;
        }
    }
}

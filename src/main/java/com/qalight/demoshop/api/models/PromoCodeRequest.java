package com.qalight.demoshop.api.models;

public class PromoCodeRequest {

    private final String code;
    private final String source;
    private final boolean autoApply;

    private PromoCodeRequest(Builder builder) {
        this.code = builder.code;
        this.source = builder.source;
        this.autoApply = builder.autoApply;
    }

    public String getCode() {
        return code;
    }

    public String getSource() {
        return source;
    }

    public boolean isAutoApply() {
        return autoApply;
    }

    public String toJson() {
        return String.format(
                "{\"code\":\"%s\",\"source\":\"%s\",\"autoApply\":%s}",
                code, source, autoApply);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private String source = "web";
        private boolean autoApply = false;

        private Builder() {
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder autoApply(boolean autoApply) {
            this.autoApply = autoApply;
            return this;
        }

        public PromoCodeRequest build() {
            if (code == null || code.isBlank()) {
                throw new IllegalStateException("PromoCodeRequest requires a non-blank code");
            }
            return new PromoCodeRequest(this);
        }
    }
}
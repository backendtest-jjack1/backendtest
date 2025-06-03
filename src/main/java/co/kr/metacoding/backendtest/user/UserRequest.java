package co.kr.metacoding.backendtest.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class SaveDTO {
        private String name;

        public User toEntity() {
            return User.builder().name(name).build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String name;
    }

}

package co.kr.metacoding.backendtest.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class SaveDTO {
        private Long id;

        public SaveDTO(User user) {
            this.id = user.getId();
        }
    }

    @Data
    public static class DTO {
        private Long id;
        private String name;

        public DTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }

    @Data
    public static class UpdateDTO {
        private Long id;
        private String name;

        public UpdateDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }

}

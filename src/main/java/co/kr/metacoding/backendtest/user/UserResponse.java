package co.kr.metacoding.backendtest.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class SaveDTO {
        private Integer id;

        public SaveDTO(User user) {
            this.id = user.getId();
        }
    }
}

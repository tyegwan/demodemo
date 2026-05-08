package com.example.demodeom.board;

import com.example.demodeom.user.User;
import lombok.Builder;
import lombok.Data;

// 요텅 데이터를 담는 DTO 클래스
//컨트롤러.. 비즈니스 .. 데이터 계층 사이에서 데이터 전송 역할 객체
public class BoardRequest {

    @Data
    @Builder
    public static class SaveDTO {

        private String title;
        private String content;

        // 편의 기능 설계 가능
        // DTO 에서 Entity로 변환해주는 편의 메서드
         public Board toEntity(User user) {
             return Board.builder()
                     .user(user)
                     .title(title)
                     .content(content)
                     .build();
         }

        public void vaildate() {
            if(title== null || title.trim().isEmpty()) {
                throw  new IllegalArgumentException("값이 없다.");
            }
            if(content == null || content.trim().isEmpty()) {
                throw  new IllegalArgumentException("값이 없다.");
            }
        }

    }

    @Data
    // 내부 정적 클래스 게시글 수정 DTO 설계
    public static class UpdateDTO {

        private String title;
        private String content;



        //게시글 수정시 유효성 검사 편의 메서드
        public void vaildate() {

            if(title== null || title.trim().isEmpty()) {
                throw  new IllegalArgumentException("값이 없다.");
            }
            if(content == null || content.trim().isEmpty()) {
                throw  new IllegalArgumentException("값이 없다.");
            }
        }

    }

}

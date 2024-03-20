package com.sparta.hh2stagram.domain.post.dto;

import com.sparta.hh2stagram.domain.post.entity.Post;
import com.sparta.hh2stagram.domain.post.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponseDto {

    // 게시물 등록
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreatePostResponseDto {

        private Long id;

        private String contents;

        private List<String> imageName;
        private List<String> imageUrlList;
        private List<PostImageResponseDto> postImageList = new ArrayList<>();

        private LocalDateTime createdAt;
//        private String formattedTime;

        public CreatePostResponseDto(Post post, List<String> urlList, List<String> nameList) {


            this.id = post.getId();
            this.contents = post.getContents();
            this.imageName = nameList;
            this.imageUrlList = urlList;
            this.createdAt = post.getCreatedAt();
//            this.formattedTime = formatCreatedAt(post.getCreatedAt());
        }

        // 생성된 시간을 포맷하는 메서드
//        private String formatCreatedAt(LocalDateTime createdAt) {
//            LocalDateTime now = LocalDateTime.now();
//            long hoursPassed = createdAt.until(now, ChronoUnit.HOURS);
//
//            // 시간 간격이 24시간 이하인 경우 시간을 포맷하여 반환
//            if (hoursPassed <= 24) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시간");
//                return createdAt.format(formatter);
//            }
//            // 시간 간격이 24시간 이상인 경우 등록된 년/월/일을 반환
//            else {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//                return createdAt.format(formatter);
//            }
//        }
    }

    // 게시물 수정
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class UpdatePostResponseDto {
        private Long id;

        private String contents;

        private List<String> imageName;
        private List<String> imageUrlList;
        private List<PostImageResponseDto> postImageList = new ArrayList<>();
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public UpdatePostResponseDto(Post post, List<String> urlList, List<String> nameList) {
            this.id = post.getId();

            this.contents = post.getContents();

            this.imageUrlList = urlList;
            this.imageName = nameList;
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }
        public UpdatePostResponseDto(Post post) {
            this.id = post.getId();

            this.contents = post.getContents();

            this.postImageList = post.getPostImageList().stream().map(PostImageResponseDto::new).toList();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }

    }


    // 게시글 전체 조회
    @NoArgsConstructor
    @Getter
    public static class AllPostResponseDto {

        private Long id;
        private String contents;
        private Long userId; // User 엔티티의 id
//        private List<Long> commentIds; // Comment 엔티티의 id 목록
        private List<Long> postImageIds; // PostImage 엔티티의 id 목록

        public AllPostResponseDto(Post post) {
            this.id = post.getId();
            this.contents = post.getContents();
            this.userId = post.getUser().getId(); // User 엔티티의 id 가져오기
//            this.commentIds = post.getCommentList().stream().map(Comment::getId).collect(Collectors.toList()); // Comment 엔티티의 id 목록 가져오기
            this.postImageIds = post.getPostImageList().stream().map(PostImage::getId).collect(Collectors.toList()); // PostImage 엔티티의 id 목록 가져오기
        }

    }

    @NoArgsConstructor
    @Getter
    public static class PostImageResponseDto {
        private Long id;
        private String imageName;
        private String url;

        public PostImageResponseDto(PostImage postImage) {
            this.id = postImage.getId();
            this.url = postImage.getUrl();
            this.imageName = postImage.getImageName();
        }
    }
}

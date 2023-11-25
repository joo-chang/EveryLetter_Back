package com.joo.everyletter_back.post.service;

import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.common.model.entity.Category;
import com.joo.everyletter_back.common.model.entity.Post;
import com.joo.everyletter_back.common.model.entity.User;
import com.joo.everyletter_back.common.model.repository.CategoryRepository;
import com.joo.everyletter_back.common.model.repository.PostRepostiory;
import com.joo.everyletter_back.common.model.repository.UserRepository;
import com.joo.everyletter_back.post.dto.CategoryListResp;
import com.joo.everyletter_back.post.dto.PostWriteReq;
import com.joo.everyletter_back.post.dto.PostWriteResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepostiory postRepostiory;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostWriteResp postWrite(PostWriteReq postWriteReq) {

        Category category = categoryRepository.findById(postWriteReq.getCategoryId()).orElseThrow(() -> ServiceException.CATEGORY_NOT_FOUND);
        User user = userRepository.findById(postWriteReq.getUserId()).orElseThrow(() -> ServiceException.USER_NOT_FOUND);

        Post post = Post.builder()
                .category(category)
                .user(user)
                .title(postWriteReq.getTitle())
                .content(postWriteReq.getContent())
                .build();

        postRepostiory.save(post);

        return PostWriteResp.builder()
                .postId(post.getId())
                .build();
    }

    public CategoryListResp cateList() {
        return new CategoryListResp(categoryRepository.findAll());
    }

//    public void imageUpload(MultipartFile multipartFile) throws IOException {
//        // 2. 서버에 파일 저장 & DB에 파일 정보(fileinfo) 저장
//// - 동일 파일명을 피하기 위해 random값 사용
//        String originalFilename = multipartFile.getOriginalFilename();
//        String saveFileName = createSaveFileName(originalFilename);
//
//// 2-1.서버에 파일 저장
//        multipartFile.transferTo(new File(getFullPath(saveFileName)));
//
//// 2-2. DB에 정보 저장
//        String contentType = multipartFile.getContentType();
//
//        FileInfoRegister fileInfoRegister = FileInfoRegister.builder()
//                .fileName(originalFilename)
//                .saveFileName(saveFileName)
//                .contentType(contentType)
//                .deleteFlag(notDeleted).build();
//
//        int fileInfoId = fileInfoDao.insert(fileInfoRegister);
//    }
//    // 파일 저장 이름 만들기
//// - 사용자들이 올리는 파일 이름이 같을 수 있으므로, 자체적으로 랜덤 이름을 만들어 사용한다
//    private String createSaveFileName(String originalFilename) {
//        String ext = extractExt(originalFilename);
//        String uuid = UUID.randomUUID().toString();
//        return uuid + "." + ext;
//    }
//
//    // 확장자명 구하기
//    private String extractExt(String originalFilename) {
//        int pos = originalFilename.lastIndexOf(".");
//        return originalFilename.substring(pos + 1);
//    }
//
//    // fullPath 만들기
//    private String getFullPath(String filename) {
//        return uploadPath + filename;
//    }

}

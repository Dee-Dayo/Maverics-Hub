package com.mavericksstube.maverickshub.services;

import com.mavericksstube.maverickshub.dtos.requests.UpdateMediaRequest;
import com.mavericksstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.mavericksstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.mavericksstube.maverickshub.dtos.response.UploadMediaResponse;
import com.mavericksstube.maverickshub.models.Media;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mavericksstube.maverickshub.models.Category.ACTION;
import static com.mavericksstube.maverickshub.models.Category.HORROR;
import static com.mavericksstube.maverickshub.utils.TestUtils.TEST_IMAGE_LOCATION;
import static com.mavericksstube.maverickshub.utils.TestUtils.TEST_VIDEO_LOCATION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {

    @Autowired
    private MediaService mediaService;

    private static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("media", inputStream);
        request.setMediaFile(file);
        request.setCategory(ACTION);
        request.setUserId(201L);
        return request;
    }

    @Test
    public void uploadMediaTest(){
        Path path = Paths.get(TEST_IMAGE_LOCATION);

        try(var inputStream = Files.newInputStream(path);){
            UploadMediaRequest uploadMediaRequest = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(uploadMediaRequest);

            log.info("response --> {}", response);
            assertThat(response).isNotNull();
            assertThat(response.getId()).isNotNull();
        } catch (IOException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void uploadVideoTest(){
        Path path = Paths.get(TEST_VIDEO_LOCATION);

        try(var inputStream = Files.newInputStream(path);){
            UploadMediaRequest uploadMediaRequest = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(uploadMediaRequest);

            assertThat(response).isNotNull();
            assertThat(response.getId()).isNotNull();
        } catch (IOException e){
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void getMediaByIdTest(){
        Media media = mediaService.getMediaBy(100L);
        log.info("found content -> {}", media);
        assertThat(media).isNotNull();
    }


    @Test
    public void updateMediaCategoryTest(){
        assertThat(mediaService.getMediaBy(101).getDescription()).contains("media");
        assertThat(mediaService.getMediaBy(101L).getCategory()).isEqualTo(ACTION);

        UpdateMediaRequest updateMediaRequest = new UpdateMediaRequest();
        updateMediaRequest.setId(101L);
        updateMediaRequest.setCategory(HORROR);
        updateMediaRequest.setDescription("THis is a horror movie");
        mediaService.updateMedia(updateMediaRequest);

        Media media = mediaService.getMediaBy(101L);

        assertThat(media.getDescription()).contains("horror");
        assertThat(media.getCategory()).isEqualTo(HORROR);
    }
}

package com.mavericksstube.maverickshub.services;

import com.mavericksstube.maverickshub.dtos.requests.UpdateMediaRequest;
import com.mavericksstube.maverickshub.dtos.requests.UploadMediaRequest;
import com.mavericksstube.maverickshub.dtos.response.UploadMediaResponse;
import com.mavericksstube.maverickshub.dtos.response.UpdateMediaResponse;
import com.mavericksstube.maverickshub.models.Media;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest uploadMediaRequest);

    Media getMediaBy(long id);

    UpdateMediaResponse updateMedia(UpdateMediaRequest updateMediaRequest);
}

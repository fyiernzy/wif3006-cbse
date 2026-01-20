package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.Pageable;

public interface ThreadEntityService {

    ThreadEntityModel createThread(CreateThreadModel createThreadModel);

    Page<ThreadEntityModel> findThreadsByPostId(String postId, Pageable pageable);

    ThreadEntityModel updateThread(UpdateThreadModel updateThreadModel);

    void deleteThreadById(String id);
}

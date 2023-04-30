package cn.tanhua.dubbo.api;

public interface UserLikeApi {

    Boolean saveOrUpdate(Long userId, Long likeUserId,Boolean isLike);

}

package shop.common;

import shop.user.entity.User;
import shop.video.entity.Video;

import java.util.List;

/**
 * Created by zapp on 24/02/15.
 */
public interface DatabaseFacade {

    Object fetch(Object o);

    Object create(Object o);

    Object delete(Object o);

    List<Object> query(Object o);
}

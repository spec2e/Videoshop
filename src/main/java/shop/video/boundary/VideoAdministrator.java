package shop.video.boundary;

import shop.common.DatabaseFacade;
import shop.user.entity.User;
import shop.video.entity.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zapp on 24/02/15.
 */
public class VideoAdministrator {

    DatabaseFacade databaseFacade;

    public Video createVideo(Video video) {
        return (Video) databaseFacade.create(video);
    }

    public List<Video> getVideosForUser(User loggedInUser) {
        List videos = databaseFacade.query(loggedInUser);
        return new ArrayList<Video>(videos);
    }
}

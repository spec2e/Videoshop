package shop.video.boundary;

import shop.payment.boundary.PaymentService;
import shop.user.entity.User;
import shop.video.entity.Video;

import java.util.List;


public class VideoService {

    VideoAdministrator videoAdministrator;
    PaymentService paymentService;

    public Video rent(Video video, User loggedInUser) {
        video.setCurrentUser(loggedInUser);
        boolean paySuccess = paymentService.pay(video.getPrice(), loggedInUser);
        if(paySuccess) {
            video.setPaidAmount(video.getPrice());
        }
        return video;
    }

    public Video createVideo(Video video) {
        if(video != null)
            return videoAdministrator.createVideo(video);
        return null;
    }

    public List<Video> getRentedVideos(User loggedInUser) {
        return videoAdministrator.getVideosForUser(loggedInUser);
    }
}

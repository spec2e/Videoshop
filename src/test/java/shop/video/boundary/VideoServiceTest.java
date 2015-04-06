package shop.video.boundary;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import shop.common.DatabaseFacade;
import shop.payment.boundary.PaymentService;
import shop.user.entity.User;
import shop.video.entity.Video;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(HierarchicalContextRunner.class)
public class VideoServiceTest {

    private VideoService videoService;

    @Before
    public void setUp() throws Exception {
        videoService = new VideoService();
        videoService.videoAdministrator = new VideoAdministrator();
    }

    public class AdministerVideos {

        @Before
        public void setup() {
            videoService.videoAdministrator.databaseFacade = new DatabaseFacade() {

                public Object fetch(Object o) {
                    return null;
                }

                public Object create(Object o) {
                    return o;
                }

                public Object delete(Object o) {
                    return null;
                }

                public List<Object> query(Object o) {
                    return null;
                }
            };
        }

        @Test
        public void whenNoVideoExists_thenNoVideoIsCreated() {
            Video video = videoService.createVideo(null);
            assertNull(video);
        }

        @Test
        public void whenANewVideoHasArrived_thenItMustBeCreatedInTheSystem() {
            Video video = new Video("A video title");
            Video createdVideo = videoService.createVideo(video);
            assertEquals("A video title", createdVideo.getTitle());
        }

    }

    public class RentVideos {

        private User loggedInUser;
        private Video video;
        private boolean paymentServiceWasCalled;

        @Before
        public void setup() {
            loggedInUser = new User("user", "password");
            loggedInUser.setLoggedIn(true);
            video = new Video("A video title");
            video.setPrice(10.0);

            videoService.videoAdministrator.databaseFacade = new DatabaseFacade() {

                public Object fetch(Object o) {
                    return null;
                }

                public Object create(Object o) {
                    return o;
                }

                public Object delete(Object o) {
                    return null;
                }

                public List<Object> query(Object o) {
                    List<Object> videos = new ArrayList<Object>();
                    videos.add(o);
                    return videos;
                }
            };

            videoService.paymentService = new PaymentService() {
                @Override
                public boolean pay(double amount, User user) {
                    paymentServiceWasCalled = true;
                    return true;
                }
            };

        }

        @Test
        public void whenUserWantsToRentAVideo_thenTheUserIsRegisteredOnTheVideosUsersList() {
            assertEquals(loggedInUser.getUsername(), videoService.rent(video, loggedInUser).getCurrentUser().getUsername());
        }

        @Test
        public void whenUserHaveRentedAVideo_thenAllTheVideosRentedByTheUserCanBeFound() {
            assertEquals(1, videoService.getRentedVideos(loggedInUser).size());
        }

        @Test
        public void whenUserRentsAVideo_thenHisPaymentIsRegistered() {
            assertEquals(10.0, videoService.rent(video, loggedInUser).getPaidAmount(), 0.00001);
        }

        @Test
        public void whenUserRentsAVideo_thenThePaymentServiceIsCalled() {
            videoService.rent(video, loggedInUser);
            assertTrue(paymentServiceWasCalled);
        }
    }

}

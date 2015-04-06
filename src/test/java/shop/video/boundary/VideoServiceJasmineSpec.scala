package shop.video.boundary

import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import shop.JasmineSpec
import shop.common.DatabaseFacade
import org.mockito.Mockito._
import org.mockito.Matchers._
import shop.video.entity.Video
import sun.reflect.generics.tree.ReturnType

class VideoServiceJasmineSpec extends JasmineSpec with Matchers with MockitoSugar {

  var aVideoService: VideoService = _

  describe("A video service") {
    beforeEach {
      aVideoService = new VideoService()
      aVideoService.videoAdministrator = new VideoAdministrator()
    }
    describe("Administer videos") {

      val databaseFacadeMock:DatabaseFacade = mock[DatabaseFacade]
      var video: Video = null
      val title = "A video title"

      beforeEach {
        video = new Video(title)
        aVideoService.videoAdministrator.databaseFacade = databaseFacadeMock
        doReturn(video).when(databaseFacadeMock).create(any[Object])
      }

      it("When no video exists, then no video is created") {
        aVideoService.createVideo(null) should be(null)
      }

      it("When a new video is received, then it is created") {
        aVideoService.createVideo(video).getTitle should equal(title)
      }
    }
  }
}

package tech.memoize.sample.boardservice.ctrl

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class PostController{
  val postList : MutableList<Post> = mutableListOf()

  @GetMapping("/posts")
  fun posts() : List<Post> {
    return postList
  }

  @GetMapping("/post/{postId}")
  fun post(@PathVariable("postId") postId: Int) : Post {
    val p = postList.get(postId)
    p.view = p.view + 1
    return p
  }

  @PostMapping("/post")
  fun writePost(@RequestBody postWrite: PostWrite){
    with(postWrite) {
      postList.add(Post(
        postId = postList.size,
          category = category,
          title = title,
          content = contents,
          tag = tag,
          file = "",
          username = username,
          writeday = writeday,
          view = 0
      ))
    }
  }

//  @PostMapping("/post/{postId}")

}

data class PostWrite(
  val category: String,
  val username: String,
  val writeday: LocalDate,
  val title: String,
  val contents: String,
  val tag: String
)


data class Post(
    val postId: Int,
    val category: String,
    val title: String,
    val content: String= "",
    val tag: String = "",
    val file: String = "",
    val username: String,
    @JsonFormat(pattern="yy-MM-dd")
    val writeday: LocalDate,
    var view: Int
)
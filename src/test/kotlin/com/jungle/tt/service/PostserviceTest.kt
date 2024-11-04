//package com.jungle.tt.service
//
//import com.jungle.tt.domain.Post
//import com.jungle.tt.repository.PostRepository
//import com.jungle.tt.dto.PostCreateRequest
//import com.jungle.tt.dto.PostUpdateRequest
//import org.apache.coyote.BadRequestException
//import org.junit.jupiter.api.Assertions.assertEquals
//
//
//
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import org.mockito.ArgumentMatchers.any
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.`when`
//import java.time.LocalDateTime
//import java.util.*
//
//
//class PostserviceTest {
//    private lateinit var postservice: Postservice
//    private lateinit var postRepository: PostRepository
//
//    @BeforeEach
//    fun setup(){
//        postRepository = mock()
//        postservice = Postservice(postRepository)
//    }
//
//    @Test
//    fun `게시글 생성 테스트 성공`() {
//
//        //given
//        val postCreateRequest =
//            PostCreateRequest(
//                title = "test title",
//                content = "test content",
//            )
//
//        val post =
//            Post.of(
//                title = "test title",
//                content = "test content",
//            )
//
//        `when`(postRepository.save(any(Post::class.java))).thenReturn(post)
//
//        //when
//        val result = postservice.insertPost(postCreateRequest)
//
//        //then
//        assertEquals(result.title, postCreateRequest.title)
//        assertEquals(result.content, postCreateRequest.content)
//    }
//
//    @Test
//    fun `게시글 1개조회`(){
//        //given
//        val postId = 1L
//
//        val post =
//            Post(
//            id = 1L,
//            title = "123",
//            content = "내용12123123",
//            createDate = LocalDateTime.now(),
//            createdBy = "test1"
//        )
//
//        `when`(postRepository.findById(any(Long::class.java))).thenReturn(Optional.ofNullable(post))
//
//        //when
////        val result = postservice.getPost(postId)
////
////        //then
////        assertEquals(result.id, post.id)
////        assertEquals(result.title, post.title)
////        assertEquals(result.content, post.content)
////        assertEquals(result.createDate, post.createDate)
////        assertEquals(result.createdBy, post.createdBy)
//
//    }
//
//    @Test
//    fun `게시글 3개조회`(){
//        //given
//        val posts = mutableListOf<Post>()
//
//        for (i in 1..3) {
//            val post = Post(
//                id = i.toLong(),
//                title = "Title $i",
//                content = "내용 $i",
//                createDate = LocalDateTime.now(),
//                createdBy = "test$i"
//            )
//            posts.add(post)
//        }
//
//        `when`(postRepository.findAll()).thenReturn(posts)
//
//        //when
//        val result = postservice.getPosts()
//
//        //then
//        println(result.size.toString() + ", " + posts.size.toString())
//        assertEquals(result.size, posts.size)
//
//    }
//
//    @Test
//    fun `게시글 삭제`(){
//        //given
//        val posts = mutableListOf<Post>()
//        val postId = 1
//        for (i in 1..3) {
//            val post = Post(
//                id = i.toLong(),
//                title = "Title $i",
//                content = "내용 $i",
//                createDate = LocalDateTime.now(),
//                createdBy = "test$i"
//            )
//            posts.add(post)
//        }
//        println(posts)
//        `when`(postRepository.findById(any(Long::class.java))).thenReturn(Optional.ofNullable(posts[postId]))
//        `when`(postRepository.delete(any(Post::class.java))).then{posts.removeAt(postId)}
//
//        //when
////        postservice.deletePost(postId.toLong(),"12345")
//
//        //then
//        println(posts)
//        assertEquals(2, posts.size)
//    }
//
//    @Test
//    fun `게시글 수정`(){
//        //given
//        val posts = mutableListOf<Post>()
//        val postId = 1
//        for (i in 1..3) {
//            val post = Post(
//                id = i.toLong(),
//                title = "Title $i",
//                content = "내용 $i",
//                createDate = LocalDateTime.now(),
//                createdBy = "test$i"
//            )
//            posts.add(post)
//        }
//
//        val postUpdateRequest = PostUpdateRequest(
//            title = "Title 수정",
//            content = "내용 수정",)
//
//        `when`(postRepository.findById(any(Long::class.java))).thenReturn(Optional.ofNullable(posts[postId]))
//
//        //when
////        postservice.updataPost(postId.toLong(), postUpdateRequest)
//
//        // then
//        println(posts[0].title)
//        println(posts[postId].title)
//        assertEquals(posts[postId].title, "Title 수정")
//        assertEquals(posts[postId].content, "내용 수정")
//    }
//
//    @Test
//    fun `게시글 수정 실패`(){
//        //given
//        val posts = mutableListOf<Post>()
//        val postId = 1
//        for (i in 1..3) {
//            val post = Post(
//                id = i.toLong(),
//                title = "Title $i",
//                content = "내용 $i",
//                createDate = LocalDateTime.now(),
//                createdBy = "test$i"
//            )
//            posts.add(post)
//        }
//
//        val postUpdateRequest = PostUpdateRequest(
//            title = "Title 수정",
//            content = "내용 수정",)
//
//        `when`(postRepository.findById(any(Long::class.java))).thenReturn(Optional.ofNullable(posts[postId]))
//
//        // then
////        assertThrows<BadRequestException> {
////            postservice.updataPost(1L, postUpdateRequest)
//        }
//
//    }
//

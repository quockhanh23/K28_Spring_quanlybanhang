package com.example.quanlybanhang.controller;


import com.example.quanlybanhang.dto.CommentDTO;
import com.example.quanlybanhang.models.Comment;
import com.example.quanlybanhang.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get-comment-by-user")
    public ResponseEntity<?> getAllCommentByUser(@RequestParam Long idUser) {
        try {
            List<Comment> list = commentService.findAllCommentByUserId(idUser);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-comment-by-product")
    public ResponseEntity<?> getAllCommentByProduct(@RequestParam Long idProduct) {
        try {
            List<Comment> list = commentService.findAllCommentByProductId(idProduct);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-comment")
    public ResponseEntity<?> getAllCommentByProductAndUser(@RequestParam Long idProduct,
                                                           @RequestParam Long idUer) {
        try {
            List<Comment> list = commentService.findAllCommentByProductIdAndUserId(idProduct, idUer);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        try {
            Comment comment = commentService.validateCommentAndInit(commentDTO);
            commentService.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long idComment) {
        try {
            commentService.delete(idComment);
            return new ResponseEntity<>("Đã xóa bình luận", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

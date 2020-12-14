package edu.fudan.onlinehotelbooking.service;

import edu.fudan.onlinehotelbooking.core.Service;
import edu.fudan.onlinehotelbooking.entity.Comment;

import java.util.List;

public interface CommentService extends Service<Comment> {
    List<Comment> getCommentOfUser(int userID);
    List<Comment> getAllComments();
    int delCommentOfUser(int userID);

    List<Comment> getCommentsOfUser(int userID);
}

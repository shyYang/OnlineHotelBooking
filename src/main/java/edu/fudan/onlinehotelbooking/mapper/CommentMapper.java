package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.Comment;
import edu.fudan.onlinehotelbooking.entity.CommentResponse;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    Comment selectByOrderId(int orderId);

    List<CommentResponse> findCommentsByHotelId(int hotelId);
}
package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.core.AbstractService;
import edu.fudan.onlinehotelbooking.entity.Comment;
import edu.fudan.onlinehotelbooking.mapper.CommentMapper;
import edu.fudan.onlinehotelbooking.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    public List<Comment> getCommentOfUser(int userID)
    {
        Comment comment=new Comment();
        comment.setUser_id(userID);
        return commentMapper.select(comment);
    }

    public List<Comment> getAllComments()
    {
        return commentMapper.selectAll();
    }

    public int delCommentOfUser(int userID)
    {
        Comment comment=new Comment();
        comment.setUser_id(userID);
        return commentMapper.delete(comment);
    }

    @Override
    public List<Comment> getCommentsOfUser(int userID) {
        return commentMapper.selectCommentDetails(userID);
    }
}

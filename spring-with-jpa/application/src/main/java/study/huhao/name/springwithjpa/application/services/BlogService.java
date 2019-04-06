package study.huhao.name.springwithjpa.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.huhao.name.springwithjpa.domain.models.blog.Blog;
import study.huhao.name.springwithjpa.domain.models.blog.BlogRepository;
import study.huhao.name.springwithjpa.domain.models.user.UserId;

@Service
@Transactional
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String title, String body, UserId author) {
        var blog = new Blog(title, body, author);
        blogRepository.save(blog);
        return blog;
    }
}
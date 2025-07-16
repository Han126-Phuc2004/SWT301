package controller;

import dao.PostDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Post;

@WebServlet(name = "CreatePostServlet", urlPatterns = {"/create-post"})
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra đăng nhập và quyền employer
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null || 
            !session.getAttribute("role").equals("Employer")) {
            response.sendRedirect("login");
            return;
        }
        
        request.getRequestDispatcher("create-post.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            // Lấy thông tin từ form
            int accID = (int) session.getAttribute("accID");
            int jcID = Integer.parseInt(request.getParameter("jcID"));
            String title = request.getParameter("title");
            String company = request.getParameter("company");
            String description = request.getParameter("description");
            String requirement = request.getParameter("requirement");
            String location = request.getParameter("location");
            double salaryMin = Double.parseDouble(request.getParameter("salaryMin"));
            String salaryMaxStr = request.getParameter("salaryMax");
            Double salaryMax = salaryMaxStr != null && !salaryMaxStr.isEmpty() ? 
                             Double.parseDouble(salaryMaxStr) : null;
            String salaryCurrency = request.getParameter("salaryCurrency");
            String workingTime = request.getParameter("workingTime");
            String jobType = request.getParameter("jobType");
            
            // Tạo đối tượng Post
            Post post = new Post();
            post.setAccID(accID);
            post.setJcID(jcID);
            post.setTitle(title);
            post.setCompany(company);
            post.setDescription(description);
            post.setRequirement(requirement);
            post.setLocation(location);
            post.setSalaryMin(salaryMin);
            post.setSalaryMax(salaryMax);
            post.setSalaryCurrency(salaryCurrency);
            post.setWorkingTime(workingTime);
            post.setJobType(jobType);
            
            // Lưu vào database
            PostDAO postDAO = new PostDAO();
            boolean success = postDAO.createPost(post);
            
            if (success) {
                session.setAttribute("message", "Đăng bài thành công!");
                response.sendRedirect("posts");
            } else {
                request.setAttribute("error", "Đăng bài thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("create-post.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("create-post.jsp").forward(request, response);
        }
    }
} 
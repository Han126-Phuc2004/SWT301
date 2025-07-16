package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import util.DBUtil;

public class PostDAO {
    
    public boolean createPost(Post post) {
        String sql = "INSERT INTO Posts (accID, jcID, title, company, description, requirement, "
                + "location, salaryMin, salaryMax, salaryCurrency, workingTime, jobType) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, post.getAccID());
            ps.setInt(2, post.getJcID());
            ps.setString(3, post.getTitle());
            ps.setString(4, post.getCompany());
            ps.setString(5, post.getDescription());
            ps.setString(6, post.getRequirement());
            ps.setString(7, post.getLocation());
            ps.setDouble(8, post.getSalaryMin());
            ps.setObject(9, post.getSalaryMax());
            ps.setString(10, post.getSalaryCurrency());
            ps.setString(11, post.getWorkingTime());
            ps.setString(12, post.getJobType());
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        post.setpID(rs.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM Posts ORDER BY createdAt DESC";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Post post = new Post();
                post.setpID(rs.getInt("pID"));
                post.setAccID(rs.getInt("accID"));
                post.setJcID(rs.getInt("jcID"));
                post.setTitle(rs.getString("title"));
                post.setCompany(rs.getString("company"));
                post.setDescription(rs.getString("description"));
                post.setRequirement(rs.getString("requirement"));
                post.setLocation(rs.getString("location"));
                post.setSalaryMin(rs.getDouble("salaryMin"));
                post.setSalaryMax(rs.getDouble("salaryMax"));
                post.setSalaryCurrency(rs.getString("salaryCurrency"));
                post.setWorkingTime(rs.getString("workingTime"));
                post.setJobType(rs.getString("jobType"));
                post.setCreatedAt(rs.getTimestamp("createdAt"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    public Post getPostById(int pID) {
        String sql = "SELECT * FROM Posts WHERE pID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, pID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Post post = new Post();
                    post.setpID(rs.getInt("pID"));
                    post.setAccID(rs.getInt("accID"));
                    post.setJcID(rs.getInt("jcID"));
                    post.setTitle(rs.getString("title"));
                    post.setCompany(rs.getString("company"));
                    post.setDescription(rs.getString("description"));
                    post.setRequirement(rs.getString("requirement"));
                    post.setLocation(rs.getString("location"));
                    post.setSalaryMin(rs.getDouble("salaryMin"));
                    post.setSalaryMax(rs.getDouble("salaryMax"));
                    post.setSalaryCurrency(rs.getString("salaryCurrency"));
                    post.setWorkingTime(rs.getString("workingTime"));
                    post.setJobType(rs.getString("jobType"));
                    post.setCreatedAt(rs.getTimestamp("createdAt"));
                    return post;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updatePost(Post post) {
        String sql = "UPDATE Posts SET jcID = ?, title = ?, company = ?, description = ?, "
                + "requirement = ?, location = ?, salaryMin = ?, salaryMax = ?, "
                + "salaryCurrency = ?, workingTime = ?, jobType = ? WHERE pID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, post.getJcID());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getCompany());
            ps.setString(4, post.getDescription());
            ps.setString(5, post.getRequirement());
            ps.setString(6, post.getLocation());
            ps.setDouble(7, post.getSalaryMin());
            ps.setObject(8, post.getSalaryMax());
            ps.setString(9, post.getSalaryCurrency());
            ps.setString(10, post.getWorkingTime());
            ps.setString(11, post.getJobType());
            ps.setInt(12, post.getpID());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deletePost(int pID) {
        String sql = "DELETE FROM Posts WHERE pID = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, pID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
} 
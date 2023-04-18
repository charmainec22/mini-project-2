package miniproject2.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import miniproject2.server.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    

    List<Category> getAllCategory();
}

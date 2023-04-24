package miniproject2.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import miniproject2.server.model.TableStatus;

public interface TableStatusDao extends JpaRepository<TableStatus, Integer>{

    List<TableStatus> getAllTable();

    @Modifying
    @Transactional
    Integer updateTableStatus(@Param("status") String status, @Param("id") Integer id);
    
}

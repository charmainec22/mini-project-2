package miniproject2.server.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@NamedQuery(name = "TableStatus.getAllTable", query = "select t from TableStatus t order by t.id asc")

@NamedQuery(name= "TableStatus.updateTableStatus", query = "update TableStatus t set t.status=:status where t.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="tableStatus")

public class TableStatus {



    public static final Long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="tableId")
    private String tableId;

    @Column(name="pax")
    private Integer pax;

    @Column(name="status")
    private String status;
}

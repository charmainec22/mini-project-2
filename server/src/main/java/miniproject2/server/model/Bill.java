package miniproject2.server.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

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

@NamedQuery(name="Bill.getAllBills", query = "select b from Bill b order by b.id desc")

@NamedQuery(name="Bill.getBillByUserName", query = "select b from Bill b where b.email=:username order by b.id desc")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="bill")
public class Bill implements Serializable{
    
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "total")
    private Integer total;

    @Column(name = "productdetails", columnDefinition = "json")
    private String productDetails;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name="date")
    private String date;

    // @Column(name="table")
    // private String table;

    // @Column(name="tablestatus")
    // private Boolean tableStatus;

}

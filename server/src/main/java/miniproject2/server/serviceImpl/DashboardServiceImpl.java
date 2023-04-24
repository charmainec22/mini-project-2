package miniproject2.server.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import miniproject2.server.dao.BillDao;
import miniproject2.server.dao.CategoryDao;
import miniproject2.server.dao.ProductDao;
import miniproject2.server.dao.TableStatusDao;
import miniproject2.server.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    BillDao billDao;

    @Autowired
    TableStatusDao tableDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", categoryDao.count());
        map.put("product", productDao.count());
        map.put("bill", billDao.count());
        map.put("table", tableDao.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}

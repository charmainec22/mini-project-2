package miniproject2.server.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import miniproject2.server.rest.DashboardRest;
import miniproject2.server.service.DashboardService;

@RestController
public class DashboardRestImpl implements DashboardRest{

    @Autowired
    DashboardService dashboardService;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboardService.getCount();
    }
    
}

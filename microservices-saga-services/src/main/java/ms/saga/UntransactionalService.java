package ms.saga;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class UntransactionalService {

    private Map<String, Status> idToStatus = new HashMap<>();

    public UntransactionalService() {
        this.idToStatus = new HashMap<>();
    }

    @PostMapping("/{resource}/{id}")
    public void commitResource(@PathVariable Resources resource, @PathVariable String id) {

        Status status = idToStatus.get(id);
        if(status == null) {
            status = new Status();
            idToStatus.put( id, status);
        }

        status.committed(resource);

    }

    @DeleteMapping("/{resource}/{id}")
    public void deleteResource(@PathVariable Resources resource, @PathVariable String id) {

        Status status = idToStatus.get(id);
        if(status == null) {
            status = new Status();
            idToStatus.put( id, status);
        }

        status.rollback(resource);

        if(status.isCompletedlyRolledBack()){
            idToStatus.remove(id);
        }

    }

    @GetMapping("/status")
    public Map<String, Status> status() {
        return idToStatus;
    }

    @GetMapping("/resources")
    public List<Resources> resources() {
        return Arrays.asList( Resources.values() );
    }

    @PostMapping("/reset")
    public void reset() {
        idToStatus.clear();
    }

    public static void main(String[] args) {
        SpringApplication.run(UntransactionalService.class, args);
    }

}

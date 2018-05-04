package ms.saga;

import java.util.HashMap;
import java.util.Map;

class Status {

    private final Map<Resources, Boolean> resourceToStatus;

    Status() {
        resourceToStatus = new HashMap<>();
        for (Resources resource : Resources.values()) {
            resourceToStatus.put(resource, Boolean.FALSE);
        }
    }

    public void committed(Resources resource) {
        if(resourceToStatus.get(resource)) throw new IllegalStateException("You cannot commit " + resource + " more than once.");
        resourceToStatus.put(resource, Boolean.TRUE);
    }

    public boolean isFullyCommitted() {
        return resourceToStatus.values().stream().reduce(true, (a, i) -> a = a && i);
    }

    public boolean isCompletedlyRolledBack() {
        return !resourceToStatus.values().stream().reduce(false, (a, i) -> a = a || i);
    }

    public void rollback(Resources resource) {
        if(!resourceToStatus.get(resource)) throw new IllegalStateException("You cannot rollback " + resource + " more than once.");
        resourceToStatus.put(resource, Boolean.FALSE);
    }


}

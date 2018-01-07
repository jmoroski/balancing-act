package org.ccts.balancingact.dao;

import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.Service;

public interface ServiceDao {
    List<Service> getServices();
    Service addService(Service service);
    Service updateService(Service service);
    void removeService(UUID id);
}

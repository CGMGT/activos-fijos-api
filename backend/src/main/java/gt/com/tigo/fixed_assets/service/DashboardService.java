package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.DashboardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardService.class);

    @Autowired
    private DashboardRepository dashboardRepo;

    public List<Object> getMyPendingRequests7Days(Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequests7Days(%d)", this.getClass().getName(), userId));

        return this.dashboardRepo.getMyPendingRequests7Days(userId);
    }

    public List<Object> getMyPendingRequestsFrom7To21Days(Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequestsFrom7To21Days(%d)", this.getClass().getName(), userId));

        return this.dashboardRepo.getMyPendingRequestsFrom7To21Days(userId);
    }

    public List<Object> getMyPendingRequestsOlderThan21Days(Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequestsOlderThan21Days(%d)", this.getClass().getName(), userId));

        return this.dashboardRepo.getMyPendingRequestsOlderThan21Days(userId);
    }

    public List<Object> getMyTeamPendingRequests7Days(Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequests7Days(%d)", this.getClass().getName(), managerId));

        return this.dashboardRepo.getMyTeamPendingRequests7Days(managerId);
    }

    public List<Object> getMyTeamPendingRequestsFrom7To21Days(Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequestsFrom7To21Days(%d)", this.getClass().getName(), managerId));

        return this.dashboardRepo.getMyTeamPendingRequestsFrom7To21Days(managerId);
    }

    public List<Object> getMyTeamPendingRequestsOlderThan21Days(Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequestsOlderThan21Days(%d)", this.getClass().getName(), managerId));

        return this.dashboardRepo.getMyTeamPendingRequestsOlderThan21Days(managerId);
    }

    public List<Object> getAllPendingRequests7Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequests7Days()", this.getClass().getName()));

        return this.dashboardRepo.getAllPendingRequests7Days();
    }

    public List<Object> getAllPendingRequestsFrom7To21Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequestsFrom7To21Days()", this.getClass().getName()));

        return this.dashboardRepo.getAllPendingRequestsFrom7To21Days();
    }

    public List<Object> getAllPendingRequestsOlderThan21Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequestsOlderThan21Days()", this.getClass().getName()));

        return this.dashboardRepo.getAllPendingRequestsOlderThan21Days();
    }

    public List<Object> getMyConfirmations(Long userId) {
        LOGGER.debug(String.format("@%s::getMyConfirmations(%d)", this.getClass().getName(), userId));

        return this.dashboardRepo.getMyConfirmations(userId);
    }

    public List<Object> getMyTeamConfirmations(Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamConfirmations(%d)", this.getClass().getName(), managerId));

        return this.dashboardRepo.getMyTeamConfirmations(managerId);
    }

    public List<Object> getConfirmationsByBusinessUnit() {
        LOGGER.debug(String.format("@%s::getConfirmationsByBusinessUnit()", this.getClass().getName()));

        return this.dashboardRepo.getConfirmationsByBusinessUnit();
    }

    public List<Object> getConfirmationsByBusinessUnitEmployeesCount() {
        LOGGER.debug(String.format("@%s::getConfirmationsByBusinessUnitEmployeesCount()", this.getClass().getName()));

        return this.dashboardRepo.getConfirmationsByBusinessUnitEmployeesCount();
    }

    public List<Object> getMyTeamUnsubscribedEmployeesFixedAssets(Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamUnsubscribedEmployeesFixedAssets(%d)", this.getClass().getName(), managerId));

        return this.dashboardRepo.getMyTeamUnsubscribedEmployeesFixedAssets(managerId);
    }

    public List<Object> getAllUnsubscribedEmployeesFixedAssets() {
        LOGGER.debug(String.format("@%s::getAllUnsubscribedEmployeesFixedAssets()", this.getClass().getName()));

        return this.dashboardRepo.getAllUnsubscribedEmployeesFixedAssets();
    }

}

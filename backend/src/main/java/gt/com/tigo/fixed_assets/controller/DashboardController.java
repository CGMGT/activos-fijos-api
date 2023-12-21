package gt.com.tigo.fixed_assets.controller;
;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @Authorized
    @GetMapping("/myPendingRequests7Days")
    public ResponseEntity getMyPendingRequests7Days(@RequestParam(required = true) Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequests7Days(%d)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyPendingRequests7Days(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myPendingRequestsFrom7To21Days")
    public ResponseEntity getMyPendingRequestsFrom7To21Days(@RequestParam(required = true) Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequestsFrom7To21Days(%d)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyPendingRequestsFrom7To21Days(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myPendingRequestsOlderThan21Days")
    public ResponseEntity getMyPendingRequestsOlderThan21Days(@RequestParam(required = true) Long userId) {
        LOGGER.debug(String.format("@%s::getMyPendingRequestsOlderThan21Days(%d)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyPendingRequestsOlderThan21Days(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myTeamPendingRequests7Days")
    public ResponseEntity getMyTeamPendingRequests7Days(@RequestParam(required = true) Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequests7Days(%d)", this.getClass().getName(), managerId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyTeamPendingRequests7Days(managerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myTeamPendingRequestsFrom7To21Days")
    public ResponseEntity getMyTeamPendingRequestsFrom7To21Days(@RequestParam(required = true) Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequestsFrom7To21Days(%d)", this.getClass().getName(), managerId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyTeamPendingRequestsFrom7To21Days(managerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myTeamPendingRequestsOlderThan21Days")
    public ResponseEntity getMyTeamPendingRequestsOlderThan21Days(@RequestParam(required = true) Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamPendingRequestsOlderThan21Days(%d)", this.getClass().getName(), managerId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyTeamPendingRequestsOlderThan21Days(managerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/allPendingRequests7Days")
    public ResponseEntity getAllPendingRequests7Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequests7Days()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getAllPendingRequests7Days());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/allPendingRequestsFrom7To21Days")
    public ResponseEntity getAllPendingRequestsFrom7To21Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequestsFrom7To21Days()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getAllPendingRequestsFrom7To21Days());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/allPendingRequestsOlderThan21Days")
    public ResponseEntity getAllPendingRequestsOlderThan21Days() {
        LOGGER.debug(String.format("@%s::getAllPendingRequestsOlderThan21Days()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getAllPendingRequestsOlderThan21Days());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myConfirmations")
    public ResponseEntity getMyConfirmations(@RequestParam(required = true) Long userId) {
        LOGGER.debug(String.format("@%s::getMyConfirmations(%d)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyConfirmations(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myTeamConfirmations")
    public ResponseEntity getMyTeamConfirmations(@RequestParam(required = true) Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamConfirmations(%d)", this.getClass().getName(), managerId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyTeamConfirmations(managerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/confirmationsByBusinessUnit")
    public ResponseEntity getConfirmationsByBusinessUnit() {
        LOGGER.debug(String.format("@%s::getConfirmationsByBusinessUnit()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getConfirmationsByBusinessUnit());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/confirmationsByBusinessUnitEmployeesCount")
    public ResponseEntity getConfirmationsByBusinessUnitEmployeesCount() {
        LOGGER.debug(String.format("@%s::getConfirmationsByBusinessUnitEmployeesCount()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getConfirmationsByBusinessUnitEmployeesCount());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/myTeamUnsubscribedEmployeesFixedAssets")
    public ResponseEntity getMyTeamUnsubscribedEmployeesFixedAssets(@RequestParam(required = true) Long managerId) {
        LOGGER.debug(String.format("@%s::getMyTeamUnsubscribedEmployeesFixedAssets(%d)", this.getClass().getName(), managerId));

        try {
            return ResponseEntity.ok(this.dashboardService.getMyTeamUnsubscribedEmployeesFixedAssets(managerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/allUnsubscribedEmployeesFixedAssets")
    public ResponseEntity getAllUnsubscribedEmployeesFixedAssets() {
        LOGGER.debug(String.format("@%s::getAllUnsubscribedEmployeesFixedAssets()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.dashboardService.getAllUnsubscribedEmployeesFixedAssets());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}

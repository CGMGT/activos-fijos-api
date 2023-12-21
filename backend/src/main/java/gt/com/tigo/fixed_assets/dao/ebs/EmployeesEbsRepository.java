package gt.com.tigo.fixed_assets.dao.ebs;

import gt.com.tigo.fixed_assets.model.ebs.EmpleadosEbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesEbsRepository extends JpaRepository<EmpleadosEbsEntity, Long> {

    @Query(value = "SELECT DISTINCT PERSON_ID personId, EFFECTIVE_START_DATE effectiveStartDate, EFFECTIVE_END_DATE effectiveEndDate, \n" +
            "       PERSON_TYPE_ID personTypeId, LAST_NAME lastName, FIRST_NAME firstName, START_DATE startDate, \n" +
            "       CURRENT_EMP_OR_APL_FLAG currentEmpOrAplFlag, CURRENT_EMPLOYEE_FLAG currentEmployeeFlag, \n" +
            "\t   EMAIL_ADDRESS emailAddress, EMPLOYEE_NUMBER employeeNumber, FULL_NAME fullName, SEX sex, ATTRIBUTE1 attribute1,\n" +
            "\t   ORIGINAL_DATE_OF_HIRE originalDateOfHire, PARTY_ID partyId, GLOBAL_NAME globalName, LOCAL_NAME localName\n" +
            "FROM  BOLINF.XXVW_PER_ALL_PEOPLE_F \t\n" +
            "WHERE EMPLOYEE_NUMBER = ?1", nativeQuery = true)
    EmpleadosEbsEntity findById(long id);

    @Query(value = "SELECT DISTINCT PERSON_ID personId, EFFECTIVE_START_DATE effectiveStartDate, EFFECTIVE_END_DATE effectiveEndDate, \n" +
            "       PERSON_TYPE_ID personTypeId, LAST_NAME lastName, FIRST_NAME firstName, START_DATE startDate, \n" +
            "       CURRENT_EMP_OR_APL_FLAG currentEmpOrAplFlag, CURRENT_EMPLOYEE_FLAG currentEmployeeFlag, \n" +
            "\t   EMAIL_ADDRESS emailAddress, EMPLOYEE_NUMBER employeeNumber, FULL_NAME fullName, SEX sex, ATTRIBUTE1 attribute1,\n" +
            "\t   ORIGINAL_DATE_OF_HIRE originalDateOfHire, PARTY_ID partyId, GLOBAL_NAME globalName, LOCAL_NAME localName\n" +
            "FROM  BOLINF.XXVW_PER_ALL_PEOPLE_F \t\n" +
            "  UNION " +
            " SELECT DISTINCT EMPLOYEE_ID personId, CREATION_DATE effectiveStartDate, CREATION_DATE effectiveEndDate, \n" +
            "       0 personTypeId, LAST_NAME lastName, FIRST_NAME firstName, CREATION_DATE startDate, \n" +
            "       'Y' currentEmpOrAplFlag, 'Y' currentEmployeeFlag, \n" +
            "       EMAIL_ADDRESS emailAddress, EMPLOYEE_NUM employeeNumber, FULL_NAME fullName, 'M' sex, ATTRIBUTE1 attribute1,\n" +
            "       CREATION_DATE originalDateOfHire, 0 partyId, FULL_NAME globalName, FULL_NAME localName\n" +
            " FROM BOLINF.XXVW_HR_EMPLOYEES \n" +
            " WHERE SUBSTR(ATTRIBUTE1,1,3) = 'GT-' \n" +
            "  AND EMPLOYEE_ID NOT IN (SELECT PERSON_ID FROM BOLINF.XXVW_PER_ALL_PEOPLE_F) " +
            "ORDER BY 1" , nativeQuery = true)
    List<EmpleadosEbsEntity> findAll();

    @Query(value = "SELECT DISTINCT PERSON_ID personId, EFFECTIVE_START_DATE effectiveStartDate, EFFECTIVE_END_DATE effectiveEndDate, \n" +
            "       PERSON_TYPE_ID personTypeId, LAST_NAME lastName, FIRST_NAME firstName, START_DATE startDate, \n" +
            "       CURRENT_EMP_OR_APL_FLAG currentEmpOrAplFlag, CURRENT_EMPLOYEE_FLAG currentEmployeeFlag, \n" +
            "\t   EMAIL_ADDRESS emailAddress, EMPLOYEE_NUMBER employeeNumber, FULL_NAME fullName, SEX sex, ATTRIBUTE1 attribute1,\n" +
            "\t   ORIGINAL_DATE_OF_HIRE originalDateOfHire, PARTY_ID partyId, GLOBAL_NAME globalName, LOCAL_NAME localName\n" +
            "FROM  BOLINF.XXVW_PER_ALL_PEOPLE_F \t\n" +
            "ORDER BY PERSON_ID \n" +
            "OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY" ,
            countQuery = "SELECT count(*) FROM BOLINF.XXVW_PER_ALL_PEOPLE_F",
            nativeQuery = true)
    List<EmpleadosEbsEntity> findAllByPage(long offset, long fetchRows);
}

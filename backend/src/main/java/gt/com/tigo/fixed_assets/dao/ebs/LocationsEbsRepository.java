package gt.com.tigo.fixed_assets.dao.ebs;

import gt.com.tigo.fixed_assets.model.ebs.LocationsEbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationsEbsRepository extends JpaRepository<LocationsEbsEntity, Long> {
    @Query(value = "       SELECT locationId, MAX(NOMBRE)NOMBRE, MAX(origSystemReference)origSystemReference, MAX(country)country, MAX(address1)address1, \n" +
            "\t         MAX(address2)address2, MAX(address3)address3, MAX(address4)address4, MAX(city)city, MAX(postalCode)postalCode, MAX(state)state, MAX(province)province, MAX(county)county, \n" +
            "\t         MAX(addressKey)addressKey, faLocationCode\n" +
            "\t    FROM(\n" +
            "            SELECT  LOCATION_ID locationId, NVL(NOMBRE,ADDRESS_KEY || '-' || FA_LOCATION_CODE) nombre, ORIG_SYSTEM_REFERENCE origSystemReference, COUNTRY country, ADDRESS1 address1, ADDRESS2 address2, ADDRESS3 address3, ADDRESS4 address4, CITY city, POSTAL_CODE postalCode, STATE state, PROVINCE province, COUNTY county, \n" +
            "                   ADDRESS_KEY addressKey, FA_LOCATION_CODE faLocationCode, T_LOCATIONID\n" +
            "              FROM( \n" +
            "            SELECT FA.LOCATION_ID, NVL(HL.ADDRESS2, HL.ADDRESS1) || ' ' || HL.STATE || ' ' || HL.COUNTY  NOMBRE, HL.ORIG_SYSTEM_REFERENCE, HL.COUNTRY, HL.ADDRESS1, HL.ADDRESS2, HL.ADDRESS3,  \n" +
            "                     HL.ADDRESS4, HL.CITY, HL.POSTAL_CODE, HL.STATE, HL.PROVINCE, HL.COUNTY, HL.ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE, HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA \n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID  \n" +
            "             UNION  \n" +
            "             SELECT FA.LOCATION_ID, NVL(HL.LOCATION_CODE,DESCRIPTION) NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     HL.COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             ADDRESS_LINE_2 ADDRESS2, ADDRESS_LINE_3 ADDRESS3, NULL ADDRESS4, TOWN_OR_CITY CITY, NULL POSTAL_CODE, REGION_1 STATE, \n" +
            "             REGION_1 PROVINCE, STYLE COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HR_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
            "\t\t\t UNION\n" +
            "\t\t\t SELECT FA.LOCATION_ID, 'ND' NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     NULL COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             NULL ADDRESS2, NULL ADDRESS3, NULL ADDRESS4, NULL CITY, NULL POSTAL_CODE, NULL STATE, \n" +
            "             NULL PROVINCE, NULL COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , NULL T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t WHERE FA.LOCATION_ID NOT IN ( SELECT CSI.FA_LOCATION_ID FROM BOLINF.XXVW_CSI_A_LOCATIONS CSI)\n" +
            "\t\t\t   AND ENABLED_FLAG = 'Y'\n" +
            "\t\t\t \n" +
            "            )T  \n" +
            "\t\t\t)T\n" +
            "\t\t\t WHERE locationId = ?1 " +
            " GROUP BY locationId, faLocationCode " , nativeQuery = true)
    LocationsEbsEntity findById(long id);

    @Query(value = " SELECT LOCATIONID,  NVL(NOMBRE, ' ')NOMBRE, NVL(ORIGSYSTEMREFERENCE,' ')ORIGSYSTEMREFERENCE, NVL(COUNTRY,' ')COUNTRY, NVL(ADDRESS1,'')ADDRESS1, " +
            "               ADDRESS2, ADDRESS3, ADDRESS4, CITY, POSTALCODE, STATE, PROVINCE, COUNTY, ADDRESSKEY, FALOCATIONCODE \n" +
            " FROM ( \n" +
            "      SELECT locationId, MAX(NOMBRE)NOMBRE, MAX(origSystemReference)origSystemReference, MAX(country)country, MAX(address1)address1, \n" +
            "\t         MAX(address2)address2, MAX(address3)address3, MAX(address4)address4, MAX(city)city, MAX(postalCode)postalCode, MAX(state)state, MAX(province)province, MAX(county)county, \n" +
            "\t         MAX(addressKey)addressKey, faLocationCode\n" +
            "\t    FROM(\n" +
            "            SELECT  LOCATION_ID locationId, NVL(NOMBRE,ADDRESS_KEY || '-' || FA_LOCATION_CODE) nombre, ORIG_SYSTEM_REFERENCE origSystemReference, COUNTRY country, ADDRESS1 address1, ADDRESS2 address2, ADDRESS3 address3, ADDRESS4 address4, CITY city, POSTAL_CODE postalCode, STATE state, PROVINCE province, COUNTY county, \n" +
            "                   ADDRESS_KEY addressKey, FA_LOCATION_CODE faLocationCode, T_LOCATIONID\n" +
            "              FROM( \n" +
            "            SELECT FA.LOCATION_ID, NVL(HL.ADDRESS2, HL.ADDRESS1) || ' ' || HL.STATE || ' ' || HL.COUNTY  NOMBRE, HL.ORIG_SYSTEM_REFERENCE, HL.COUNTRY, HL.ADDRESS1, HL.ADDRESS2, HL.ADDRESS3,  \n" +
            "                     HL.ADDRESS4, HL.CITY, HL.POSTAL_CODE, HL.STATE, HL.PROVINCE, HL.COUNTY, HL.ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE, HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA \n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID  \n" +
            "             UNION  \n" +
            "             SELECT FA.LOCATION_ID, NVL(HL.LOCATION_CODE,DESCRIPTION) NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     HL.COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             ADDRESS_LINE_2 ADDRESS2, ADDRESS_LINE_3 ADDRESS3, NULL ADDRESS4, TOWN_OR_CITY CITY, NULL POSTAL_CODE, REGION_1 STATE, \n" +
            "             REGION_1 PROVINCE, STYLE COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HR_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
            "\t\t\t UNION\n" +
            "\t\t\t SELECT FA.LOCATION_ID, 'ND' NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     NULL COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             NULL ADDRESS2, NULL ADDRESS3, NULL ADDRESS4, NULL CITY, NULL POSTAL_CODE, NULL STATE, \n" +
            "             NULL PROVINCE, NULL COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , NULL T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t WHERE FA.LOCATION_ID NOT IN ( SELECT CSI.FA_LOCATION_ID FROM BOLINF.XXVW_CSI_A_LOCATIONS CSI)\n" +
            "\t\t\t   AND ENABLED_FLAG = 'Y'\n" +
            "\t\t\t \n" +
            "            )T  \n" +
            "\t\t\t)T\n" +
            "\t\t\tGROUP BY locationId, faLocationCode " +
            " )T2" +
            " ORDER BY locationId " , nativeQuery = true)
    List<LocationsEbsEntity> findAll();

    @Query(value = "       SELECT locationId, MAX(NOMBRE)NOMBRE, MAX(origSystemReference)origSystemReference, MAX(country)country, MAX(address1)address1, \n" +
            "\t         MAX(address2)address2, MAX(address3)address3, MAX(address4)address4, MAX(city)city, MAX(postalCode)postalCode, MAX(state)state, MAX(province)province, MAX(county)county, \n" +
            "\t         MAX(addressKey)addressKey, faLocationCode\n" +
            "\t    FROM(\n" +
            "            SELECT  LOCATION_ID locationId, NVL(NOMBRE,ADDRESS_KEY || '-' || FA_LOCATION_CODE) nombre, ORIG_SYSTEM_REFERENCE origSystemReference, COUNTRY country, ADDRESS1 address1, ADDRESS2 address2, ADDRESS3 address3, ADDRESS4 address4, CITY city, POSTAL_CODE postalCode, STATE state, PROVINCE province, COUNTY county, \n" +
            "                   ADDRESS_KEY addressKey, FA_LOCATION_CODE faLocationCode, T_LOCATIONID\n" +
            "              FROM( \n" +
            "            SELECT FA.LOCATION_ID, NVL(HL.ADDRESS2, HL.ADDRESS1) || ' ' || HL.STATE || ' ' || HL.COUNTY  NOMBRE, HL.ORIG_SYSTEM_REFERENCE, HL.COUNTRY, HL.ADDRESS1, HL.ADDRESS2, HL.ADDRESS3,  \n" +
            "                     HL.ADDRESS4, HL.CITY, HL.POSTAL_CODE, HL.STATE, HL.PROVINCE, HL.COUNTY, HL.ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE, HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA \n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID  \n" +
            "             UNION  \n" +
            "             SELECT FA.LOCATION_ID, NVL(HL.LOCATION_CODE,DESCRIPTION) NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     HL.COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             ADDRESS_LINE_2 ADDRESS2, ADDRESS_LINE_3 ADDRESS3, NULL ADDRESS4, TOWN_OR_CITY CITY, NULL POSTAL_CODE, REGION_1 STATE, \n" +
            "             REGION_1 PROVINCE, STYLE COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , HL.LOCATION_ID T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
            "              INNER JOIN BOLINF.XXVW_HR_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
            "\t\t\t UNION\n" +
            "\t\t\t SELECT FA.LOCATION_ID, 'ND' NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
            "                     NULL COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
            "             NULL ADDRESS2, NULL ADDRESS3, NULL ADDRESS4, NULL CITY, NULL POSTAL_CODE, NULL STATE, \n" +
            "             NULL PROVINCE, NULL COUNTY, NULL ADDRESS_KEY, \n" +
            "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , NULL T_LOCATIONID \n" +
            "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
            "\t\t\t WHERE FA.LOCATION_ID NOT IN ( SELECT CSI.FA_LOCATION_ID FROM BOLINF.XXVW_CSI_A_LOCATIONS CSI)\n" +
            "\t\t\t   AND ENABLED_FLAG = 'Y'\n" +
            "\t\t\t \n" +
            "            )T  \n" +
            "\t\t\t)T\n" +
            "\t\t\tGROUP BY locationId, faLocationCode " +
            "OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY" ,
            countQuery = "SELECT COUNT(*)\n" +
                    "  FROM(\n" +
                    "       SELECT locationId, MAX(NOMBRE)NOMBRE, MAX(origSystemReference)origSystemReference, MAX(country)country, MAX(address1)address1, \n" +
                    "\t         MAX(address2)address2, MAX(address3)address3, MAX(address4)address4, MAX(city)city, MAX(postalCode)postalCode, MAX(state)state, MAX(province)province, MAX(county)county, \n" +
                    "\t         MAX(addressKey)addressKey, faLocationCode\n" +
                    "\t    FROM(\n" +
                    "            SELECT  LOCATION_ID locationId, NVL(NOMBRE,ADDRESS_KEY || '-' || FA_LOCATION_CODE) nombre, ORIG_SYSTEM_REFERENCE origSystemReference, COUNTRY country, ADDRESS1 address1, ADDRESS2 address2, ADDRESS3 address3, ADDRESS4 address4, CITY city, POSTAL_CODE postalCode, STATE state, PROVINCE province, COUNTY county, \n" +
                    "                   ADDRESS_KEY addressKey, FA_LOCATION_CODE faLocationCode, T_LOCATIONID\n" +
                    "              FROM( \n" +
                    "            SELECT FA.LOCATION_ID, NVL(HL.ADDRESS2, HL.ADDRESS1) || ' ' || HL.STATE || ' ' || HL.COUNTY  NOMBRE, HL.ORIG_SYSTEM_REFERENCE, HL.COUNTRY, HL.ADDRESS1, HL.ADDRESS2, HL.ADDRESS3,  \n" +
                    "                     HL.ADDRESS4, HL.CITY, HL.POSTAL_CODE, HL.STATE, HL.PROVINCE, HL.COUNTY, HL.ADDRESS_KEY, \n" +
                    "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE, HL.LOCATION_ID T_LOCATIONID \n" +
                    "             FROM BOLINF.XXVW_FA_LOCATIONS FA \n" +
                    "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
                    "              INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID  \n" +
                    "             UNION  \n" +
                    "             SELECT FA.LOCATION_ID, NVL(HL.LOCATION_CODE,DESCRIPTION) NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
                    "                     HL.COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
                    "             ADDRESS_LINE_2 ADDRESS2, ADDRESS_LINE_3 ADDRESS3, NULL ADDRESS4, TOWN_OR_CITY CITY, NULL POSTAL_CODE, REGION_1 STATE, \n" +
                    "             REGION_1 PROVINCE, STYLE COUNTY, NULL ADDRESS_KEY, \n" +
                    "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , HL.LOCATION_ID T_LOCATIONID \n" +
                    "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
                    "\t\t\t  INNER JOIN BOLINF.XXVW_CSI_A_LOCATIONS CSI ON FA.LOCATION_ID = CSI.FA_LOCATION_ID\n" +
                    "              INNER JOIN BOLINF.XXVW_HR_LOCATIONS HL  ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
                    "\t\t\t UNION\n" +
                    "\t\t\t SELECT FA.LOCATION_ID, 'ND' NOMBRE, ' ' ORIG_SYSTEM_REFERENCE,  \n" +
                    "                     NULL COUNTRY, SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 ADDRESS1, \n" +
                    "             NULL ADDRESS2, NULL ADDRESS3, NULL ADDRESS4, NULL CITY, NULL POSTAL_CODE, NULL STATE, \n" +
                    "             NULL PROVINCE, NULL COUNTY, NULL ADDRESS_KEY, \n" +
                    "             SEGMENT1||'-'||SEGMENT2||'-'||SEGMENT3||'-'||SEGMENT4||'-'||SEGMENT5||'-'||SEGMENT6 FA_LOCATION_CODE , NULL T_LOCATIONID \n" +
                    "             FROM BOLINF.XXVW_FA_LOCATIONS FA\n" +
                    "\t\t\t WHERE FA.LOCATION_ID NOT IN ( SELECT CSI.FA_LOCATION_ID FROM BOLINF.XXVW_CSI_A_LOCATIONS CSI)\n" +
                    "\t\t\t   AND ENABLED_FLAG = 'Y'\n" +
                    "\t\t\t \n" +
                    "            )T  \n" +
                    "\t\t\t)T\n" +
                    "\t\t\tGROUP BY locationId, faLocationCode" +
                    ")T",
            nativeQuery = true)
    List<LocationsEbsEntity> findAllByPage(long offset, long fetchRows);

}

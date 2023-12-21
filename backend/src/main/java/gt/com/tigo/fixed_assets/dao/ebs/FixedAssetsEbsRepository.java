package gt.com.tigo.fixed_assets.dao.ebs;

import gt.com.tigo.fixed_assets.model.ebs.FixedAssetsEbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FixedAssetsEbsRepository extends JpaRepository<FixedAssetsEbsEntity, Long> {


    @Query(value = "SELECT DISTINCT\n" +
            "      ad.asset_number assetNumber,\n" +
            "      ad.description,\n" +
            "      ad.tag_number tagNumber,\n" +
            "      AD.ATTRIBUTE1 legacycode,\n" +
            "      ad.MODEL_NUMBER modelNumber,\n" +
            "      ad.PARENT_ASSET_ID parentAssetId,\n" +
            "      ad.ASSET_KEY_CCID assetKeyCcid,\n" +
            "      ad.serial_number serialNumber,\n" +
            "      ad.asset_type assetType,\n" +
            "      AD.INVENTORIAL inventorial,\n" +
            "      ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
            "      bk.book_type_code bookTypeCode,\n" +
            "      bk.date_placed_in_service dateIn,\n" +
            "      round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
            "      (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
            "      (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
            "      cb.asset_cost_acct assetAccount,\n" +
            "      P.EMPLOYEE_NUMBER employeeNumber,\n" +
            "      P.FIRST_NAME || ' ' || P.LAST_NAME employeeName,\n" +
            "\t  csi.location_id locationId\n" +
            "FROM  \n" +
            "      BOLINF.XXVW_FA_ADDITIONS AD\n" +
            "      Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
            "      Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
            "      Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
            "      Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
            "      Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
            "\t  INNER join BOLINF.XXVW_CSI_A_LOCATIONS csi on loc.LOCATION_ID = csi.FA_LOCATION_ID\n" +
            "\t  INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
            "WHERE\n" +
            "      BK.book_type_code in ('GT_COMCEL_IFRS','GT_SICESA_IFRS','GT_NAVEGA_IFRS','GT_C2NUBE_IFRS', 'GT_NEWBER_IFRS') and\n" +
            "      BC.book_class = 'CORPORATE' and\n" +
            "      BK.period_counter_fully_retired is null and\n" +
            "      BK.date_ineffective is null and\n" +
            "      DH.date_ineffective is null and\n" +
            "      CA.SEGMENT1 || '-' || CA.SEGMENT2 in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')" +
            "WHERE ASSET_NUMBER = ?1", nativeQuery = true)
    FixedAssetsEbsEntity findById(long id);

    @Query(value = "SELECT DISTINCT\n" +
            "      ad.asset_number assetNumber,\n" +
            "      ad.description,\n" +
            "      ad.tag_number tagNumber,\n" +
            "      AD.ATTRIBUTE1 legacycode,\n" +
            "      ad.MODEL_NUMBER modelNumber,\n" +
            "      ad.PARENT_ASSET_ID parentAssetId,\n" +
            "      ad.ASSET_KEY_CCID assetKeyCcid,\n" +
            "      ad.serial_number serialNumber,\n" +
            "      ad.asset_type assetType,\n" +
            "      AD.INVENTORIAL inventorial,\n" +
            "      ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
            "      bk.book_type_code bookTypeCode,\n" +
            "      bk.date_placed_in_service dateIn,\n" +
            "      round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
            "      (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
            "      (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
            "      cb.asset_cost_acct assetAccount,\n" +
            "      P.EMPLOYEE_NUMBER employeeNumber,\n" +
            "      P.FIRST_NAME || ' ' || P.LAST_NAME employeeName,\n" +
            "\t  csi.location_id locationId\n" +
            "FROM  \n" +
            "      BOLINF.XXVW_FA_ADDITIONS AD\n" +
            "      Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
            "      Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
            "      Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
            "      Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
            "      Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
            "      Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
            "\t  INNER join BOLINF.XXVW_CSI_A_LOCATIONS csi on loc.LOCATION_ID = csi.FA_LOCATION_ID\n" +
            "\t  INNER JOIN BOLINF.XXVW_HZ_LOCATIONS HL ON CSI.LOCATION_ID = HL.LOCATION_ID\n" +
            "WHERE\n" +
            "      BK.book_type_code in ('GT_COMCEL_IFRS','GT_SICESA_IFRS','GT_NAVEGA_IFRS','GT_C2NUBE_IFRS', 'GT_NEWBER_IFRS') and\n" +
            "      BC.book_class = 'CORPORATE' and\n" +
            "      BK.period_counter_fully_retired is null and\n" +
            "      BK.date_ineffective is null and\n" +
            "      DH.date_ineffective is null and\n" +
            "      CA.SEGMENT1 || '-' || CA.SEGMENT2 in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000') " +
            "ORDER BY ASSET_NUMBER" , nativeQuery = true)
    List<FixedAssetsEbsEntity> findAll();

    @Query(value = "\t\t\tSELECT DISTINCT\n" +
            "                          ad.asset_number assetNumber,\n" +
            "                          ad.description,\n" +
            "                          ad.tag_number tagNumber,\n" +
            "                          AD.ATTRIBUTE1 legacycode,\n" +
            "                          ad.MODEL_NUMBER modelNumber,\n" +
            "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
            "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
            "                          ad.serial_number serialNumber,\n" +
            "                          ad.asset_type assetType,\n" +
            "                          AD.INVENTORIAL inventorial,\n" +
            "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
            "                          bk.book_type_code bookTypeCode,\n" +
            "                          bk.date_placed_in_service dateIn,\n" +
            "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
            "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
            "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
            "                          cb.asset_cost_acct assetAccount,\n" +
            "                          P.EMPLOYEE_NUMBER employeeNumber,\n" +
            "                          P.FIRST_NAME || ' ' || P.LAST_NAME employeeName,\n" +
            "                      loc.location_id locationId\n" +
            "                    FROM  \n" +
            "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
            "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
            "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
            "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
            "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
            "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
            "                    WHERE\n" +
            "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' AND\n" +
            "                          BC.book_class = 'CORPORATE' and\n" +
            "                          BK.period_counter_fully_retired is null and\n" +
            "                          BK.date_ineffective is null and\n" +
            "                          DH.date_ineffective is null and\n" +
            "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
            "\t\t\t\t\tUNION\n" +
            "\t\t\t\t\tSELECT DISTINCT\n" +
            "                          ad.asset_number assetNumber,\n" +
            "                          ad.description,\n" +
            "                          ad.tag_number tagNumber,\n" +
            "                          AD.ATTRIBUTE1 legacycode,\n" +
            "                          ad.MODEL_NUMBER modelNumber,\n" +
            "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
            "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
            "                          ad.serial_number serialNumber,\n" +
            "                          ad.asset_type assetType,\n" +
            "                          AD.INVENTORIAL inventorial,\n" +
            "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
            "                          bk.book_type_code bookTypeCode,\n" +
            "                          bk.date_placed_in_service dateIn,\n" +
            "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
            "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
            "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
            "                          cb.asset_cost_acct assetAccount,\n" +
            "                          NVL(P.EMPLOYEE_NUMBER,HP.EMPLOYEE_NUM) employeeNumber,\n" +
            "                          NVL(P.FIRST_NAME, HP.FIRST_NAME) || ' ' || NVL(P.LAST_NAME, HP.LAST_NAME) employeeName,\n" +
            "                      loc.location_id locationId\n" +
            "                    FROM  \n" +
            "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
            "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
            "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
            "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
            "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
            "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
            "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
            "                          LEFT JOIN BOLINF.XXVW_HR_EMPLOYEES HP On (HP.EMPLOYEE_ID = DH.assigned_to) " +
            "                    WHERE\n" +
            "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' and\n" +
            "                          BC.book_class = 'CORPORATE' and\n" +
            "                          BK.period_counter_fully_retired is null and\n" +
            "                          BK.date_ineffective is null and\n" +
            "                          DH.date_ineffective is null and\n" +
            "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 NOT in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
            "\t\t\t\t\t      AND NVL(P.EMPLOYEE_NUMBER, HP.EMPLOYEE_ID) IS NOT NULL\n" +
            "OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY" ,
            countQuery = "            SELECT COUNT(*) CANTIDAD\n" +
                    "            FROM (\n" +
                    "            SELECT DISTINCT\n" +
                    "                          ad.asset_number assetNumber,\n" +
                    "                          ad.description,\n" +
                    "                          ad.tag_number tagNumber,\n" +
                    "                          AD.ATTRIBUTE1 legacycode,\n" +
                    "                          ad.MODEL_NUMBER modelNumber,\n" +
                    "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
                    "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
                    "                          ad.serial_number serialNumber,\n" +
                    "                          ad.asset_type assetType,\n" +
                    "                          AD.INVENTORIAL inventorial,\n" +
                    "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
                    "                          bk.book_type_code bookTypeCode,\n" +
                    "                          bk.date_placed_in_service dateIn,\n" +
                    "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
                    "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
                    "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
                    "                          cb.asset_cost_acct assetAccount,\n" +
                    "                          P.EMPLOYEE_NUMBER employeeNumber,\n" +
                    "                          P.FIRST_NAME || ' ' || P.LAST_NAME employeeName,\n" +
                    "                      loc.location_id locationId\n" +
                    "                    FROM  \n" +
                    "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
                    "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
                    "                    WHERE\n" +
                    "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' AND\n" +
                    "                          BC.book_class = 'CORPORATE' and\n" +
                    "                          BK.period_counter_fully_retired is null and\n" +
                    "                          BK.date_ineffective is null and\n" +
                    "                          DH.date_ineffective is null and\n" +
                    "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
                    "                    UNION\n" +
                    "                    SELECT DISTINCT\n" +
                    "                          ad.asset_number assetNumber,\n" +
                    "                          ad.description,\n" +
                    "                          ad.tag_number tagNumber,\n" +
                    "                          AD.ATTRIBUTE1 legacycode,\n" +
                    "                          ad.MODEL_NUMBER modelNumber,\n" +
                    "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
                    "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
                    "                          ad.serial_number serialNumber,\n" +
                    "                          ad.asset_type assetType,\n" +
                    "                          AD.INVENTORIAL inventorial,\n" +
                    "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
                    "                          bk.book_type_code bookTypeCode,\n" +
                    "                          bk.date_placed_in_service dateIn,\n" +
                    "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
                    "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
                    "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
                    "                          cb.asset_cost_acct assetAccount,\n" +
                    "                          NVL(P.EMPLOYEE_NUMBER,HP.EMPLOYEE_NUM) employeeNumber,\n" +
                    "                          NVL(P.FIRST_NAME, HP.FIRST_NAME) || ' ' || NVL(P.LAST_NAME, HP.LAST_NAME) employeeName,\n" +
                    "                      loc.location_id locationId\n" +
                    "                    FROM  \n" +
                    "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
                    "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
                    "                          LEFT JOIN BOLINF.XXVW_HR_EMPLOYEES HP On (HP.EMPLOYEE_ID = DH.assigned_to) " +
                    "                    WHERE\n" +
                    "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' and\n" +
                    "                          BC.book_class = 'CORPORATE' and\n" +
                    "                          BK.period_counter_fully_retired is null and\n" +
                    "                          BK.date_ineffective is null and\n" +
                    "                          DH.date_ineffective is null and\n" +
                    "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 NOT in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
                    "                          AND NVL(P.EMPLOYEE_NUMBER, HP.EMPLOYEE_ID) IS NOT NULL\n" +
                    "                          )t",
            nativeQuery = true)
    List<FixedAssetsEbsEntity> findAllByPage(long offset, long fetchRows);

    @Query(
            value = "            SELECT COUNT(*) CANTIDAD\n" +
                    "            FROM (\n" +
                    "            SELECT DISTINCT\n" +
                    "                          ad.asset_number assetNumber,\n" +
                    "                          ad.description,\n" +
                    "                          ad.tag_number tagNumber,\n" +
                    "                          AD.ATTRIBUTE1 legacycode,\n" +
                    "                          ad.MODEL_NUMBER modelNumber,\n" +
                    "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
                    "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
                    "                          ad.serial_number serialNumber,\n" +
                    "                          ad.asset_type assetType,\n" +
                    "                          AD.INVENTORIAL inventorial,\n" +
                    "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
                    "                          bk.book_type_code bookTypeCode,\n" +
                    "                          bk.date_placed_in_service dateIn,\n" +
                    "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
                    "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
                    "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
                    "                          cb.asset_cost_acct assetAccount,\n" +
                    "                          P.EMPLOYEE_NUMBER employeeNumber,\n" +
                    "                          P.FIRST_NAME || ' ' || P.LAST_NAME employeeName,\n" +
                    "                      loc.location_id locationId\n" +
                    "                    FROM  \n" +
                    "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
                    "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
                    "                    WHERE\n" +
                    "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' AND\n" +
                    "                          BC.book_class = 'CORPORATE' and\n" +
                    "                          BK.period_counter_fully_retired is null and\n" +
                    "                          BK.date_ineffective is null and\n" +
                    "                          DH.date_ineffective is null and\n" +
                    "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
                    "                    UNION\n" +
                    "                    SELECT DISTINCT\n" +
                    "                          ad.asset_number assetNumber,\n" +
                    "                          ad.description,\n" +
                    "                          ad.tag_number tagNumber,\n" +
                    "                          AD.ATTRIBUTE1 legacycode,\n" +
                    "                          ad.MODEL_NUMBER modelNumber,\n" +
                    "                          ad.PARENT_ASSET_ID parentAssetId,\n" +
                    "                          ad.ASSET_KEY_CCID assetKeyCcid,\n" +
                    "                          ad.serial_number serialNumber,\n" +
                    "                          ad.asset_type assetType,\n" +
                    "                          AD.INVENTORIAL inventorial,\n" +
                    "                          ca.segment1 || '-' || ca.segment2 categoryCode, \n" +
                    "                          bk.book_type_code bookTypeCode,\n" +
                    "                          bk.date_placed_in_service dateIn,\n" +
                    "                          round (bk.cost * dh.units_assigned / ad.current_units, 2) cost,\n" +
                    "                          (loc.segment1 || '-' || loc.segment2 || '-' || loc.segment3 || '-' || loc.segment4 || '-' || loc.segment5 || '-' || loc.segment6) AS  combination,\n" +
                    "                          (expense_cc.segment1 || '-' || expense_cc.segment2 || '-' || expense_cc.segment3 || '-' || expense_cc.segment4 || '-' || expense_cc.segment5 || '-' || expense_cc.segment6 || '-' || expense_cc.segment7 || '-' || expense_cc.segment8 || '-' || expense_cc.segment9 || '-' || expense_cc.segment10 || '-' || expense_cc.segment11 || '-' || expense_cc.segment12 || '-' || expense_cc.segment13) AS  expenseAccount,\n" +
                    "                          cb.asset_cost_acct assetAccount,\n" +
                    "                          NVL(P.EMPLOYEE_NUMBER,HP.EMPLOYEE_NUM) employeeNumber,\n" +
                    "                          NVL(P.FIRST_NAME, HP.FIRST_NAME) || ' ' || NVL(P.LAST_NAME, HP.LAST_NAME) employeeName,\n" +
                    "                      loc.location_id locationId\n" +
                    "                    FROM  \n" +
                    "                          BOLINF.XXVW_FA_ADDITIONS AD\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOKS BK On (AD.asset_id = BK.asset_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_DISTRIBUTION_HISTORY DH On (AD.asset_id = DH.asset_id and DH.book_type_code = BK.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_BOOK_CONTROLS BC On (BK.book_type_code = BC.book_type_code)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORIES_B CA On (CA.category_id = AD.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_CATEGORY_BOOKS CB On (cb.book_type_code = bk.book_type_code and cb.category_id = ad.asset_category_id)\n" +
                    "                          Inner Join BOLINF.XXVW_GL_CODE_COMBINATIONS EXPENSE_CC On (EXPENSE_CC.code_combination_id = DH.code_combination_id)\n" +
                    "                          Inner Join BOLINF.XXVW_FA_LOCATIONS LOC On (LOC.location_id = DH.location_id)\n" +
                    "                          Left Join  BOLINF.XXVW_PER_ALL_PEOPLE_F P On (P.person_id = DH.assigned_to)\n" +
                    "                          LEFT JOIN BOLINF.XXVW_HR_EMPLOYEES HP On (HP.EMPLOYEE_ID = DH.assigned_to)" +
                    "                    WHERE\n" +
                    "                          SUBSTR(Bc.book_type_code,1,2) = 'GT' and\n" +
                    "                          BC.book_class = 'CORPORATE' and\n" +
                    "                          BK.period_counter_fully_retired is null and\n" +
                    "                          BK.date_ineffective is null and\n" +
                    "                          DH.date_ineffective is null and\n" +
                    "                          CA.SEGMENT1 || '-' || CA.SEGMENT2 NOT in ('25-1000','27-1100','41-1100','43-2016','43-2100','56-1000')\n" +
                    "                          AND NVL(P.EMPLOYEE_NUMBER, HP.EMPLOYEE_ID) IS NOT NULL\n" +
                    "                          )t",
            nativeQuery = true
    )
    long getTotalRows();




}

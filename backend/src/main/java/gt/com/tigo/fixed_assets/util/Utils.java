package gt.com.tigo.fixed_assets.util;

import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.model.portal.CpEmpleadosEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private Utils() {
        // do nothing
    }

    /**
     * Builds a default predicate equivalent to 1 == 1.
     * @return A default predicate.
     */
    public static Specification<CpEmpleadosEntity> getDefaultSpecification() {
        return new Specification<CpEmpleadosEntity>() {
            @Override
            public Predicate toPredicate(Root<CpEmpleadosEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.conjunction();
            }
        };
    }

    /**
     * Builds a predicate using the LIKE operator.
     * @param fieldName The field name to use in filter.
     * @param filterValue The value to filter.
     * @param operator The operator to use. Can use LIKE, STARTSWITH, ENDSWITH
     * @param <T> The type of the built specification.
     * @return A predicate comparing two values using the LIKE operator.
     */
    public static <T> Specification<T> getLikeSpecification(final String fieldName, final String filterValue, final String operator) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                final Predicate predicate = filterValue == null ?
                            builder.conjunction() :
                            builder.like(builder.upper(root.<String>get(fieldName)), String.format(getOperator(operator), filterValue.toUpperCase()));

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate using the LIKE operator.
     * @param fieldName The field name to use in filter.
     * @param filterValue The value to filter.
     * @param <T> The type of the built specification.
     * @return A predicate comparing two values using the LIKE operator.
     */
    public static <T> Specification<T> getLikeSpecification(final String fieldName, final String filterValue) {
        return getLikeSpecification(fieldName, filterValue, "LIKE");
    }

    /**
     * Returns comparation operator from name.
     * @param operator The name of the operator.
     * @return The value of the operator.
     */
    private static String getOperator(String operator) {
        if (operator != null && operator.equalsIgnoreCase("STARTSWITH")) {
            return "%%%s";
        } else if (operator != null && operator.equalsIgnoreCase("ENDSWITH")) {
            return "%s%%";
        }

        return "%%%s%%";
    }

    /**
     * Builds a predicate using the BETWEEN operator. It works for dates.
     * @param fieldName The field name to use in filter.
     * @param filterValue The value to filter.
     * @param <T> The type of the built specification.
     * @return A predicate comparing two values using the BETWEEN operator.
     */
    public static <T> Specification<T> getBetweenSpecification(final String fieldName, final Date startDate, final Date endDate) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                final Predicate predicate =  startDate == null || endDate == null ?
                        builder.conjunction() :
                        builder.between(root.<Date>get(fieldName), startDate, endDate);

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate using the EQUAL operator. It works for strings.
     * @param fieldName The field name to use in filter.
     * @param filterValue The value to filter.
     * @param <T> The type of the built specification.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T> Specification<T> getEqualSpecification(final String fieldName, final String filterValue) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                final Predicate predicate =  filterValue == null ?
                        builder.conjunction() :
                        builder.equal(builder.upper(root.<String>get(fieldName)), filterValue.toUpperCase());

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate using the EQUAL operator. It works with long values.
     * @param fieldName The field name to use in filter.
     * @param filterValue The value to filter.
     * @param <T> The type of the built specification.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T> Specification<T> getEqualSpecification(final String fieldName, final Long filterValue) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                final Predicate predicate =  filterValue == null ?
                        builder.conjunction() :
                        builder.equal(root.<Long>get(fieldName), filterValue);

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the LIKE operator.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param operator
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the LIKE operator.
     */
    public static <T, U> Specification<T> getLikeChildSpecification(final String parentFieldName, final String childFieldName, final String filterValue, final String operator) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Join<T, U> join = getJoin(root, parentFieldName);

                final Predicate predicate =  filterValue == null ?
                        builder.conjunction() :
                        builder.like(builder.upper(join.<String>get(childFieldName)), String.format(getOperator(operator), filterValue.toUpperCase()));

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the LIKE operator.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the LIKE operator.
     */
    public static <T, U> Specification<T> getLikeChildSpecification(final String parentFieldName, final String childFieldName, final String filterValue) {
        return Utils.<T, U>getLikeChildSpecification(parentFieldName, childFieldName, filterValue, "LIKE");
    }

    public static <T, U> Join<T, U> getJoin(Root<T> root, String joinFieldName, JoinType joinType) {
        Iterator<Join<T, ?>> joins = root.getJoins().iterator();

        while (joins.hasNext()) {
            Join<T, U> tempJoin = (Join<T, U>) joins.next();

            if (tempJoin.getAttribute().getName().equals(joinFieldName)) {
                return tempJoin;
            }
        }

        return root.join(joinFieldName, joinType);
    }

    private static <T, U> Join<T, U> getJoin(Root<T> root, String joinFieldName) {
        return getJoin(root, joinFieldName, JoinType.INNER);
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the EQUAL operator and works for strings.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T, U> Specification<T> getEqualChildSpecification(final String parentFieldName, final String childFieldName, final String filterValue, final JoinType joinType) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Join<T, U> join = getJoin(root, parentFieldName, joinType);

                final Predicate predicate =  filterValue == null ?
                        builder.conjunction() :
                        builder.equal(builder.upper(join.<String>get(childFieldName)), filterValue.toUpperCase());

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the EQUAL operator and works for strings.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T, U> Specification<T> getEqualChildSpecification(final String parentFieldName, final String childFieldName, final String filterValue) {
        return Utils.<T,U>getEqualChildSpecification(parentFieldName, childFieldName, filterValue, JoinType.INNER);
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the EQUAL operator and works for long values.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T, U> Specification<T> getEqualChildSpecification(final String parentFieldName, final String childFieldName, final Long filterValue, final JoinType joinType) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Join<T, U> join = getJoin(root, parentFieldName, joinType);

                final Predicate predicate =  filterValue == null ?
                        builder.conjunction() :
                        builder.equal(join.get(childFieldName), filterValue);

                query.distinct(true);

                return predicate;
            }
        };
    }

    /**
     * Builds a predicate by joining T and U tables. It uses the EQUAL operator and works for long values.
     * @param parentFieldName
     * @param childFieldName
     * @param filterValue
     * @param <T> The type of the parent table.
     * @param <U> The type of the child table.
     * @return A predicate comparing two values using the EQUAL operator.
     */
    public static <T, U> Specification<T> getEqualChildSpecification(final String parentFieldName, final String childFieldName, final Long filterValue) {
        return Utils.<T, U>getEqualChildSpecification(parentFieldName, childFieldName, filterValue, JoinType.INNER);
    }

    /**
     * Gets a value from a map.
     * @param map The map.
     * @param key The key to search for.
     * @return The string value.
     */
    public static String getValueAsString(Map<String, FilterInfoDto> map, String key) {
        return (map.containsKey(key) && map.get(key) != null) ? map.get(key).getValue() : null;
    }

    /**
     * Gets a value from a map.
     * @param map The map.
     * @param key The key to search for.
     * @return The string value.
     */
    public static Long getValueAsLong(Map<String, FilterInfoDto> map, String key) {
        return (map.containsKey(key) && map.get(key) != null) ? Long.parseLong(map.get(key).getValue()) : null;
    }

    /**
     * Gets a value from a map.
     * @param map The map.
     * @param key The key to search for.
     * @return The string value.
     */
    public static Date getValueAsDate(Map<String, FilterInfoDto> map, String key) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        try {
            return (map.containsKey(key) && map.get(key) != null) ? sdf.parse(map.get(key).getValue()) : null;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Gets a operator from a map.
     * @param map The map.
     * @param key The key to search for.
     * @return The operator name.
     */
    public static String getOperator(Map<String, FilterInfoDto> map, String key) {
        return (map.containsKey(key) && map.get(key) != null) ? map.get(key).getOperator() : null;
    }

    /**
     * Converts a list of filters to a map.
     * @param filters The filter.
     * @return a Map with the name of the filter column as key.
     */
    public static Map<String, FilterInfoDto> getFiltersAsMap(List<FilterInfoDto> filters) {
        Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

        // get column names to filter
        if (filters != null) {
            for (FilterInfoDto filterInfo : filters) {
                filtersInfo.put(filterInfo.getId(), filterInfo);
            }
        }

        return filtersInfo;
    }

    /**
     * Removes a filter entry from a list of filters.
     * @param key The key to remove.
     * @return an updated list without the removed filter.
     */
    public static boolean removeFilter(List<FilterInfoDto> filters, String filterIdToRemove) {
        FilterInfoDto filterToRemove = null;

        for (FilterInfoDto filter : filters) {
            if (filter.getId().equals(filterIdToRemove)) {
                filterToRemove = filter;
                break;
            }
        }

        if (filterToRemove != null) {
            filters.remove(filterToRemove);

            return true;
        }

        return false;
    }

    public static <T> Specification<T> getConjunction() {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.conjunction();
            }
        };
    }

}

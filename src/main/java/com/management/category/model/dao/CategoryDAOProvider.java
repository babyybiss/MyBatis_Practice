package com.management.category.model.dao;

import com.management.category.model.dto.CategoryDTO;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.FROM;

public class CategoryDAOProvider {

    // * 주석을 지우고 Provider 활용하여 내용을 작성하세요.

    public String selectCategoryList(Map<String, String> parameter) {
        SQL sql = new SQL();

        String option = parameter.get("option");

        if (parameter.containsValue("orderList")) {
            sql.SELECT("A.CATEGORY_CODE")
                    .SELECT("A.CATEGORY_NAME")
                    .SELECT("COUNT(B.PRODUCT_CODE) AS ROW_COUNT")
                    .FROM("PRODUCT_CATEGORY A")
                    .JOIN("PRODUCT_INFO B ON (A.CATEGORY_CODE = B.CATEGORY_CODE)")
                    .GROUP_BY("A.CATEGORY_CODE, A.CATEGORY_NAME")
                    .ORDER_BY("ROW_COUNT DESC");

        } else {
            sql.SELECT("A.CATEGORY_CODE")
                    .SELECT("A.CATEGORY_NAME")
                    .FROM("PRODUCT_CATEGORY A");
        }

        return sql.toString();
    }

            // 1. Provider를 활용하여 제품분류 목록을 조회하는 코드를 작성하세요.
        //    (조건) 전체 조회 or 제품분류 순위로 정렬해 조회하는 조건에 따른 Dynamic SQL로 작성하세요.
        //          제품분류 순위는 PRODUCT_INFO 테이블과 JOIN하여, 해당 제품분류에 속한 제품 갯수가 많은 순입니다.
        //          예를 들어, PRODUCT_INFO 테이블에 제품분류가 A인 제품이 10개, B인 제품이 4개, C인 제품이 13개 있다면
        //          제품분류 순위는 C > A > B 입니다. (단, 제품 갯수가 0개인 제품분류는 목록에 출력되지 않아도 됩니다.)
        //    아래 작성된 return null은 과제 툴 오류를 제거하고자 임의 작성하였으니 지우고 로직을 작성하세요.


    public String insertCategory(CategoryDTO category) {
        return new SQL()
                .INSERT_INTO("PRODUCT_CATEGORY")
                .VALUES("CATEGORY_NAME", "#{ name }").toString();

        // 2. Provider를 활용하여 제품분류를 등록하는 코드를 작성하세요.
        //    아래 작성된 return null은 과제 툴 오류를 제거하고자 임의 작성하였으니 지우고 로직을 작성하세요.

    }

    public String updateCategory(CategoryDTO category) {
        return new SQL()
                .UPDATE("PRODUCT_CATEGORY")
                .SET("CATEGORY_NAME = #{ name }")
                .WHERE("CATEGORY_CODE = #{ code }").toString();
        // 3. Provider를 활용하여 제품분류명을 수정하는 코드를 작성하세요.
        //    아래 작성된 return null은 과제 툴 오류를 제거하고자 임의 작성하였으니 지우고 로직을 작성하세요.

    }

    public String deleteCategory(Map<String, String> parameter) {
        return new SQL()
                .DELETE_FROM("PRODUCT_CATEGORY")
                .WHERE("CATEGORY_CODE = #{ parameter.CatCode }").toString();

        // 4. Provider를 활용하여 제품분류를 삭제하는 코드를 작성하세요.
        //    아래 작성된 return null은 과제 툴 오류를 제거하고자 임의 작성하였으니 지우고 로직을 작성하세요.

    }
}

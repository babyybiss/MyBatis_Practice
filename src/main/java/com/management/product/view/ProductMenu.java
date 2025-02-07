package com.management.product.view;

import com.common.SearchCondition;
import com.management.product.controller.ProductController;
import com.management.product.model.dto.ProductDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductMenu {

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        ProductController productController = new ProductController();

        do {
            System.out.println("======== Product Info 관리 ========");
            System.out.println("생산 및 판매 제품 정보 관리 화면입니다.");
            System.out.println("===================================");
            System.out.println("1. 전체 제품 정보 조회");
            System.out.println("2. 조건부 제품 정보 조회");
            System.out.println("3. 신규 제품 정보 등록");
            System.out.println("4. 기존 제품 정보 수정");
            System.out.println("5. 판매부진 단종 제품 삭제");
            System.out.println("9. 이전 메뉴로 이동");
            System.out.println("===================================");
            System.out.println("원하는 관리 메뉴의 번호를 입력해 주세요 : ");
            int selectMenu = sc.nextInt();

            switch (selectMenu) {
                case 1 : productController.selectAllProductList(); break;
                case 2 : productController.selectProductByCondition(condition()); break;
                case 3 : productController.registNewProduct(inputNewProductInfo()); break;
                case 4 : productController.modifyProductInfo(inputModifyProductInfo()); break;
                case 5 : productController.deleteProduct(inputProductCode()); break;
                case 9 : System.out.println("========상위 메뉴로 이동합니다.========"); return;
                default : System.out.println("잘못된 번호입니다. 확인 후 다시 입력해 주세요."); break;
            }

        } while(true);
    }

    private static SearchCondition condition() {

        Scanner sc = new Scanner(System.in);
        String searchOption = "";
        String searchValue = "";

        System.out.println("===================================");
        System.out.println("조회하고 싶은 조건을 선택하세요. ");
        System.out.println("===================================");
        System.out.println("1. 제품명으로 조회");
        System.out.println("2. 판매점별 취급 제품 조회");
        System.out.println("3. 이달의 신재품 조회");
        System.out.println("4. 생산 중단 제품 조회");
        System.out.println("===================================");
        System.out.println("원하는 조건의 번호를 입력해 주세요 : ");
        int selectMenu = sc.nextInt();

        switch (selectMenu) {
            case 1 :
                sc.nextLine();
                searchOption = "name";
                System.out.println("조회할 제품명을 입력해 주세요 : ");
                searchValue = sc.nextLine();
                break;
            case 2 :
                sc.nextLine();
                searchOption = "salesStore";
                System.out.println("판매점 유형을 입력해 주세요(백화점 or 아울렛) : ");
                searchValue = sc.nextLine();
                break;
            case 3 :
                searchOption = "newProduct";
                break;
            case 4 :
                searchOption = "nonProduction";
                break;
            case 9 : System.out.println("========상위 메뉴로 이동합니다.========"); return null;
            default : System.out.println("잘못된 번호입니다. 확인 후 다시 입력해 주세요."); break;
            }

        SearchCondition condition = new SearchCondition(searchOption, searchValue);

        return condition;
    }

    private static ProductDTO inputNewProductInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("등록할 새로운 제품 정보를 입력하세요. ");
        System.out.println("===================================");
        ProductDTO proDTO = new ProductDTO();
        getProductInfo(proDTO);
        System.out.println("===================================");

        return proDTO;
    }

    private static ProductDTO inputModifyProductInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("수정할 제품 정보를 입력하세요. ");
        System.out.println("수정을 원하지 않는 정보는 SKIP을 입력하세요.");
        System.out.println("===================================");
        System.out.println("수정 대상 제품코드를 입력해 주세요 : ");
        String code = sc.nextLine();

        ProductDTO proDTO = new ProductDTO();
        proDTO.setCode(code);

        getProductInfo(proDTO);

        System.out.println("제품의 판매량을 입력해 주세요 : ");
        String salesQ  = sc.nextLine();
        System.out.println("제품의 생산여부를 입력해 주세요(Y:생산중 / H:생산보류 / N:생산중단) : ");
        String proStat = sc.nextLine().toUpperCase();

        // 주석을 지우고 받아온 활동 상태를 productDTO 객체에 setting 하세요.
        proDTO.setSalesQ(salesQ);
        proDTO.setProStat(proStat);

        System.out.println("===================================");

        return proDTO;
    }

    private static ProductDTO getProductInfo(ProductDTO proDTO) {
        Scanner sc = new Scanner(System.in);

        System.out.println("제품명을 입력해 주세요 : ");
        String name = sc.nextLine();
        System.out.println("제품의 분류코드를 입력해 주세요 : ");
        String catCode = sc.nextLine();
        System.out.println("제품의 원가를 입력해 주세요 : ");
        String cost = sc.nextLine();
        System.out.println("제품의 출시일울 입력해 주세요(2000-01-01 형식) : ");
        String date = sc.nextLine();
        System.out.println("제품의 재고량을 입력해 주세요 : ");
        String stockQ  = sc.nextLine();
        System.out.println("제품의 할인율을 입력해 주세요 : ");
        String rate  = sc.nextLine();

        proDTO.setName(name);
        proDTO.setCatCode(catCode);
        proDTO.setCost(cost);
        proDTO.setDate(date);
        proDTO.setStockQ(stockQ);
        proDTO.setRate(rate);

        return proDTO;
    }

    private static Map<String, String> inputProductCode() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("삭제할 제품의 코드를 입력해 주세요 : ");
        String code = sc.nextLine();
        System.out.println("===================================");

        Map<String, String> parameter = new HashMap<>();
        parameter.put("code", code);

        return parameter;
    }
}

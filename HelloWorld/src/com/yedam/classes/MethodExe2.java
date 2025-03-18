package com.yedam.classes;

import java.util.ArrayList;
import java.util.List;

public class MethodExe2 {

	// 필드
	private List<Product> store;

	// 생성자
	MethodExe2() {
		store = new ArrayList<Product>();
		store.add(new Product("A001", "지우개", 500));
		store.add(new Product("B001", "샤프", 1000));
		store.add(new Product("C001", "연필", 800));
		store.add(new Product("D001", "볼펜", 700));
		store.add(new Product("E001", "자", 300));
		store.add(new Product("F001", "붓", 1300));
		store.add(new Product("G001", "지우개", 1000));
	}

	// 메소드
	boolean add(Product prd) { // 여기서 add는 무조건 boolean를 반환 해야함
		boolean result = store.add(prd);
		return result;
	}// end of void add(Product prd)

	// 상품이름, ALL
	List<Product> productList(Product prd) {
		List<Product> list = new ArrayList<Product>();
		for (int i = 0; i < store.size(); i++) {
			if (prd.getProductName().equals("ALL")//
					|| store.get(i).getProductName().equals(prd.getProductName())) {
				// 상품 가격이 조건으로 추가됨.
				if (store.get(i).getPrice() >= prd.getPrice()) {
					list.add(store.get(i));
				}
			}

		}
		return list;
	}// end of productList
		// 코드로 조회

//	Product[] productList(Product prd) {
//		Product[] list = new Product[10];
//		int idx = 0;
//		for (int i = 0; i < store.length; i++) {
//			if(store[i] != null) {
//				if (prd.getProductName().equals("ALL") || store[i].getProductName().equals(prd.getProductName())) {
//					//상품의 가격이 조건으로 추가됨.
//					if(store[i].getPrice() >= prd.getPrice()) {
//						list[idx++] = store[i];
//					}
//				}
//			}
//		}
//			return list;
//	}//end of productList

	// 삭제 - 상품코드로 삭제
	// boolean remove(String code)
	boolean remove(String code) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(code)) {
				store.remove(i);
				return true;
			}
		}
		return false;
	}// end of remove

	// 수정 -> boolean modify (Product prod)

	boolean modify(Product prod) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(prod.getProductCode())) {
				// 상품명 수정.
				if (prod.getProductCode() != null) {
					store.get(i).setProductName(prod.getProductName());
				}
				// 상품가격 수정
				if (prod.getPrice() != 0) {
					store.get(i).setPrice(prod.getPrice());
				}
				return true;
			}
		} // end of for

		return false;
	}// end of modify

}

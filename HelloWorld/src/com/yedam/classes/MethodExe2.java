package com.yedam.classes;

public class MethodExe2 {
	
	
	//필드
	private Product[] store;	
	
	//생성자
	MethodExe2() {
		store = new Product[10];
		store[0] = new Product("A001", "지우개", 500);
		store[1] = new Product("B001", "샤프", 1000);
		store[2] = new Product("C001", "연필", 800);	
		store[3] = new Product("D001", "볼펜", 700);	
		store[4] = new Product("E001", "자", 300);	
		store[5] = new Product("F001", "붓", 1300);	
		store[6] = new Product("G001", "지우개", 1000);
	}
	
	//메소드  
	boolean add(Product prd) {		//여기서 add는 무조건 boolean를 반환 해야함
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				store[i] = prd;
				return true;				
			}
		}
		return false;		
	}//end of void add(Product prd)
	
	//상품이름, ALL
	Product[] productList(Product prd) {
		Product[] list = new Product[10];
		int idx = 0;
		for (int i = 0; i < store.length; i++) {
			if(store[i] != null) {
				if (prd.getProductName().equals("ALL")//
					|| store[i].getProductName().equals(prd.getProductName())) {
					list[idx++] = store[i];
				}
			}
		}
		return list;
	}//end of productList
	//코드로 조회
	
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

	//삭제 - 상품코드로 삭제
	//boolean remove(String code)
	boolean remove(String code) {
		Product[] remove = new Product[10];
		for (int i = 0; i < store.length; i++) {
			if(store[i] != null
				&& store[i].getProductCode().equals(code)) {
					store[i] = null;
					
					return true;					
				}
			}
		return false;
		}//end of remove
	
	
	//수정 -> boolean modify (Product prod)
	
	boolean modify (Product prod) {
			for (int i = 0; i < store.length; i++) {
				if(store[i] != null
						&& store[i].getProductCode().equals(prod.getProductCode())) {
				//상품명 수정.
					if(prod.getProductCode() != null) {
						store[i].setProductName(prod.getProductName());
					}
					//상품가격 수정
					if(prod.getPrice() != 0) {
						store[i].setPrice(prod.getPrice());
					}			
					return true;					
				}
			}//end of for
		
		return false;
	}//end of modify
	
	

	
	
	
}

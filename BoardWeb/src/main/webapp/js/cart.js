/**
 *  js/cart.js
 */

window.addEventListener('DOMContentLoaded', function() {
	//수량 변경
	const rows = document.querySelectorAll('.row.data');

	rows.forEach(function(row) {
		const qtyInput = row.querySelector('.p_num');
		const priceHidden = row.querySelector('.p_price');
		const sumDiv = row.querySelector('.sum');
		const upBtn = row.querySelector('.up');
		const downBtn = row.querySelector('.down');

		//상품별 합계 계산.
		function updateSum() {
			const price = parseInt(priceHidden.value);
			let quantity = parseInt(qtyInput.value);

			if (isNaN(quantity) || quantity < 0) {
				quantity = 0;
				qtyInput.value = 0;
			}
			const sum = price * quantity;
			sumDiv.textContent = sum.toLocaleString() + '원';
			updateTotal();
		}

		//버튼 이벤트 리스너 (수량 변경)
		if (upBtn) {
			upBtn.addEventListener('click', function() {
				qtyInput.value = parseInt(qtyInput.value) + 1;
				updateSum();
			});
		}
		if (downBtn) {
			downBtn.addEventListener('click', function() {
				qtyInput.value = Math.max(1, parseInt(qtyInput.value) - 1);
				updateSum();
			});
		}

		//수량입력 이벤트 리스너
		qtyInput.addEventListener('keyup', updateSum);
	});

	//체크박스 유무, 전체 합계 계산.
	function updateTotal() {
		const rows = document.querySelectorAll('.row.data');
		let totalQty = 0;
		let totalPrice = 0;

		rows.forEach(function(row) {
			const checkbox = row.querySelector('input[type="checkbox"]');
			if (checkbox && checkbox.checked) {
				const qty = parseInt(row.querySelector('.p_num').value);
				const price = parseInt(row.querySelector('.p_price').value);

				totalQty += qty;
				totalPrice += qty * price;
			}
		});

		document.querySelector('#sum_p_num span').textContent = totalQty;
		document.querySelector('#sum_p_price span').textContent = totalPrice.toLocaleString();
	}
	//체크박스 상태가 바뀔때마다 전체합계 계산.
	document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
		checkbox.addEventListener('change', updateTotal);
	});

	//상품삭제
	document.querySelectorAll('.delete-btn').forEach(function(btn) {
		btn.addEventListener('click', function() {
			const row = btn.closest('.row.data');
			row.remove();
			updateTotal();
		});
	});

	//체크박스 선택 삭제
	document.querySelector('.delete-checked-btn')?.addEventListener('click', function() {
		const rows = document.querySelectorAll('.row.data');
		rows.forEach(function(row) {
			const checkbox = row.querySelector('input[type="checkbox"]');
			if (checkbox && checkbox.checked) {
				row.remove();
			}
		});
		updateTotal();
	});
	//장바구니 비우기
	document.querySelector('.delete-all-btn')?.addEventListener('click', function() {
		const rows = document.querySelectorAll('.row.data');
		rows.forEach(function(row) {
			row.remove();
		});
		updateTotal();
	});
	updateTotal();
});
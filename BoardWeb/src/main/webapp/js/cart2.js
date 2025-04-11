/**
 * cartf.js
 */
Number.prototype.formatNumber = function () {
  let strAry = (this + "").split('').reverse();
  let idx = 1;
  let cal = strAry.reduce((acc, num) => {
    acc += num;
    if (idx % 3 == 0 && idx != strAry.length) {
      acc += ',';
    }
    idx++;
    return acc;
  }, '');

  return cal.split('').reverse().join('');
}

// cart의 수량변경.
function changePNum(e) {
  console.log(e);
  // input, i (up, down)
  let inputItem, itemPrice = 0;
  let currentQty, changeQty = 1;

  if (e.target.nodeName == 'INPUT') {
    inputItem = e.target;
    // 수량을 증가할지 감소할지.
    if (e.key == "ArrowDown") {
      changeQty = -1;
    }
  } else if (e.target.nodeName == 'I') {
    inputItem = e.target.parentElement.parentElement.children[0];
    if (e.target.classList.contains('down')) {
      changeQty = -1;
    }
  }
  // 현재수량.
  currentQty = inputItem.value;
  // 증감된 수량 출력.
  inputItem.value = parseInt(currentQty) + changeQty;

  // 합계금액 계산.
  let targetItem = inputItem.parentElement.parentElement.parentElement.parentElement;

  itemPrice = targetItem.querySelector('input.p_price').value; // 금액.
  let amount = parseInt(inputItem.value) * itemPrice; // 금액 * 수량.

  targetItem.querySelector('div.sum').innerHTML = amount.formatNumber() + "원";

  // 합계부분 처리.
  changeSumInfo();

} // end of changePNum.

// 상품별 합계금액 계산.
function changePriceInfo(item) {
  console.log(item);
}

// 전체 합계정보 계산.
function changeSumInfo() {
  // 수량합계.
  let qty = 0;
  document.querySelectorAll('input.p_num').forEach(item => {
    qty += parseInt(item.value);
  })

  // 금액합계.
  let price = 0;
  document.querySelectorAll('div.sum').forEach((item, idx) => {
    if (idx) {
      let str = item.innerHTML.substring(0, item.innerHTML.length - 1);
      price += parseInt(str.replace(/,/g, ''))
    }
  })

  // 화면에 계산한 정보 출력.
  document.querySelector('div.sumcount>span').innerHTML = qty;
  document.querySelector('div.summoney>span').innerHTML = price.formatNumber();
} // end of changeSumInfo.
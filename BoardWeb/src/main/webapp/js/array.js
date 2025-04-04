/**
 *  array.js
 *  push, unshift 추가.
 *  pop, shift 제거.
 *  splice 추가, 수정, 삭제 .
 *  forEach()
 */

const ary = [];
// push, unshift
ary.push('홍길동'); //마지막 위치에 추가.
ary.push('김길동');
ary.unshift('최길동'); //첫번째 위치에 추가.

//ary.pop();//뒤에서부터 삭제.
//ary.shift();//앞에서부터 삭제.

//ary.splice(0, 1, '박길동','황길동' ); // 첫번째 위치 , 수정 , 대체값(값이 들어오면 수정또는 추가).
//ary.splice(0, 0, '홍길동'); // 크기가 0이면 추가.
//ary.splice(0, 3); //값이 들어오지 않으면 삭제.

ary.forEach(function(item, idx, ary) {
	console.log(`item=> ${item}, index=> ${idx} array=> ${ary}`);
});

// 함수.
function addElement(elem = "noElem") {
	ary.push(elem);
}
//화면요소 제거
function deleteElement(e) {
	//alert('삭제버튼클릭됨.');
	console.log(e.target.parentElement);
	e.target.parentElement.remove();
}

//추가 버튼에 대한 이벤트   /   버튼을 클릭했을때 함수가 발동하는 이벤트
document.querySelector('button#addBtn').addEventListener('click', function() {
//input의 값	
  let val = document.querySelector('input#userInput').value;
  addElement(val); // ary 배열에 추가.
  //화면에 출력.
  let html = '';
  ary.forEach(function(item, idx, ary) {
  //ary 배열의 반복
    html += '<li>' + item + '<button onclick="deleteElement(event)">삭제</button></li>';	
  });
  // ul태그의 영역.
  document.querySelector('ul#list').innerHTML = html;
});




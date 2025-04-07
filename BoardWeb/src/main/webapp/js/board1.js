/**
 * board1.js
 * XMLHttpRequest, fetch => 실행순서.
 */

//함수선언식
//목록출력
function successCallback(result) {
	console.log(result);
	result.forEach(item => {
		makeRow2(item);
	});
}
//=>함수표현식
//let successCallback = function successCallback (result) {
//	console.log(result);
//	result.forEach(item => {
//		makeRow2(item);
//	});
//}

//에러콜백
function errorCallback(err) {
	console.error(err);
}
 
//삭제함수
function deleteFnc(rno) {
	let deleteOK = confirm("삭제하시겠습니까?");
	if (!deleteOK) {
		return;
	}
	svc.removeReply(rno//삭제할 댓들번호
		, function(result) {
			console.log(result); //{retCode: 'OK/NG'}
			if (result.retCode == 'OK') {
				alert("삭제되었습니다.")
				//id 속성으로 찾기
				document.querySelector('#rno_' + rno).remove();

			}
		}
		, errorCallback);
}

//이벤트.
document.querySelector('button.addReply').addEventListener('click', function(e) {
	//등록.
	if (replyer == '') {
		alert('로그인하세요.');
		return;
	}
	//bno, replyer, reply: #reply.value 속성.
	let reply = document.querySelector('#reply').value;
	if (reply=='') {
		alert('댓글을 입력하세요.');
		return;
	}
	svc.addReply({ bno, reply, replyer }//등록하기위한 첫번째 파라메터
		, function(result) {
			if (result.retCode == "OK" ) {
				alert('등록성공!');
				let item = result.retVal; //반환되는 결과값을 활용.
				makeRow2(item);
			} else {
				alert('등록실패...');
				//입력값 초기화
//				document.querySelector('#reply').value
//				document.querySelector('#reply').value
			}
		}, errorCallback);

})

//목록보여주기
svc.replyList(bno, successCallback, errorCallback)

// 댓글정보 -> 화면출력.
function makeRow2(item) {
	let html = `<li data-rno="${item.replyNo}" id="rno_${item.replyNo}">
		 	      <span class="col-sm-2">${item.replyNo}</span>
		 	      <span class="col-sm-5">${item.reply}</span>
		 	      <span class="col-sm-2">${item.replyer}</span>
		 	      <span class="col-sm-2"><button onclick="deleteFnc(${item.replyNo})">삭제</button></span>
		 	    </li>`;
	let templ = document.querySelector('div.content>ul');
	templ.insertAdjacentHTML('beforeend', html);
}

// 동기, 비동기 (Asynchronous Javascript And Xml => ajax)
// 각 처리하는 시간이 1초씩 걸리면 총 3초가 걸렸을때,
//비동기 방식으로 처리하면 1번이 처리후 결과를 주기 전에 2번을 시작 하는 방식으로 전체 소요시간이 줄어들어 시간적 이점이 존재함.

//동기방식
setTimeout(function() {
	console.log('1');
}, 1000);
//1000미리세컨->1초
setTimeout(function() {
	console.log('2');
}, 1000);

setTimeout(function() {
	console.log('3');
}, 1000);

//비동기방식
setTimeout(function() {
	console.log('1');
	setTimeout(function() {
		console.log('2');
		setTimeout(function() {
			console.log('3');
		}, 1000);
	}, 1000);
}, 1000);

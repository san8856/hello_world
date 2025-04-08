/**
 * board1.js
 * XMLHttpRequest, fetch => 실행순서.
 */
//목록보여주기에 사용할 page 전역 함수 선언
let page = 1;


//함수선언식
//목록출력
//석세스 콜백
function successCallback(result) {
	//기존목록 지우기
	document.querySelectorAll('div.reply div.content>ul>li')//
		.forEach(function(item, idx){
			if(idx){ // truthy, falsy(0, null, '', undifined => false값처럼 여김)
				item.remove();
			}
		});
	//새로운 목록 출력.	
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

//페이징 콜백
function pagingCallback(result){
	//기존 페이지 목록 지우기.
	document.querySelector('nav>ul.pagination').innerHTML = "";

	console.log(result.totalCnt);
	let totalCnt = result.totalCnt;
	// 첫페이지, 마지막페이지, => 현재 페이지로 계산.
	let startPage, endPage;
	// 이전페이지, 이후페이지.
	let prev, next;
	endPage = Math.ceil(page / 10) * 10;
	startPage = endPage - 9;
	let realEnd = Math.ceil(totalCnt / 5);
	endPage = endPage > realEnd ? realEnd : endPage;
	prev = startPage == 1 ? false : true;
	next = endPage < realEnd ? true : false;
		
	// 이전페이지.
		let prevContent;
		if (!prev) {
			prevContent = `<li class="page-item disabled">
			                 <span class="page-link">Previous</span>
			               </li>`;
		} else {
			prevContent = `<li class="page-item">
			                 <a class="page-link" data-page="${startPage-1}" href="#">Previous</a>
			               </li>`;
		}
		let prevTarget = document.querySelector('nav>ul.pagination');
		prevTarget.insertAdjacentHTML('beforeend', prevContent);

		// 페이지 갯수.
		let html;
		for (let p = startPage; p <= endPage; p++) {
			if (p == page) {
				html = `<li class="page-item active" aria-current="page">
				          <span class="page-link">${p}</span>
				        </li>`;
			} else {
				html = `<li class="page-item"><a class="page-link" data-page="${p}" href="#">${p}</a></li>`;
			}
			let target = document.querySelector('nav>ul.pagination');
			target.insertAdjacentHTML('beforeend', html);
		}

		// 이후페이지.
		let nextContent;
		if (!next) {
			nextContent = `<li class="page-item disabled">
				             <span class="page-link">Next</span>
				           </li>`;
		} else {
			nextContent = `<li class="page-item">
				             <a class="page-link" data-page="${endPage+1}" href="#">Next</a>
				           </li>`;
		}
		let nextTarget = document.querySelector('nav>ul.pagination');
		nextTarget.insertAdjacentHTML('beforeend', nextContent);

    //링크이벤트.
	pageLink();
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
				svc.replyList({ bno, page }, successCallback, errorCallback)
				svc.pagingList(bno, pagingCallback, errorCallback);

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
				svc.replyList({ bno, page }, successCallback, errorCallback)
				svc.pagingList(bno, pagingCallback, errorCallback);
			} else {
				alert('등록실패...');
				//입력값 초기화
//				document.querySelector('#reply').value
//				document.querySelector('#reply').value
			}
		}, errorCallback);
})

	//페이지 링크에 이벤트 등록.
function pageLink(){
	document.querySelectorAll('div.reply ul a')//
		.forEach(function(atag) {
		atag.addEventListener('click', function(e){
			e.preventDefault(); // 이벤트의 기본 기능을 차단.
			page = atag.dataset.page; // <a data-page>"3"</a>
			
			//댓글목록
			svc.replyList({ bno, page }, successCallback, errorCallback)
			//페이징 목록 보여주기.
			svc.pagingList(bno, pagingCallback, errorCallback);
		})
	});	
}

	
	
//목록보여주기
svc.replyList({ bno, page }, successCallback, errorCallback)
//        속성의 이름과 값이 동일할경우 줄여써도 됌{ bno:bno, page:page }

//페이징 목록 보여주기.
svc.pagingList(bno, pagingCallback, errorCallback);

// 댓글정보 -> 화면출력.
function makeRow2(item) {
	let html = `<li data-rno="${item.replyNo}" id="rno_${item.replyNo}">
		 	      <span class="col-sm-2">${item.replyNo}</span>
		 	      <span class="col-sm-5">${item.reply}</span>
		 	      <span class="col-sm-2">${item.replyer}</span>
		 	      <span class="col-sm-2"><button class="btn btn-light btn-sm" style="margin-bottom: 10px;" onclick="deleteFnc(${item.replyNo})">삭제</button></span>
		 	    </li>`;
	let templ = document.querySelector('div.content>ul');
	templ.insertAdjacentHTML('beforeend', html);
}



// 동기, 비동기 (Asynchronous Javascript And Xml => ajax)
// 각 처리하는 시간이 1초씩 걸리면 총 3초가 걸렸을때,
//비동기 방식으로 처리하면 1번이 처리후 결과를 주기 전에 2번을 시작 하는 방식으로 전체 소요시간이 줄어들어 시간적 이점이 존재함.

//동기방식
//setTimeout(function() {
//	console.log('1');
//}, 1000);
//1000미리세컨->1초
//setTimeout(function() {
//	console.log('2');
//}, 1000);

//setTimeout(function() {
//	console.log('3');
//}, 1000);

//비동기방식
//setTimeout(function() {
//	console.log('1');
//	setTimeout(function() {
//		console.log('2');
//		setTimeout(function() {
//			console.log('3');
//		}, 1000);
//	}, 1000);
//}, 1000);

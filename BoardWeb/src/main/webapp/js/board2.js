/**
 * board2.js
 */

const table = new DataTable('#example', {
	ajax: 'replyListDatatable.do?bno=' + bno,
	columns: [
		{ data: 'REPLY_NO' },
		{ data: 'REPLY' },
		{ data: 'REPLYER' },
		{ data: 'REPLY_DATE' }
	],
	lengthMenu: [
		[5, 10, 25, 50, -1],
		[5, 10, 25, 50, 'All']
	],
	order: [[0, 'desc']]
});

// 1.등록
function addNewRow() {
	// control 을 통해서 db 한건생성.
	let reply = document.querySelector('#addContent').value;
	fetch('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + reply)
		.then(result => result.json())
		.then(result => {
			console.log(result);
			let obj = result.retVal;
			// 화면에 출력.
			table.row
				.add({
					REPLY_NO: obj.replyNo,
					REPLY: obj.reply,
					REPLYER: obj.replyer,
					REPLY_DATE: obj.replyDate
				})
				.draw(false);
			//입력값을 초기화.
			document.querySelector('#addContent').value = "";
		})
		.catch(err => console.error(err));
}

document.querySelector('#addRow')//
	.addEventListener('click', addNewRow);

// 2.삭제
let rno;
table.on('click', 'tbody tr', (e) => {
	console.log(e.currentTarget.children[0].innerHTML);
	let classList = e.currentTarget.classList; // class목록()

	// classList에 contains('클래스명'), 제거(remove('클래스명'), 추가(add('클래스명'))
	if (classList.contains('selected')) {
		classList.remove('selected');
		rno = '';
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
		classList.add('selected');
		rno = e.currentTarget.children[0].innerHTML;
	}
});

document.querySelector('#delRow')//
	.addEventListener('click', function() {
		if (!rno) {
			alert('삭제할 글을 선택하세요.');
			return;
		}
		fetch('removeReply.do?rno=' + rno)
			.then(result => result.json())
			.then(result => {
				if (result.retCode == 'OK') {
					alert('삭제성공');
					// 화면에서 삭제.
					table.row('.selected').remove().draw(false);
				}
			})
			.catch(err => console.error(err))

	});

// end of file.
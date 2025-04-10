/**
 * json.js
 */

const jsonStr = `[{"id":1,"first_name":"Matthieu","last_name":"Crolla","email":"mcrolla0@seesaa.net","gender":"Male","salary":9181},
{"id":2,"first_name":"Erick","last_name":"Broxap","email":"ebroxap1@icq.com","gender":"Male","salary":3180},
{"id":3,"first_name":"Morty","last_name":"Mawd","email":"mmawd2@bloglovin.com","gender":"Male","salary":7951},
{"id":4,"first_name":"Carolus","last_name":"Trowell","email":"ctrowell3@lulu.com","gender":"Male","salary":5469},
{"id":5,"first_name":"Jamie","last_name":"Eagar","email":"jeagar4@ycombinator.com","gender":"Male","salary":9481},
{"id":6,"first_name":"Elisabet","last_name":"Benes","email":"ebenes5@mozilla.com","gender":"Female","salary":8012},
{"id":7,"first_name":"Malory","last_name":"Burbank","email":"mburbank6@ft.com","gender":"Polygender","salary":4976},
{"id":8,"first_name":"Carlota","last_name":"Dudny","email":"cdudny7@soundcloud.com","gender":"Genderfluid","salary":4947},
{"id":9,"first_name":"Bay","last_name":"Yoslowitz","email":"byoslowitz8@ox.ac.uk","gender":"Male","salary":4288},
{"id":10,"first_name":"Inglis","last_name":"Kringe","email":"ikringe9@unesco.org","gender":"Male","salary":3708},
{"id":11,"first_name":"Britt","last_name":"Dent","email":"bdenta@flavors.me","gender":"Male","salary":5383},
{"id":12,"first_name":"Marijo","last_name":"Hinckes","email":"mhinckesb@hao123.com","gender":"Female","salary":6127},
{"id":13,"first_name":"Ginni","last_name":"Tanman","email":"gtanmanc@creativecommons.org","gender":"Female","salary":3656},
{"id":14,"first_name":"Kellby","last_name":"Seivwright","email":"kseivwrightd@shutterfly.com","gender":"Male","salary":6767},
{"id":15,"first_name":"Celeste","last_name":"Walklot","email":"cwalklote@hao123.com","gender":"Non-binary","salary":1980}]`;

let obj = JSON.parse(jsonStr); // json문자열 -> object 변경.
let str = JSON.stringify(obj); // object -> json문자열 변경.
console.log(obj);

// 한건 데이터를 매개값으로 tr을 생성하는 함수.
function makeRow(emp = { id, first_name, last_name, email, gender, salary }) {
	const fields = ['id', 'first_name', 'last_name', 'email'];
	let tr = document.createElement('tr'); // <tr></tr>
	for (let i = 0; i < fields.length; i++) {
		let td = document.createElement('td'); // <td></td>
		td.innerHTML = emp[fields[i]]; // <td>1</td>
		tr.appendChild(td); // <tr><td>1</td><td>Matthieu</td></tr>
	}
	return tr;
}

// 화면출력.
obj.forEach(function(item, idx, ary) {
	let tr = makeRow(item);
	document.querySelector('tbody#target').appendChild(tr);
})









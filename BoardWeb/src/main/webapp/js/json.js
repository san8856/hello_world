/**
 *  json.js
 */

const jsonStr = `[{"id":1,"first_name":"Flossi","last_name":"Leinthall","email":"fleinthall0@topsy.com","gender":"Female","salary":7011},
{"id":2,"first_name":"Aline","last_name":"Fasson","email":"afasson1@list-manage.com","gender":"Female","salary":7256},
{"id":3,"first_name":"Lucille","last_name":"Lythgoe","email":"llythgoe2@posterous.com","gender":"Female","salary":4091},
{"id":4,"first_name":"Rivi","last_name":"Searl","email":"rsearl3@mail.ru","gender":"Female","salary":7401},
{"id":5,"first_name":"Stefano","last_name":"Megarrell","email":"smegarrell4@stanford.edu","gender":"Male","salary":2988},
{"id":6,"first_name":"Eddy","last_name":"Delaney","email":"edelaney5@mlb.com","gender":"Female","salary":7583},
{"id":7,"first_name":"Jermaine","last_name":"Tregale","email":"jtregale6@auda.org.au","gender":"Female","salary":6343},
{"id":8,"first_name":"Helenka","last_name":"Iddy","email":"hiddy7@usnews.com","gender":"Female","salary":9087},
{"id":9,"first_name":"Leonelle","last_name":"Fairweather","email":"lfairweather8@sun.com","gender":"Female","salary":6370},
{"id":10,"first_name":"Darcee","last_name":"Di Baudi","email":"ddibaudi9@qq.com","gender":"Female","salary":7499},
{"id":11,"first_name":"Klarrisa","last_name":"Stewartson","email":"kstewartsona@macromedia.com","gender":"Female","salary":7105},
{"id":12,"first_name":"Tam","last_name":"Govenlock","email":"tgovenlockb@trellian.com","gender":"Male","salary":8133},
{"id":13,"first_name":"Afton","last_name":"Stimpson","email":"astimpsonc@apple.com","gender":"Female","salary":3767},
{"id":14,"first_name":"Brear","last_name":"Nansom","email":"bnansomd@facebook.com","gender":"Female","salary":2564},
{"id":15,"first_name":"Conan","last_name":"Danick","email":"cdanicke@cnbc.com","gender":"Male","salary":5168}]`;

let obj = JSON.parse(jsonStr); // json 문자열 -> object 변경
let str = JSON.stringify(obj); // object -> json 문자열 변경.
console.log(obj);

// 한건 데이터를 매개값으로 tr을 생성하는 함수.
function makeRow(emp = {id, first_name, last_name, email, gender, salary}) {
  const fields = ['id', 'first_name', 'last_name', 'email'];
  let tr = document.createElement('tr'); // <tr></tr> 생성
  for (let i=0; i<fields.length; i++){
    let td = document.createElement('td'); //<td></td>
	td.innerHTML = emp[fields[i]]	       //<td>1</td>
	tr.appendChild(td);   //<tr><td> id </td><td> firstname </td><td> lastname </td><td> email </td></tr>
  }	
  return tr;
}

//화면 출력
obj.forEach(function(item, idx, ary) {
  let tr = makeRow(item);
  document.querySelector('tbody#target').appendChild(tr);
})


















<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8"%>
 <!DOCTYPE html>
 <html>
 
 <head>
   <meta charset='utf-8' />
   <script src='js/index.global.js'></script>
   <script>
 <meta charset='utf-8' />
 <script src='js/index.global.js'></script>
 <script>
     document.addEventListener('DOMContentLoaded', function () {
       var calendarEl = document.getElementById('calendar');
       var events = [];
       const calendarEl = document.getElementById('calendar');
       const events = [];
       // Ajax call.
       console.log('1');
 
       //일정 목록 불러오기
       fetch('eventList.do')
         .then(result => result.json())
         .then(eventListCallback)
         .catch(err => console.error(err));
 
       console.log('3');
 
 	  //이벤트 리스트 콜백
       function eventListCallback(result) {
         console.log(result);
         result.forEach(item => {
 @@ -28,9 +28,10 @@
                 start: item.START_DATE,
                 end: item.END_DATE
             });
             
         }); // json문자열 -> events 변수 할당.
         var calendar = new FullCalendar.Calendar(calendarEl, {
         
         //FullCalender 초기화
         const calendar = new FullCalendar.Calendar(calendarEl, {
           headerToolbar: {
             left: 'prev,next today',
             center: 'title',
 @@ -40,52 +41,89 @@
           navLinks: true, // can click day/week names to navigate views
           selectable: true,
           selectMirror: true,
           
           //일정등록
           select: function (arg) {
             var title = prompt('Event Title:');
             const title = prompt('Event Title:');
             if (title) {
               calendar.addEvent({
                 title: title,
                 start: arg.start,
                 end: arg.end,
                 allDay: arg.allDay
             	//서버에 등록
             	fetch('addEvent.do?title=' + encodeURIComponent(title) + '&sDate=' 
             	  + arg.startStr + '&eDate=' + arg.endStr)
             	  .then(res => res.json())
             	  .then(data => {
             		  if(data.retCode === 'OK') {
 		                calendar.addEvent({
         	            title: title,
               		    start: arg.start,
                 	    end: arg.end,
                 	    allDay: arg.allDay
 	                  });
            		  } else {
            			  alert("다시 입력해주세요.")
            		  }
               })
               .catch(err => console.error(err));
             }
             calendar.unselect()
             calendar.unselect();
           },
           
           //일정삭제
           eventClick: function (arg) {
             if (confirm('Are you sure you want to delete this event?')) {
               arg.event.remove()
               const eventOBJ = {
                 title: arg.event.title,
                 startDate: arg.event.startStr,
                 endDate: arg.event.endStr
               };
               
               //서버에서 삭제
               fetch('removeEvent.do', {
             	  method: 'POST',
             	  headers: {
             		  'Content-Type': 'application/json'
             	  },
             	  body: JSON.stringify(eventOBJ)
               })
               .then(res => res.json())
               .then(data => {
             	  if(data.retCode === 'OK') {
             		  arg.event.remove();
             	  } else {
             		  alert("다시 시도해주세요.");
             	  }
               })
               .catch(err => console.error(err));
             }
           },
           
           editable: true,
           dayMaxEvents: true, // allow "more" link when too many events
           events: events
         });
 
         
         calendar.render();
       } // end of eventListCallback.
 
 
     });
   </script>
   <style>
     body {
       margin: 40px 10px;
       padding: 0;
       font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
       font-size: 14px;
     }
   
 <style>
 body {
 	margin: 40px 10px;
 	padding: 0;
 	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
 	font-size: 14px;
 }
 
     #calendar {
       max-width: 1100px;
       margin: 0 auto;
     }
   </style>
 #calendar {
 	max-width: 1100px;
 	margin: 0 auto;
 }
 </style>
 </head>
 
 <body>
 
   <div id='calendar'></div>
 	<div id='calendar'></div>
 
 </body>
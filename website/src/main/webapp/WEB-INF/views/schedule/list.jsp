<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/css/calendar.css">
<script src='/resources/js/calendar.js'></script>


<%@include file="../includes/header.jsp" %>

<div>
	<div class="panel">
		<div class="panel-body">
			<div class="form-inline">
				<div class="form-group m-l-10 Homepage-navlink">
					<a class=" active" href="${pageContext.request.contextPath}/adms/reservation/roomReservation/list.do">회의실 예약</a>
					<a href="${pageContext.request.contextPath}/adms/reservation/roomReservation/list_my_reservation.do">나의 예약</a>
				</div>
			</div>
		</div>
	</div>

	<div class="panel">
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-2" style="padding-right: 5px">
					<!-- <div class="row" style="margin: auto;">
						<div class="col-sm-12  form-group has-feedback">
							<input type="text" class="form-control" autocomplete="off"
								id="search" placeholder="검색..."> <span
								class="fa fa-search form-control-feedback right"
								aria-hidden="true" style="line-height: 2"></span>
						</div>
					</div> -->
					<div class="row">
						<div class="col-xs-12 text-center"
							style="margin-top: 10px; margin-bottom: 10px;">
							<button class="btn" type="button" data-toggle="modal"
								data-target="#calendarModal" id="btn-reservation">회의실
								예약</button>
						</div>
					</div>
					<div class="row">
						<div class="col-12 text-center">
							<div id="minicalendar"></div>
						</div>
						<%-- <div class="col-lg-12">
							<div id="sidebar-menu">
								<ul>
									<li class="has-sub-sub">
										<a href="javascript:;" class="waves-effect subdrop"> 
											<span>회의실 선택</span> 
											<span class="pull-right"><i class="fa fa-angle-right" style="vertical-align: middle; line-height: 1"></i></span>
									</a>
										<ul class="list-unstyled sub-sub" style="margin-left: 20px; display: block;">
											<c:forEach var="result" items="${roomList}" varStatus="status">
												<div class="checkbox checkbox-color-${status.index%10}">
													<input id="room-${result.main_code}" class="room_selector" type="checkbox" checked="checked" value="${result.main_code}" style="accent-color: ${result.code_etc}"> 
													<label for="room-${result.main_code}"> ${result.code_name} </label>
												</div>
											</c:forEach>

										</ul></li>
								</ul>
							</div>
							<div id="sidebar-menu" style="overflow-y: auto; max-height: 613px;">
								<ul>
									<li class="has-sub-sub">
										<a href="javascript:;" class="waves-effect subdrop"> 
											<span>반복 회의</span> 
											<span class="pull-right"><i class="fa fa-angle-right" style="vertical-align: middle; line-height: 1"></i></span>
										</a>
										<ul class="bullet-list" style="margin-left: 20px; display: block;" id="list-repeated-events">
											<c:forEach items="${mainScheduleList}" var="reservation">
												<c:if test="${reservation.is_repeat and (reservation.repeat_mode==RepeatMode.EVERY_WEEK or reservation.repeat_mode==RepeatMode.EVERY_2_WEEKS)}">
												<li class="bullet-list-item">
													<span style="color:${reservation.room_color}">&#x25CF;</span>
													<span>
														<fmt:parseDate var="date" pattern="yyyy-MM-dd" value="${reservation.date}" />
														<fmt:formatDate pattern="(E) MM/dd" value="${date}" />
														(${reservation.starttime}-${reservation.endtime})
													</span> 
													<span>: ${reservation.title}</span>
													<span>(${reservation.room_name})</span>
												</li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
								</ul>
							</div>
						</div> --%>
					</div>
				</div>
				<div class="col-xs-10">
					<div id='calendar'></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--  end panel -->
	<!-- Trigger the modal with a button -->
 
</div>

           
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">

function listEvents(startDate, endDate) {
	$.ajax({
		url: "${pageContext.request.contextPath}/adms/reservation/roomReservation/list_ajax.do",
		data: {
			"start_timestamp" : startDate,
			"end_timestamp": endDate
		},
		method: 'POST',
		success: function(response) {
			if(response!=null && response.mainScheduleList!=null) {
				mainScheduleList = []
				$("#list-repeated-events").empty()
				for (var i=0; i<response.mainScheduleList.length; i++) {
					mainScheduleList.push({
			    		id	  : response.mainScheduleList[i].id,
						title : response.mainScheduleList[i].title,
						start : response.mainScheduleList[i].start_timestamp,
						end   : response.mainScheduleList[i].end_timestamp,
						room_code: response.mainScheduleList[i].room_code,
						room_name  : response.mainScheduleList[i].room_name,
						color : response.mainScheduleList[i].room_color
					})
					
					/* if (response.mainScheduleList[i].is_repeat && (response.mainScheduleList[i].repeat_mode=='EVERY_WEEK' || response.mainScheduleList[i].repeat_mode=='EVERY_2_WEEKS')) {
						var item = '<li class="bullet-list-item"> ' +
						'<span style="color:' + response.mainScheduleList[i].room_color + '">&#x25CF;</span>' +
						'<span>' +
						'	' + moment(response.mainScheduleList[i].date).format("(ddd) MM/DD") +
						'	(' + response.mainScheduleList[i].starttime + '-' + response.mainScheduleList[i].endtime + ')' +
						'</span> ' +
						'<span>: ' + response.mainScheduleList[i].title + '</span>' +
						'</li>';
						$("#list-repeated-events").append(item)
					} */
				}
				calendar.refetchEvents()
			}
		}
	});
}



var mainScheduleList = new Array();
<c:forEach items="${mainScheduleList}" var="reservation">
	mainScheduleList.push({
		id	  : "${reservation.id}",
		title : "${reservation.title}",
		start : "${reservation.start_timestamp}",
		end   : "${reservation.end_timestamp}",
		room_code: "${reservation.room_code}",
		room_name  : "${reservation.room_name}",
		color : "${reservation.room_color}",
		backgroundColor : "${reservation.room_color}",

	})
</c:forEach>
	
var calendar = null;

document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');

	calendar = new FullCalendar.Calendar(calendarEl, {
		expandRows : true,
		headerToolbar : {
			left : 'prev,next today',
			center : 'title',
			right : 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
		},
		initialView : 'dayGridMonth',
		locale : 'ko',
		initialDate : new Date(),
		navLinks : true, // can click day/week names to navigate views
		//editable : true,
		selectable : true,
		select : function(e) {
			var calendarMode = e.view.type;
	        switch(calendarMode) {
		        case "dayGridMonth":	//월
		        	$('#reservation-date,#repeat_till').datepicker('setDate', e.start);
		          break;
		        case "timeGridWeek": 	// 주
		        case "timeGridDay": 	// 일
		        	$('#reservation-date,#repeat_till').datepicker('setDate', e.start);
		        	$('#reservation-starttime').val(moment(e.start).format('HH:mm'))
		        	$('#reservation-endtime').val(moment(e.end).format('HH:mm'))
		         	break;
		    }
			$('#calendarModal').modal('toggle')
		},
		nowIndicator : true,
		dayMaxEvents : true, 
		selectConstraint:{
			startTime: '00:00', 
			endTime: '24:00', 
	    },
		events : function (fetchInfo, successCallback, failureCallback) { successCallback(mainScheduleList); },
		eventDidMount: function ( arg ) {
			let selectedRooms = $('.room_selector:checked').map(function(){ return $(this).val(); }).get();
			if (selectedRooms.indexOf(arg.event.extendedProps.room_code) < 0 ) {
				arg.el.style.display = "none";
			}
	    }, 
	    eventClick: function(info) {
	        view_event(info.event.id);
	    },
		displayEventEnd: true,
		eventTimeFormat: { // like '14:30'
            hour: '2-digit',
            minute: '2-digit',
            hour12: false
        },
        allDaySlot: false,
	});

	calendar.render();
	
	calendar.on('datesSet', function(payload) {
		var calendarMode = payload.view.type;
        switch(calendarMode) {
	        case "dayGridMonth":	//월
	        	var date = moment(payload.start).add(10, "days").date(1).format("YYYY-MM-DD")
				var startDate = moment(payload.start).add(6, "days").date(1).format("YYYY-MM-DD")
				var endDate = moment(payload.start).add(6, "days").date(31).format("YYYY-MM-DD")
	        	$("#minicalendar").datepicker('update', date);
	        	listEvents(startDate, endDate)
	          break;
	        case "timeGridWeek": 	// 주
	        case "timeGridDay": 	// 일
	        case "listWeek": 		// 일정목록
	        	var date = moment(payload.start).format("YYYY-MM-DD")
				var startDate = moment(payload.start).date(1).format("YYYY-MM-DD")
				var endDate = moment(payload.start).date(31).format("YYYY-MM-DD")
	        	$("#minicalendar").datepicker('update', date);
	        	listEvents(startDate, endDate)
	         	break;
	    }
	});
});

/*calendar*/
$(function() {

	$("#minicalendar").datepicker({
		inline : true,
		format : "yyyy-mm-dd",
		language : "ko",
		todayHighlight: true
	}).on("changeDate", function(e) {
		var startDate = e.date, endDate = e.date;
		
		if (calendar!=null){
			calendar.gotoDate(e.date)
		}
        
    });
	
	$('#minicalendar').datepicker('setDate', new Date());

});

$(document).on('change', '.room_selector', function(){
    if (calendar != null) {
    	calendar.refetchEvents()
    }
})


</script>
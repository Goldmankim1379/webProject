<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<<script type="text/javascript">

function _onSubmit(){
	
	 if($("#board_writer").val() == ""){
	    	alert("작성자를 입력해주세요");
			$("#board_writer").focus();
			return false;
	    }
	
	 if($("#board_title").val() == ""){
	    	alert("제목을 입력해주세요");
			$("#board_title").focus();
			return false;
	    }
	
	 if($("#board_content").val() == ""){
	    	alert("내용을 입력해주세요");
			$("#board_content").focus();
			return false;
	    }

	if(!confirm("등록하시겠습니까?")){
		return false;
	}
}

</script>




<%@include file="../includes/header.jsp"%>

<div class="row" style="margin-bottom:20px; margin-left:1px;">
	<div class="col-lg-12">
		<h1 class="page-header">등록 페이지</h1>
	</div>
</div>

<div class="panel" style="margin-left:1px;">
	<div id="contAreaBox">
		<div class="panel">
			<div class="panel-body">
				<form role="form" action="/board/create_action" method="post" onsubmit="return _onSubmit();">
				<div class="table-responsive" style="text-align:center;">
					<table id="datatable-scroller"
						class="table table-bordered tbl_Form">
						<caption></caption>
						<colgroup>
							<col width="250px" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th class="active" >작성자</th>
								<td class="form-inline"><input type="text" id="board_writer"
									name="board_writer" class="form-control" style="width: 200px" />
								</td>
							</tr>
							<tr> 
								<th class="active">제목</th>
								<td class="form-inline"><input type="text" id="board_title"
									name="board_title" class="form-control" style="width: 840px" />
								</td>
							</tr>
							<tr>
								<th class="active" >내용</th>
								<td class="form-inline"><textarea 
										id="board_content" name="board_content" cols="100" rows="10"
										class="form-control"></textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="margin-left:1px;">
					<button type="submit" class="btn btn-primary">등록</button>
					<a href="/board/list" class="btn btn-danger">취소</a>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="../includes/footer.jsp"%>
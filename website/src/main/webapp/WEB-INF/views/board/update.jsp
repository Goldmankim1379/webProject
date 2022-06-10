<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>

function _onSubmit(){
	if(!confirm("수정하시겠습니까?")){
		return false;
	}
}

</script>



<%@include file="../includes/header.jsp"%>

<div class="row" style="margin-bottom:20px; margin-left:1px;">
	<div class="col-lg-12">
		<h1 class="page-header">수정 페이지</h1>
	</div>
</div>

<div class="panel" style="margin-left:1px;">
	<div id="contAreaBox">
		<div class="panel">
			<div class="panel-body">
				<form role="form" action="/board/update_action" method="post" onsubmit="return _onSubmit();" >
				<input type="hidden" id="board_idx" name="board_idx" value="${boardContents.board_idx }"/>
				<input type="hidden" id="qustr" name="qustr" value="${searchVO.qustr}"/>
				
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
								<th class="active">제목</th>
								<td class="form-inline"><input type="text" id="board_title"
									name="board_title" class="form-control" style="width: 840px" value="${boardContents.board_title }"/>
								</td>
							</tr>
							<tr>
								<th class="active" >작성자</th>
								<td class="form-inline"><input type="text" id="board_writer"
									name="board_writer" class="form-control" style="width: 200px" value="${boardContents.board_writer }"/>
								</td>
							</tr>
							<tr>
								<th class="active" >내용</th>
								<td class="form-inline"><textarea 
										id="board_content" name="board_content" cols="100" rows="10"
										class="form-control">${boardContents.board_content }</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="margin-left:1px;">
					<button type="submit" class="btn btn-success">수정</button>
					<a href="/board/read?board_idx=${boardContents.board_idx}&${searchVO.qustr}" class="btn btn-danger">취소</a>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="../includes/footer.jsp"%>